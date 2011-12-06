package ee.itcollege.i377.team6.entities;

import java.util.Calendar;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.security.core.context.SecurityContextHolder;

//Baasklass, mis tegutseb tegevusi logivate olemitega

@RooJavaBean
@MappedSuperclass
@RooEntity
public class BaseEntity {
	
	@NotNull
	private String kommentaar;
	
	@Size(max = 32)
	private String avaja;
	
	
	@Temporal( TemporalType.DATE)
	@DateTimeFormat(style = "M-")
	private Calendar avatud;
	
	@Size(max = 32)
	private String muutja;
	
	
	@Temporal( TemporalType.DATE)
	@DateTimeFormat(style = "M-")
	private Calendar muudetud;
	
	@Size(max = 32)
	private String sulgeja;
	
	
	@Temporal( TemporalType.DATE)
	@DateTimeFormat(style = "M-")
	private Calendar suletud;
	
	@PrePersist
	public void setCreated() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Calendar now = Calendar.getInstance();
		setOpen(currentUser, now);
		setModified(currentUser, temporaryClosedDate());
		setClosed(currentUser, temporaryClosedDate());
	}
	
	@PreUpdate
	public void setUpdated() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Calendar now = Calendar.getInstance();
		
		setModified(currentUser, now);
	
	}
	
	@PreRemove
	public void setDeleted() {
		Calendar now = Calendar.getInstance();
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		setClosed(currentUser, now);
	}
	
	private void setOpen(String user, Calendar date) {
		this.avaja = user;
		this.avatud = date;
	}
	
	private void setModified(String user, Calendar date) {
		this.muutja = user;
		this.muudetud = date;
	}
	
	private void setClosed(String user, Calendar date) {
		this.suletud = date;
		this.sulgeja = user;
	}
	
	protected Calendar temporaryClosedDate() {
		Calendar tempDate = Calendar.getInstance();
		tempDate.clear();
		tempDate.set(Calendar.YEAR, 9999);
		tempDate.set(Calendar.MONTH, Calendar.DECEMBER);
		tempDate.set(Calendar.DAY_OF_MONTH, 31);
		return tempDate;
	}
	
	
	
	public String getKommentaar() {
		return kommentaar;
	}
	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
	}
	public String getAvaja() {
		return avaja;
	}
	public void setAvaja(String avaja) {
		this.avaja = avaja;
	}
	public Calendar getAvatud() {
		return avatud;
	}
	public void setAvatud(Calendar avatud) {
		this.avatud = avatud;
	}
	public String getMuutja() {
		return muutja;
	}
	public void setMuutja(String muutja) {
		this.muutja = muutja;
	}
	public Calendar getMuudetud() {
		return muudetud;
	}
	public void setMuudetud(Calendar muudetud) {
		this.muudetud = muudetud;
	}
	public String getSulgeja() {
		return sulgeja;
	}
	public void setSulgeja(String sulgeja) {
		this.sulgeja = sulgeja;
	}
	public Calendar getSuletud() {
		return suletud;
	}
	public void setSuletud(Calendar suletud) {
		this.suletud = suletud;
	}

}
