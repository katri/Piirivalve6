package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;


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
	
	@NotNull
	@Size(max = 20)
	private String kood;
	
	@NotNull
	@Size(max = 100)
	private String nimetus;

	//bi-directional many-to-one association to Vaeosa
	@OneToMany(mappedBy="riigiAdminYksus")
	private Set<Vaeosa> vaeosas;

	   @Transactional
	    public void remove() {
	        if (this.entityManager == null) this.entityManager = entityManager();
	        if (this.entityManager.contains(this)) {
	        	this.setDeleted();
	        	this.entityManager.merge(this);
	        } else {
	        	this.setDeleted();
	            RiigiAdminYksus attached = RiigiAdminYksus.findRiigiAdminYksus(this.riigiAdminYksusId);
	            this.entityManager.merge(attached);
	        }
	    }
	   
	    public static List<RiigiAdminYksus> findAllRiigiAdminYksuses() {
	        return entityManager().createQuery("SELECT o FROM RiigiAdminYksus o WHERE suletud ='9999-12-31'", RiigiAdminYksus.class).getResultList();
	    }
	    
	    public static List<RiigiAdminYksus> findRiigiAdminYksusEntries(int firstResult, int maxResults) {
	        return entityManager().createQuery("SELECT o FROM RiigiAdminYksus o WHERE suletud ='9999-12-31'", RiigiAdminYksus.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	    }
	    
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
