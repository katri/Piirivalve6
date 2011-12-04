package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;


/**
 * The persistent class for the PIIRIPUNKT database table.
 * 
 */
@Entity
@RooToString
@RooEntity
public class Piiripunkt extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PIIRIPUNKT_ID")
	private Long piiripunktId;

	@NotNull
	@Size(max = 20)
	private String kood;
	
	@NotNull
	@Size(max = 100)
	private String nimetus;
	
	@Digits(integer=9, fraction=5)
    @Column(name="GPS_LATITUDE")
	private BigDecimal gpsLatitude;

	@Digits(integer=9, fraction=5)
	@Column(name="GPS_LONGITUIDE")
	private BigDecimal gpsLongituide;

	@Digits(integer=6, fraction=2)
	@Column(name="KORGUS_MEREPINNAST")
	private BigDecimal korgusMerepinnast;

	@NotNull
	@Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	private Date alates;
	
	@NotNull
    @Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	private Date kuni;

	

  	//bi-directional many-to-one association to PiiriloiguHaldaja
	@OneToMany(mappedBy="piiripunkt")
	private Set<PiiriloiguHaldaja> piiriloiguHaldajas;

	//bi-directional many-to-one association to PiiripunktiAlluvus
	@OneToMany(mappedBy="piiripunkt")
	private Set<PiiripunktiAlluvus> piiripunktiAlluvuses;

	//bi-directional many-to-one association to Vahtkond
	@OneToMany(mappedBy="piiripunkt")
	private Set<Vahtkond> vahtkonds;

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
        	this.setDeleted();
            this.entityManager.merge(this);
        } else {
            Piiripunkt attached = Piiripunkt.findPiiripunkt(this.piiripunktId);
            this.setDeleted();
            this.entityManager.merge(attached);
        }
    }
    
    public static List<Piiripunkt> findAllPiiripunkts() {
        return entityManager().createQuery("SELECT o FROM Piiripunkt o WHERE suletud ='9999-12-31'", Piiripunkt.class).getResultList();
    }
    
    public static List<Piiripunkt> findPiiripunktEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Piiripunkt o WHERE suletud ='9999-12-31'", Piiripunkt.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
	
    public Piiripunkt() {
    }

	public Long getPiiripunktId() {
		return this.piiripunktId;
	}

	public void setPiiripunktId(Long piiripunktId) {
		this.piiripunktId = piiripunktId;
	}

	public Date getAlates() {
		return this.alates;
	}

	public void setAlates(Date alates) {
		this.alates = alates;
	}

	public BigDecimal getGpsLatitude() {
		return this.gpsLatitude;
	}

	public void setGpsLatitude(BigDecimal gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public BigDecimal getGpsLongituide() {
		return this.gpsLongituide;
	}

	public void setGpsLongituide(BigDecimal gpsLongituide) {
		this.gpsLongituide = gpsLongituide;
	}

	public String getKood() {
		return this.kood;
	}

	public void setKood(String kood) {
		this.kood = kood;
	}

	public BigDecimal getKorgusMerepinnast() {
		return this.korgusMerepinnast;
	}

	public void setKorgusMerepinnast(BigDecimal korgusMerepinnast) {
		this.korgusMerepinnast = korgusMerepinnast;
	}

	public Date getKuni() {
		return this.kuni;
	}

	public void setKuni(Date kuni) {
		this.kuni = kuni;
	}

	
	public String getNimetus() {
		return this.nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public Set<PiiriloiguHaldaja> getPiiriloiguHaldajas() {
		return this.piiriloiguHaldajas;
	}

	public void setPiiriloiguHaldajas(Set<PiiriloiguHaldaja> piiriloiguHaldajas) {
		this.piiriloiguHaldajas = piiriloiguHaldajas;
	}
	
	public Set<PiiripunktiAlluvus> getPiiripunktiAlluvuses() {
		return this.piiripunktiAlluvuses;
	}

	public void setPiiripunktiAlluvuses(Set<PiiripunktiAlluvus> piiripunktiAlluvuses) {
		this.piiripunktiAlluvuses = piiripunktiAlluvuses;
	}
	
	public Set<Vahtkond> getVahtkonds() {
		return this.vahtkonds;
	}

	public void setVahtkonds(Set<Vahtkond> vahtkonds) {
		this.vahtkonds = vahtkonds;
	}
	
}
