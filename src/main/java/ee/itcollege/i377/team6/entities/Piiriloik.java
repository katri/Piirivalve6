package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;


/**
 * The persistent class for the PIIRILOIK database table.
 * 
 */
@Entity
@RooToString
@RooEntity
@Transactional
public class Piiriloik extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PIIRILOIK_ID")
	private Long piiriloikId;
	
	@NotNull
	@Size(max = 20)
	private String kood;
	
	@NotNull
	@Size(max = 60)
	private String nimetus;
    	
	@Column(name="GPS_KOORDINAADID")
	private String gpsKoordinaadid;

	//bi-directional many-to-one association to PiiriloiguHaldaja
	@OneToMany(mappedBy="piiriloik")
	private List<PiiriloiguHaldaja> piiriloiguHaldajas;

	
	//bi-directional many-to-one association to VahtkonndPiiriloigul
	@OneToMany(mappedBy="piiriloik")
	private Set<VahtkonndPiiriloigul> vahtkonndPiiriloiguls;

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
        	this.setDeleted();
        	this.entityManager.merge(this);
        } else {
            Piiriloik attached = Piiriloik.findPiiriloik(this.piiriloikId);
            this.setDeleted();
            this.entityManager.merge(attached);
        }
    }
    
    public static List<Piiriloik> findAllPiiriloiks() {
        return entityManager().createQuery("SELECT o FROM Piiriloik o WHERE suletud ='9999-12-31'", Piiriloik.class).getResultList();
    }
    
      
    public static List<Piiriloik> findPiiriloikEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Piiriloik o WHERE suletud ='9999-12-31'", Piiriloik.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
	
    public Piiriloik() {
    }
    //haldajaMuutmine
	

	public List<PiiriloiguHaldaja> getPiiriloiguHaldajas() {
		return this.piiriloiguHaldajas;
	}


	// Määrab piirilõiguhaldajate setiks parasjagu need read, mis piirilõiguhaldajate olemis
	// selle konkreetse piirilõigu ID-d omavad
	
	public void setPiiriloiguHaldajas(Long id) throws ParseException {
		this.piiriloiguHaldajas = manageRelations(this.piiriloiguHaldajas, id);
	
	}
	
/*Haldab piirilõiguhaldaja tabeli muudatusi ja logimist selles
	Esiteks: Kui vana piirilõiguhaldaja set on tühi, siis teeme lihtsalt uue kirje.	
	Kui ei olnud tühi, siis leiame kõige uuema kuupäevaga "kuni" kirje vanas piiriloiguhaldaja setis. 
	Teiseks:
		Kui uus väeosa ID on sama, mis uus ja kuni kpv on täna või hilisem siis ei tehta midagi - st tagastatkse sama set.
		
		Kui uus väeosa ID on sama, mis uus ja kuni kpv on tänasest varasem, siis on tegemist
		uue kirje lisamisega: alates, avaja ja avatud. Lisame ka piirilõigu ID ja ja uue väeosa ID.
		
		Kui vana väeosa ID on tühi, ja uus on olemas, siis muudetakse välju alates, avaja ja avatud.
			Lisame ka piirilõigu ID ja ja uue väeosa ID.
		
		Kui vana väeosa ID!=uue väeosa ID ja vana ning uus on olemas, siis :
			Vana rida: täidame väljad muutja, muudetud ja kuni=tänane kpv.
			Uus rida: Täidame välja alates, mis on sama, mis vana rea kuni e tänane kpv. 
				Täidame ka väljad avaja ja avatud=tänane kpv. Lisame ka piirilõigu ID ja ja uue väeosa ID. 
		
		Kui vana väeosa ID on olemas aga uus on tühi, siis uut rida ei tehta. Vana reaga:
			täidame väljad muutja, muudetud=tänane kpv ja kuni=tänane kpv.
*/
	private List<PiiriloiguHaldaja> manageRelations(List<PiiriloiguHaldaja> oldSet, Long id) throws ParseException {
		
				
		
		// Tänast kuupĆ¤eva lĆ¤heb ikka vaja
		Calendar now = Calendar.getInstance();
		
		//kauge tulevik
		
	
		
		//Teeme uue piirilĆµiguHaldaja objekti
		PiiriloiguHaldaja workObject= new PiiriloiguHaldaja();
		
		// Kui vana set on tühi, siis teeme lihtsalt uue kirje
		if(oldSet==null){
			workObject.setAlates(now);
			workObject.setKuni(temporaryClosedDate());
			
			// ei ole vist vaja-tuleb ise? workObject.setPiiriloiguHaldajaId();
			workObject.setPiiriloik(this);
			workObject.setPiiripunkt(null);
			workObject.setVaeosa(Vaeosa.findVaeosa(id));
			workObject.persist();
			}
		else{
		
		//Leiame kĆµige uuema kuupĆ¤evaga "kuni" kirje vanas piiriloiguhaldaja setis. 
		Calendar uusimKpv;
		uusimKpv=null;
		
	for(PiiriloiguHaldaja o: oldSet) {
			if (uusimKpv.before(o.getKuni()))
				uusimKpv=o.getKuni();
				workObject=o;
		}
		// Kui uus vĆ¤eosa ID on sama, mis uus ja kuni kpv on tĆ¤na vĆµi hilisem siis ei tehta midagi - st tagastatkse sama set.
	if(workObject.getVaeosa().getVaeosaIdId()==id){
		return oldSet;
	}
	else{
		
		workObject.setVaeosa(Vaeosa.findVaeosa(id));
		workObject.merge();
	}
		}
		
		return (PiiriloiguHaldaja.findAllPiiriloiguHaldajas());
	}
	
	
	
	
	
	public Set<VahtkonndPiiriloigul> getVahtkonndPiiriloiguls() {
		return this.vahtkonndPiiriloiguls;
	}

	public void setVahtkonndPiiriloiguls(Set<VahtkonndPiiriloigul> vahtkonndPiiriloiguls) {
		this.vahtkonndPiiriloiguls = vahtkonndPiiriloiguls;
	}
	public Long getPiiriloikId() {
		return this.piiriloikId;
	}

	public void setPiiriloikId(Long piiriloikId) {
		this.piiriloikId = piiriloikId;
	}

		public String getGpsKoordinaadid() {
		return this.gpsKoordinaadid;
	}

	public void setGpsKoordinaadid(String gpsKoordinaadid) {
		this.gpsKoordinaadid = gpsKoordinaadid;
	}



	public String getKood() {
		return this.kood;
	}

	public void setKood(String kood) {
		this.kood = kood;
	}



	public String getNimetus() {
		return this.nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}


}
