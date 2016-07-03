package gov.nih.nci.cbiit.scimgmt.gds.domain;
// Generated Mar 28, 2016 10:25:57 AM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DulChecklistSelection generated by hbm2java
 */
@Entity
@Table(name = "DUL_CHECKLIST_SELECTIONS_T")
public class DulChecklistSelection implements java.io.Serializable {

	private Long id;
	private StudiesDulSet studiesDulSet;
	private DulChecklist dulChecklist;
	private Date createdDate;
	private String createdBy;
	private Date lastChangedDate;
	private String lastChangedBy;

	public DulChecklistSelection() {
	}

	public DulChecklistSelection(Long id, StudiesDulSet studiesDulSet, DulChecklist dulChecklist,
			Date createdDate, String createdBy) {
		this.id = id;
		this.studiesDulSet = studiesDulSet;
		this.dulChecklist = dulChecklist;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public DulChecklistSelection(Long id, StudiesDulSet studiesDulSet, DulChecklist dulChecklist,
			Date createdDate, String createdBy, Date lastChangedDate, String lastChangedBy) {
		this.id = id;
		this.studiesDulSet = studiesDulSet;
		this.dulChecklist = dulChecklist;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastChangedDate = lastChangedDate;
		this.lastChangedBy = lastChangedBy;
	}

	@Id
	@SequenceGenerator(name="dulcs_seq_gen", sequenceName="DULCS_DOC_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dulcs_seq_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DUL_SET_ID", nullable = false)
	public StudiesDulSet getStudiesDulSet() {
		return this.studiesDulSet;
	}

	public void setStudiesDulSet(StudiesDulSet studiesDulSet) {
		this.studiesDulSet = studiesDulSet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DUL_ID", nullable = false)
	public DulChecklist getDulChecklist() {
		return this.dulChecklist;
	}

	public void setDulChecklist(DulChecklist dulChecklist) {
		this.dulChecklist = dulChecklist;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false, length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 30)
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
