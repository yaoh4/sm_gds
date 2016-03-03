package gov.nih.nci.cbiit.scimgmt.gds.domain;
// Generated Mar 3, 2016 2:46:22 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * MailTemplateT generated by hbm2java
 */
@Entity
@Table(name = "MAIL_TEMPLATE_T")
public class MailTemplateT implements java.io.Serializable {

	private Integer id;
	private String shortIdentifier;
	private String description;
	private String subject;
	private String body;
	private Boolean activeFlag;
	private Date createdDate;
	private String createdBy;
	private Date lastChangedDate;
	private String lastChangedBy;

	public MailTemplateT() {
	}

	public MailTemplateT(Integer id, String subject, String body, Boolean activeFlag, Date createdDate,
			String createdBy) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.activeFlag = activeFlag;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public MailTemplateT(Integer id, String shortIdentifier, String description, String subject, String body,
			Boolean activeFlag, Date createdDate, String createdBy, Date lastChangedDate, String lastChangedBy) {
		this.id = id;
		this.shortIdentifier = shortIdentifier;
		this.description = description;
		this.subject = subject;
		this.body = body;
		this.activeFlag = activeFlag;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastChangedDate = lastChangedDate;
		this.lastChangedBy = lastChangedBy;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SHORT_IDENTIFIER", length = 80)
	public String getShortIdentifier() {
		return this.shortIdentifier;
	}

	public void setShortIdentifier(String shortIdentifier) {
		this.shortIdentifier = shortIdentifier;
	}

	@Column(name = "DESCRIPTION", length = 800)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SUBJECT", nullable = false, length = 400)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "BODY", nullable = false, length = 4000)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Type(type="yes_no")
	@Column(name = "ACTIVE_FLAG", nullable = false)
	public Boolean getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false, length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 120)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_CHANGED_DATE", length = 7)
	public Date getLastChangedDate() {
		return this.lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	@Column(name = "LAST_CHANGED_BY", length = 120)
	public String getLastChangedBy() {
		return this.lastChangedBy;
	}

	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

}
