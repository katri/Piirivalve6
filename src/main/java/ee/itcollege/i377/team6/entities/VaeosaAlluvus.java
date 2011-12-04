package ee.itcollege.i377.team6.entities;

import java.io.Serializable;
import java.util.Date;
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
 * The persistent class for the VAEOSA_ALLUVUS database table.
 * 
 */
@Entity
@RooToString
@RooEntity
@Table(name="VAEOSA_ALLUVUS")
public class VaeosaAlluvus extends BaseEntity implements  Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VAEOSA_ALLUVUS_ID")
	private Long vaeosaAlluvusId;
	
	//bi-directional many-to-one association to Vaeosa
    @ManyToOne
	@JoinColumn(name="YLEMUS_VAEOSA_ID")
	private Vaeosa vaeosa2;
    
   	//bi-directional many-to-one association to Vaeosa
    @ManyToOne
	@JoinColumn(name="ALLUVA_VAEOSA_ID")
	private Vaeosa vaeosa1;
	
	@NotNull
    @Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	private Date alates;
	
	@NotNull
	@Temporal( TemporalType.DATE)
    @DateTimeFormat(style = "M-")
	private Date kuni;

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
        	this.setDeleted();
            this.entityManager.merge(this);
        } else {
            VaeosaAlluvus attached = VaeosaAlluvus.findVaeosaAlluvus(this.vaeosaAlluvusId);
            this.setDeleted();
            this.entityManager.merge(attached);
        }
    }
	
    public static List<VaeosaAlluvus> findAllVaeosaAlluvuses() {
        return entityManager().createQuery("SELECT o FROM VaeosaAlluvus o WHERE suletud ='9999-12-31'", VaeosaAlluvus.class).getResultList();
    }
    
  
    public static List<VaeosaAlluvus> findVaeosaAlluvusEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM VaeosaAlluvus o WHERE suletud ='9999-12-31'", VaeosaAlluvus.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public VaeosaAlluvus() {
    }

	public Long getVaeosaAlluvusId() {
		return this.vaeosaAlluvusId;
	}

	public void setVaeosaAlluvusId(Long vaeosaAlluvusId) {
		this.vaeosaAlluvusId = vaeosaAlluvusId;
	}

	public Date getAlates() {
		return this.alates;
	}

	public void setAlates(Date alates) {
		this.alates = alates;
	}

	

	public Date getKuni() {
		return this.kuni;
	}

	public void setKuni(Date kuni) {
		this.kuni = kuni;
	}

	
	public Vaeosa getVaeosa1() {
		return this.vaeosa1;
	}

	public void setVaeosa1(Vaeosa vaeosa1) {
		this.vaeosa1 = vaeosa1;
	}
	
	public Vaeosa getVaeosa2() {
		return this.vaeosa2;
	}

	public void setVaeosa2(Vaeosa vaeosa2) {
		this.vaeosa2 = vaeosa2;
	}
	
}
