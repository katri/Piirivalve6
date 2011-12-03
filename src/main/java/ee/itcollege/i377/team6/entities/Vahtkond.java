package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;


/**
 * The persistent class for the VAHTKOND database table.
 * 
 */
@Entity
@RooToString
@RooEntity

public class Vahtkond extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VAHTKOND_ID")
	private Long vahtkondId;
	
	@Temporal( TemporalType.DATE)
	@DateTimeFormat(style = "M-")
	private Date alates;

	private String kood;
	
	@Temporal( TemporalType.DATE)
	@DateTimeFormat(style = "M-")
	private Date kuni;
	 
	private String nimetus;
	
	//bi-directional many-to-one association to Piiripunkt
    @ManyToOne
	@JoinColumn(name="PIIRIPUNKT_ID")
	private Piiripunkt piiripunkt;

	//bi-directional many-to-one association to Vaeosa
    @ManyToOne
	@JoinColumn(name="VAEOSA_ID_ID")
	private Vaeosa vaeosa;

	//bi-directional many-to-one association to VahtkonndPiiriloigul
	@OneToMany(mappedBy="vahtkond")
	private Set<VahtkonndPiiriloigul> vahtkonndPiiriloiguls;

    public Vahtkond() {
    }

	public Long getVahtkondId() {
		return this.vahtkondId;
	}

	public void setVahtkondId(Long vahtkondId) {
		this.vahtkondId = vahtkondId;
	}

	public Date getAlates() {
		return this.alates;
	}

	public void setAlates(Date alates) {
		this.alates = alates;
	}

	
	public String getKood() {
		return this.kood;
	}

	public void setKood(String kood) {
		this.kood = kood;
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
	
	public Set<VahtkonndPiiriloigul> getVahtkonndPiiriloiguls() {
		return this.vahtkonndPiiriloiguls;
	}

	public void setVahtkonndPiiriloiguls(Set<VahtkonndPiiriloigul> vahtkonndPiiriloiguls) {
		this.vahtkonndPiiriloiguls = vahtkonndPiiriloiguls;
	}
	
}
