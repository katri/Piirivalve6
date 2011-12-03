package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;


/**
 * The persistent class for the RIIGI_ADMIN_YKSUS database table.
 * 
 */
@Entity
@RooToString
@RooEntity

@Table(name="RIIGI_ADMIN_YKSUS")
public class RiigiAdminYksus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RIIGI_ADMIN_YKSUS_ID")
	private Long riigiAdminYksusId;

	private String kood;

	private String nimetus;

	//bi-directional many-to-one association to Vaeosa
	@OneToMany(mappedBy="riigiAdminYksus")
	private Set<Vaeosa> vaeosas;

    public RiigiAdminYksus() {
    }

	public Long getRiigiAdminYksusId() {
		return this.riigiAdminYksusId;
	}

	public void setRiigiAdminYksusId(Long riigiAdminYksusId) {
		this.riigiAdminYksusId = riigiAdminYksusId;
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

	public Set<Vaeosa> getVaeosas() {
		return this.vaeosas;
	}

	public void setVaeosas(Set<Vaeosa> vaeosas) {
		this.vaeosas = vaeosas;
	}
	
}
