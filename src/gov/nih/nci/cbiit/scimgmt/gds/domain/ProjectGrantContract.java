package gov.nih.nci.cbiit.scimgmt.gds.domain;
// Generated Mar 28, 2016 10:25:57 AM by Hibernate Tools 4.0.0

import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;



/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "PROJECT_GRANTS_CONTRACTS_T")
public class ProjectGrantContract implements java.io.Serializable {

	private Long id;
	private Long applId;
	private String applClassCode;
	private String createdBy;
	private Date createdDate;
	private String dataLinkFlag;
	private String grantContractNum;
	private String grantContractType;
	private String lastChangedBy;
	private Date lastChangedDate;
	private String pdFirstName;
	private String pdLastName;
	private String piEmailAddress;
	private String piFirstName;
	private String piInstitution;
	private String piLastName;
	private String pocEmailAddress;
	private String pocFirstName;
	private String pocLastName;
	private String primaryGrantContractFlag;
	private Date projectEndDate;
	private Date projectStartDate;
	private String projectTitle;
	
	private Project project;
	private String activityCode;
	private String cayCode;
	private NedPerson createdByPerson;
	private NedPerson lastChangedByPerson;
	

	@Override
	public String toString() {
		return "Project [id=" + id + ", grantContractNum=" + grantContractNum + ", projectTitle="
				+ projectTitle + ", piInstitution=" + piInstitution
				+ ", piEmailAddress=" + piEmailAddress + ", projectStartDate=" + projectStartDate
				+ ", projectEndDate=" + projectEndDate
				+ ", createdBy=" + createdBy + ", lastChangedBy=" + lastChangedBy
				+ ", primaryGrantContractFlag =" + primaryGrantContractFlag 
				+ ", applClassCode=" + applClassCode + ", piFirstName=" + piFirstName
				+ ", piLastName=" + piLastName + ", pocFirstName=" + pocFirstName + ", pocLastName=" + pocLastName
				+ ", pdFirstName=" + pdFirstName + ", pdLastName=" + pdLastName + ", pageStatuses="
				+ ", applId=" + applId +  ", dataLinkFlag=" + dataLinkFlag +"]";
	}


	public ProjectGrantContract() {
	}
	
	public ProjectGrantContract(String grantContractType, String primaryGrantContractFlag) {
		this.grantContractType = grantContractType;
		this.primaryGrantContractFlag = primaryGrantContractFlag;
	}
	
	public ProjectGrantContract(String grantContractNum, String projectTitle,
			String programBranch, String applicationNum, String piInstitution,
			String piEmailAddress, Date projectStartDate, Date projectEndDate,
			String createdBy, String lastChangedBy, String applClassCode,
			String piFirstName, String piLastName, String pocFirstName, 
			String pocLastName, String pdFirstName, String pdLastName, 
			Long applId, String submissionTitle, String dataLinkFlag,
			String primaryGrantContractFlag) {
		this.grantContractNum = grantContractNum;
		this.projectTitle = projectTitle;
		this.piInstitution = piInstitution;
		this.piEmailAddress = piEmailAddress;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.createdBy = createdBy;
		this.lastChangedBy = lastChangedBy;
		this.applClassCode = applClassCode;
		this.piFirstName = piFirstName;
		this.piLastName = piLastName;
		this.pocFirstName = pocFirstName;
		this.pocLastName = pocLastName;
		this.pdFirstName = pdFirstName;
		this.pdLastName = pdLastName;
		this.applId = applId;
		this.dataLinkFlag = dataLinkFlag;
		this.primaryGrantContractFlag = primaryGrantContractFlag;
	}
	

	@Id
	@SequenceGenerator(name="project_seq_gen", sequenceName="PROJ_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "PROJECT_TITLE", length = 400)
	public String getProjectTitle() {
		return this.projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = StringUtils.trim(projectTitle);
	}

	
	@Column(name = "GRANT_CONTRACT_NUM", length = 120)
	public String getGrantContractNum() {
		return this.grantContractNum;
	}

	public void setGrantContractNum(String applicationNum) {
		if(!StringUtils.isEmpty(applicationNum)) {
		this.grantContractNum = StringUtils.trim(applicationNum.replaceAll("\\s",""));
		} else {
			this.grantContractNum = StringUtils.trim(applicationNum);
		}
	}

	@Column(name = "GRANT_CONTRACT_TYPE", length = 30)
	public String getGrantContractType() {
		return grantContractType;
	}


	public void setGrantContractType(String grantContractType) {
		this.grantContractType = grantContractType;
	}


	@Column(name = "PI_INSTITUTION", length = 480)
	public String getPiInstitution() {
		return this.piInstitution;
	}

	public void setPiInstitution(String piInstitution) {
		this.piInstitution = StringUtils.trim(piInstitution);
	}
	
	@Column(name = "PI_EMAIL_ADDRESS", length = 320)
	public String getPiEmailAddress() {
		return this.piEmailAddress;
	}

	public void setPiEmailAddress(String piEmailAddress) {
		this.piEmailAddress = StringUtils.trim(piEmailAddress);
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROJECT_START_DATE", length = 7)
	public Date getProjectStartDate() {
		return this.projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	
	@Transient
	public String getFormattedStartDate() {
		if(projectStartDate != null) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			return format.format(projectStartDate);
		}
		return "";
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROJECT_END_DATE", length = 7)
	public Date getProjectEndDate() {
		return this.projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	
	@Transient
	public String getFormattedEndDate() {
		if(projectEndDate != null) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			return format.format(projectEndDate);
		}
		return "";
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY", nullable=true, insertable=false, updatable=false)
	public NedPerson getCreatedByPerson() {
		return this.createdByPerson;
	}
	
	public void setCreatedByPerson(NedPerson person) {
		this.createdByPerson = person;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAST_CHANGED_BY", nullable=true, insertable=false, updatable=false)
	public NedPerson getLastChangedByPerson() {
		return this.lastChangedByPerson;
	}
	
	public void setLastChangedByPerson(NedPerson person) {
		this.lastChangedByPerson = person;
	}
	
	
	@Transient
	public String getUpdatedBy() {
		//If lastChangedBy present return that info
		if(lastChangedBy != null) {
			try {
				return getLastChangedByPerson().getFullName();
			} catch (Exception e) {
				//Person may be left, so return the stored user id
				return lastChangedBy;
			}
		}
		
		//Else get createdBy info
		try {
			return getCreatedByPerson().getFullName();
		} catch (Exception e) {
		
		return createdBy;
		}
	}

	@Column(name = "PI_FIRST_NAME", length = 120)
	public String getPiFirstName() {
		return this.piFirstName;
	}

	public void setPiFirstName(String piFirstName) {
		this.piFirstName = StringUtils.trim(piFirstName);
	}

	@Column(name = "PI_LAST_NAME", length = 120)
	public String getPiLastName() {
		return this.piLastName;
	}

	public void setPiLastName(String piLastName) {
		this.piLastName = StringUtils.trim(piLastName);
	}

	@Column(name = "POC_FIRST_NAME", length = 120)
	public String getPocFirstName() {
		return this.pocFirstName;
	}

	public void setPocFirstName(String pocFirstName) {
		this.pocFirstName = StringUtils.trim(pocFirstName);
	}

	@Column(name = "POC_LAST_NAME", length = 120)
	public String getPocLastName() {
		return this.pocLastName;
	}

	public void setPocLastName(String pocLastName) {
		this.pocLastName = StringUtils.trim(pocLastName);
	}

	@Column(name = "POC_EMAIL_ADDRESS", length = 320)
	public String getPocEmailAddress() {
		return pocEmailAddress;
	}

	public void setPocEmailAddress(String pocEmailAddress) {
		this.pocEmailAddress = StringUtils.trim(pocEmailAddress);
	}

	@Column(name = "PD_FIRST_NAME", length = 120)
	public String getPdFirstName() {
		return this.pdFirstName;
	}

	public void setPdFirstName(String pdFirstName) {
		this.pdFirstName = StringUtils.trim(pdFirstName);
	}

	@Column(name = "PD_LAST_NAME", length = 120)
	public String getPdLastName() {
		return this.pdLastName;
	}

	public void setPdLastName(String pdLastName) {
		this.pdLastName = StringUtils.trim(pdLastName);
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_CHANGED_DATE", length = 7)
	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}
	
	@Transient
	public Date getUpdatedDate() {
		if(lastChangedDate != null) {
			return lastChangedDate;
		}
		
		return createdDate;
	}

	
	@Column(name = "APPL_ID", length = 10)
	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}


	@Column(name = "DATA_LINK_FLAG", length = 1)
	public String getDataLinkFlag() {
		return dataLinkFlag;
	}

	public void setDataLinkFlag(String dataLinkFlag) {
		this.dataLinkFlag = dataLinkFlag;
	}

	@Column(name = "PRIMARY_GRANT_CONTRACT_FLAG", length = 1)
	public String getPrimaryGrantContractFlag() {
		return primaryGrantContractFlag;
	}


	public void setPrimaryGrantContractFlag(String primaryGrantContractFlag) {
		this.primaryGrantContractFlag = primaryGrantContractFlag;
	}


	@Column(name = "APPL_CLASS_CODE", length = 3)
	public String getApplClassCode() {
		return applClassCode;
	}
	
	public void setApplClassCode(String applClassCode) {
		this.applClassCode = applClassCode;
	}

	
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", nullable = false)
	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	/**
	 * This method is for displaying the pi full name and hyper link for the email.
	 * @return
	 */
	@Transient
	public String getPiFullName(){
		String lastName = piLastName;
		String firstName = piFirstName;
		String fullName = "";
		if(lastName != null && lastName.length() > 0){
			fullName = fullName + lastName;
		}
		if(lastName != null && lastName.length() > 0 && firstName != null && firstName.length() > 0){
			fullName = fullName + ", ";
		}
		if(firstName != null && firstName.length() > 0){
			fullName = fullName + firstName;
		}
		String email = piEmailAddress;
		if(StringUtils.isBlank(fullName)){
			return "";
		}else if(email == null || fullName.trim().length() < 1 || email.trim().length() < 1 ){
			return fullName;
		}
		else{
			return "<a href='mailto:" + email + "'>" + fullName + "</a>";
		}
	}

	
	@Transient 
	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	
	@Column(name = "CAY_CODE")
	public String getCayCode() {
		return cayCode;
	}

	public void setCayCode(String cayCode) {
		this.cayCode = cayCode;
	}
}
