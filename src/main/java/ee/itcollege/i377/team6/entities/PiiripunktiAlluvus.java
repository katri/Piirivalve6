package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;


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

	@NotNull
	@Size(max = 20)
	private String alates;

	@NotNull
	@Size(max = 20)
	private String kuni;


  	//bi-directional many-to-one association to Piiripunkt
    @ManyToOne
	@JoinColumn(name="PIIRIPUNKT_ID")
	private Piiripunkt piiripunkt;

	//bi-directional many-to-one association to Vaeosa
    @ManyToOne
	@JoinColumn(name="VAEOSA_ID")
	private Vaeosa vaeosa;

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
        	 this.setDeleted();
            this.entityManager.merge(this);
        } else {
            PiiripunktiAlluvus attached = PiiripunktiAlluvus.findPiiripunktiAlluvus(this.piiripunktiAlluvusId);
            this.setDeleted();
            this.entityManager.merge(attached);
        }
    }
    
    public static List<PiiripunktiAlluvus> findAllPiiripunktiAlluvuses() {
        return entityManager().createQuery("SELECT o FROM PiiripunktiAlluvus o WHERE suletud ='9999-12-31'", PiiripunktiAlluvus.class).getResultList();
    }
    
   
    public static List<PiiripunktiAlluvus> findPiiripunktiAlluvusEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PiiripunktiAlluvus o WHERE suletud ='9999-12-31'", PiiripunktiAlluvus.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
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
