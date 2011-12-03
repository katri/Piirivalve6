package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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

    @Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	private Date alates;

	@Column(name="GPS_LATITUDE")
	private BigDecimal gpsLatitude;

	@Column(name="GPS_LONGITUIDE")
	private BigDecimal gpsLongituide;

	private String kood;

	@Column(name="KORGUS_MEREPINNAST")
	private BigDecimal korgusMerepinnast;

    @Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	private Date kuni;

	private String nimetus;

  	//bi-directional many-to-one association to PiiriloiguHaldaja
	@OneToMany(mappedBy="piiripunkt")
	private Set<PiiriloiguHaldaja> piiriloiguHaldajas;

	//bi-directional many-to-one association to PiiripunktiAlluvus
	@OneToMany(mappedBy="piiripunkt")
	private Set<PiiripunktiAlluvus> piiripunktiAlluvuses;

	//bi-directional many-to-one association to Vahtkond
	@OneToMany(mappedBy="piiripunkt")
	private Set<Vahtkond> vahtkonds;

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
