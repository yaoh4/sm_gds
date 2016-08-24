package gov.nih.nci.cbiit.scimgmt.gds.domain;
// Generated Aug 22, 2016 2:13:28 PM by Hibernate Tools 3.4.0.CR1

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
 * ProjectPlanAnswersMapping generated by hbm2java
 */
@Entity
@Table(name = "PROJECT_PLAN_ANSWERS_MAPPING_T")
public class ProjectPlanAnswersMapping implements java.io.Serializable {

	private Long id;
	private PlanAnswerSelection planAnswerSelection;
	private Long projectId;
	private Date createdDate;
	private String createdBy;
	private Date lastChangedDate;
	private String lastChangedBy;

	public ProjectPlanAnswersMapping() {
	}

	public ProjectPlanAnswersMapping(Long id, PlanAnswerSelection planAnswerSelection, Long projectId,
			Date createdDate, String createdBy) {
		this.id = id;
		this.planAnswerSelection = planAnswerSelection;
		this.projectId = projectId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public ProjectPlanAnswersMapping(Long id, PlanAnswerSelection planAnswerSelection, Long projectId,
			Date createdDate, String createdBy, Date lastChangedDate, String lastChangedBy) {
		this.id = id;
		this.planAnswerSelection = planAnswerSelection;
		this.projectId = projectId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastChangedDate = lastChangedDate;
		this.lastChangedBy = lastChangedBy;
	}

	@Id
	@SequenceGenerator(name="projpas_seq_gen", sequenceName="PROJPAS_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projpas_seq_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANSWER_SELECTION_ID", nullable = false)
	public PlanAnswerSelection getPlanAnswerSelection() {
		return this.planAnswerSelection;
	}

	public void setPlanAnswerSelection(PlanAnswerSelection planAnswerSelection) {
		this.planAnswerSelection = planAnswerSelection;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 10, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
