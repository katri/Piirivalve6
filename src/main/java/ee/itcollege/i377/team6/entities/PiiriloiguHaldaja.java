package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;


/**
 * The persistent class for the PIIRILOIGU_HALDAJA database table.
 * 
 */

@Entity
@RooToString
@RooEntity
@Table(name="PIIRILOIGU_HALDAJA")
public class PiiriloiguHaldaja extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PIIRILOIGU_HALDAJA_ID")
	private Long piiriloiguHaldajaId;
/*
	 @NotNull
	 @Temporal( TemporalType.DATE)
	 @DateTimeFormat(style = "M-")
	private Calendar alates;

	  @NotNull
		@Temporal( TemporalType.DATE)
	    @DateTimeFormat(style = "M-")
		private Calendar kuni;
	*/
	//bi-directional many-to-one association to Piiriloik
    @ManyToOne
	@JoinColumn(name="PIIRILOIK_ID")
	private Piiriloik piiriloik;

	//bi-directional many-to-one association to Piiripunkt
    @ManyToOne
	@JoinColumn(name="PIIRIPUNKT_ID")
	private Piiripunkt piiripunkt;

	//bi-directional many-to-one association to Vaeosa
    @ManyToOne
	@JoinColumn(name="VAEOSA_ID_ID")
	private Vaeosa vaeosa;

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
        	this.setDeleted();
            entityManager.merge(this);
        } else {
            PiiriloiguHaldaja attached = PiiriloiguHaldaja.findPiiriloiguHaldaja(this.piiriloiguHaldajaId);
            this.setDeleted();
            entityManager.merge(attached);
        }
    }
    
    @Transactional
    public static List<PiiriloiguHaldaja> findAllPiiriloiguHaldajas() {
        return entityManager().createQuery("SELECT o FROM PiiriloiguHaldaja o WHERE suletud ='9999-12-31'", PiiriloiguHaldaja.class).getResultList();
    }
    
    @Transactional
    public static List<PiiriloiguHaldaja> findPiiriloiguHaldajaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PiiriloiguHaldaja o WHERE suletud ='9999-12-31'", PiiriloiguHaldaja.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public PiiriloiguHaldaja() {
    }

	public Long getPiiriloiguHaldajaId() {
		return this.piiriloiguHaldajaId;
	}

	public void setPiiriloiguHaldajaId(Long piiriloiguHaldajaId) {
		this.piiriloiguHaldajaId = piiriloiguHaldajaId;
	}

	
	public Piiriloik getPiiriloik() {
		return this.piiriloik;
	}

	public void setPiiriloik(Piiriloik piiriloik) {
		this.piiriloik = piiriloik;
	}
	
	public Piiripunkt getPiiripunkt() {
		return this.piiripunkt;
	}

	public void setPiiripunkt(Piiripunkt piiripunkt) {
		this.piiripunkt = piiripunkt;
	}
	
	public Vaeosa getVaeosa() {
		return this.vaeosa;
	}

	public void setVaeosa(Vaeosa vaeosa) {
		this.vaeosa = vaeosa;
	}


	/*public Calendar getAlates() {
		return alates;
	}


	public void setAlates(Calendar alates) {
		this.alates = alates;
	}


	public Calendar getKuni() {
		return kuni;
	}


	public void setKuni(Calendar kuni) {
		this.kuni = kuni;
	}*/
	
}
