package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * The persistent class for the PIIRILOIK database table.
 * 
 */
@Entity
@RooToString
@RooEntity
public class Piiriloik extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PIIRILOIK_ID")
	private Long piiriloikId;

  
    	
	@Column(name="GPS_KOORDINAADID")
	private String gpsKoordinaadid;

	private String kood;

  	private String nimetus;

    @Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	

	//bi-directional many-to-one association to PiiriloiguHaldaja
	@OneToMany(mappedBy="piiriloik")
	private Set<PiiriloiguHaldaja> piiriloiguHaldajas;

	
	//bi-directional many-to-one association to VahtkonndPiiriloigul
	@OneToMany(mappedBy="piiriloik")
	private Set<VahtkonndPiiriloigul> vahtkonndPiiriloiguls;

    public Piiriloik() {
    }
    //haldajaMuutmine
	

	public Set<PiiriloiguHaldaja> getPiiriloiguHaldajas() {
		return this.piiriloiguHaldajas;
	}


	// Määrab piirilõiguhaldajate setiks parasjagu need read, mis piirilõiguhaldajate olemis
	// selle konkreetse piirilõigu ID-d omavad
	public void setPiiriloiguHaldajas(Vaeosa newVaeosa) throws ParseException {
		this.piiriloiguHaldajas = manageRelations(this.piiriloiguHaldajas, newVaeosa);
	
		
		
	}
	
/*Haldab piirilõiguhaldaja tabeli muudatusi ja logimist selles
	Esiteks: Kui vana piirilõiguhaldaja set on tühi, siis teeme lihtsalt uue kirje.	
	Teiseks: Leiame kõige uuema kuupäevaga "kuni" kirje vanas piiriloiguhaldaja setis. 
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
	private Set<PiiriloiguHaldaja> manageRelations(Set<PiiriloiguHaldaja> oldSet, Vaeosa newVaeosa) throws ParseException {
		
		// Tänast kuupäeva läheb ikka vaja
		DateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd");		
		Date now = new Date();
		
		//kauge tulevik
		
		Date future = (Date)dateFormat.parse("31.12.9999"); 
		
		//kasutajanimi
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName();
		
		//Teeme tagastatava uue piirilõiguHaldaja objekti
		PiiriloiguHaldaja workObject= new PiiriloiguHaldaja();
		
		// Kui vana set on tühi, siis teeme lihtsalt uue kirje
		if(oldSet==null){
			//workObject.setAlates(now);
			workObject.setAvaja(userName);
			//workObject.setAvatud(now);
			//workObject.setMuudetud(future);
			workObject.setMuutja(userName);
			// ei ole vist vaja-tuleb is? workObject.setPiiriloiguHaldajaId();
			workObject.setPiiriloik(this);
			workObject.setPiiripunkt(null);
			//workObject.setSuletud(future);
			
			workObject.setSulgeja(userName);
			workObject.setVaeosa(newVaeosa);
			
			
		}
		else{
		
		//Leiame kõige uuema kuupäevaga "kuni" kirje vanas piiriloiguhaldaja setis. 
		Date uusimKpv;
		uusimKpv=null;
		
/*		for(PiiriloiguHaldaja o: oldSet) {
			if (uusimKpv.before(o.getKuni()))
				uusimKpv=o.getKuni();
				workObject=o;
		}*/
		// Kui uus väeosa ID on sama, mis uus ja kuni kpv on täna või hilisem siis ei tehta midagi - st tagastatkse sama set.
		}
		
		return ((Set<PiiriloiguHaldaja>) PiiriloiguHaldaja.findAllPiiriloiguHaldajas());
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
