package ee.itcollege.i377.team6.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;


/**
 * The persistent class for the PIIRIPUNKTI_ALLUVUS database table.
 * 
 */
@Entity
@RooToString
@RooEntity
@Table(name="PIIRIPUNKTI_ALLUVUS")
public class PiiripunktiAlluvus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PIIRIPUNKTI_ALLUVUS_ID")
	private Long piiripunktiAlluvusId;

	private String alates;

	private String kuni;

  	//bi-directional many-to-one association to Piiripunkt
    @ManyToOne
	@JoinColumn(name="PIIRIPUNKT_ID")
	private Piiripunkt piiripunkt;

	//bi-directional many-to-one association to Vaeosa
    @ManyToOne
	@JoinColumn(name="VAEOSA_ID")
	private Vaeosa vaeosa;

    public PiiripunktiAlluvus() {
    }

	public Long getPiiripunktiAlluvusId() {
		return this.piiripunktiAlluvusId;
	}

	public void setPiiripunktiAlluvusId(Long piiripunktiAlluvusId) {
		this.piiripunktiAlluvusId = piiripunktiAlluvusId;
	}

	public String getAlates() {
		return this.alates;
	}

	public void setAlates(String alates) {
		this.alates = alates;
	}

	

	public String getKuni() {
		return this.kuni;
	}

	public void setKuni(String kuni) {
		this.kuni = kuni;
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
	
}
