package net.upaste.shared.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Paste")
@NamedQueries({
	@NamedQuery(name = "RecentPastes", query = "SELECT p FROM Paste p WHERE p.isPrivate = false ORDER BY p.addedOn DESC"),
	@NamedQuery(name = "LimitedPastes", query = "SELECT p FROM Paste p WHERE p.isPrivate = false ORDER BY p.addedOn DESC")
})
public class Paste extends BaseModel {

	@Column(name = "Title")
	private String title;
	
	@Column(name = "Content", length=8086)
	private String content;
	
	@Column(name = "Private")
	private boolean isPrivate = false;
	
	@Column(name = "AddedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn = new Date();
	
	@Column(name = "HighlightType")
	private String highlightType;
	
	@Column(name = "Email")
	private String email;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	public void setHighlightType(String highlightType) {
		this.highlightType = highlightType;
	}
	public String getHighlightType() {
		return this.highlightType;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
}
