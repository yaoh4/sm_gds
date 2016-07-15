package gov.nih.nci.cbiit.scimgmt.gds.domain;
// Generated Mar 28, 2016 10:25:57 AM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.CollectionUtils;

import gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper;

/**
 * StudiesDulSet generated by hbm2java
 */
@Entity
@Table(name = "STUDIES_DUL_SET_T")
public class StudiesDulSet implements java.io.Serializable {

	private Long id;
	private Study study;
	private String createdBy;
	private String lastChangedBy;
	private List<DulChecklistSelection> dulChecklistSelections = new ArrayList<DulChecklistSelection>();
	private String displayId = null;

	public StudiesDulSet() {
	}

	
	public StudiesDulSet(Long id, Study study, String createdBy) {
		this.id = id;
		this.study = study;
		this.createdBy = createdBy;
	}

	public StudiesDulSet(Long id, Study study, String createdBy,
			String lastChangedBy, List<DulChecklistSelection> dulChecklistSelections) {
		this.id = id;
		this.study = study;
		this.createdBy = createdBy;
		this.lastChangedBy = lastChangedBy;
		this.dulChecklistSelections = dulChecklistSelections;
	}

	@Id
	@SequenceGenerator(name="stud_seq_gen", sequenceName="STUD_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stud_seq_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDY_ID", nullable = false)
	public Study getStudy() {
		return this.study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 120)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "LAST_CHANGED_BY", length = 120)
	public String getLastChangedBy() {
		return this.lastChangedBy;
	}

	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studiesDulSet", orphanRemoval=true)
	@Cascade({CascadeType.ALL})
	public List<DulChecklistSelection> getDulChecklistSelections() {
		return this.dulChecklistSelections;
	}

	public void setDulChecklistSelections(List<DulChecklistSelection> dulChecklistSelections) {
		this.dulChecklistSelections = dulChecklistSelections;
	}
	
	public void addDulChecklistSelection(DulChecklistSelection dulChecklistSelection) {
		this.dulChecklistSelections.add(dulChecklistSelection);
	}

	
	@Transient
	public String getAdditionalText() {
		String additionalText = null;
		List<DulChecklistSelection> dulSelections = getDulChecklistSelections();
		if(!CollectionUtils.isEmpty(dulSelections)) {
			for(DulChecklistSelection dulSelection: dulSelections) {
				Long parentDulId = dulSelection.getDulChecklist().getParentDulId();
				if( parentDulId == null) {
					//We have encountered a record without parentId, so it is the parent itself
					additionalText = dulSelection.getOtherText();
					break;
				} 
			}
		}
			
		return additionalText;
	}


	@Transient
	public DulChecklist getParentDulChecklist() {
		DulChecklist parentDulChecklist = null;
		List<DulChecklistSelection> dulSelections = getDulChecklistSelections();
		if(!CollectionUtils.isEmpty(dulSelections)) {
			DulChecklist dulChecklist = dulSelections.get(0).getDulChecklist();
			Long parentDulId = dulChecklist.getParentDulId();
			if( parentDulId != null) {
				parentDulChecklist = GdsSubmissionActionHelper.getDulChecklist(parentDulId);
			} else {
				//We add a row into the DUL_CHECK;IST_SELECTIONS_T for the parent itself also,
				parentDulChecklist = dulChecklist;
			}
		}
		
		return parentDulChecklist;
	}


	@Transient
	public String getDisplayId() {
		return displayId;
	}


	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

}
