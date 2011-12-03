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
	
}
