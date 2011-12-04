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
 * The persistent class for the VAHTKONND_PIIRILOIGUL database table.
 * 
 */
@Entity
@RooToString
@RooEntity
@Table(name="VAHTKONND_PIIRILOIGUL")
public class VahtkonndPiiriloigul extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VAHTKOND_PIIRILOIUL_ID")
	private Long vahtkondPiiriloiulId;
	
	//bi-directional many-to-one association to Vahtkond
    @ManyToOne
	@JoinColumn(name="VAHTKOND_ID")
	private Vahtkond vahtkond;
	
  //bi-directional many-to-one association to Piiriloik
    @ManyToOne
	@JoinColumn(name="PIIRILOIK_ID")
	private Piiriloik piiriloik;

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
            VahtkonndPiiriloigul attached = VahtkonndPiiriloigul.findVahtkonndPiiriloigul(this.vahtkondPiiriloiulId);
            this.setDeleted();
            this.entityManager.merge(attached);
        }
    }
    
    public static List<VahtkonndPiiriloigul> findAllVahtkonndPiiriloiguls() {
        return entityManager().createQuery("SELECT o FROM VahtkonndPiiriloigul o WHERE suletud ='9999-12-31'", VahtkonndPiiriloigul.class).getResultList();
    }
    
    public static List<VahtkonndPiiriloigul> findVahtkonndPiiriloigulEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM VahtkonndPiiriloigul o WHERE suletud ='9999-12-31'", VahtkonndPiiriloigul.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public VahtkonndPiiriloigul() {
    }

	public Long getVahtkondPiiriloiulId() {
		return this.vahtkondPiiriloiulId;
	}

	public void setVahtkondPiiriloiulId(Long vahtkondPiiriloiulId) {
		this.vahtkondPiiriloiulId = vahtkondPiiriloiulId;
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

	public Piiriloik getPiiriloik() {
		return this.piiriloik;
	}

	public void setPiiriloik(Piiriloik piiriloik) {
		this.piiriloik = piiriloik;
	}
	
	public Vahtkond getVahtkond() {
		return this.vahtkond;
	}

	public void setVahtkond(Vahtkond vahtkond) {
		this.vahtkond = vahtkond;
	}
	
}
