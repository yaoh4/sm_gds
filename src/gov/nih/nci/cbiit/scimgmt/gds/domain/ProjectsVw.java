package gov.nih.nci.cbiit.scimgmt.gds.domain;
// Generated Jul 19, 2016 12:32:23 PM by Hibernate Tools 3.4.0.CR1

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Formula;

/**
 * ProjectsVw generated by hbm2java
 */
@Entity
@Table(name = "PROJECTS_VW")
public class ProjectsVw implements java.io.Serializable {

	private Long id;
	private String docAbbreviation;
	private String programBranch;
	private String parentAccessionNum;
	private Long versionNum;
	private String subprojectFlag;
	private ProjectsVw parentProject;
	private String latestVersionFlag;
	private Long projectGroupId;
	private Long submissionReasonId;
	private String projectSubmissionTitle;
	private String gdsPlanPageStatusCode;
	private String dataSharingExcepStatusCode;
	private String icPageStatusCode;
	private String bsiPageStatusCode;
	private String repositoryPageStatusCode;
	private String subprojectEligibleFlag;
	private String newVersionEligibleFlag;
	private String projectStatusCode;
	
	private String extGrantContractNum;
	private String extPiInstitution;
	private String extPiEmailAddress;
	private String extPiFirstName;
	private String extPiLastName;
	private Long extPdNpnId;
	private String extPdFirstName;
	private String extPdLastName;
	
	private String intGrantContractNum;
	private String intPiInstitution;
	private String intPiEmailAddress;
	private String intPiFirstName;
	private String intPiLastName;
	private Long intPdNpnId;
	private String intPdFirstName;
	private String intPdLastName;
	
	private String piFullNameSort;
	private Long subprojectCount;
	private Long repoCount;
	private boolean expandRepository = false;
	private Date createdDate;
	private String createdBy;
	private Date anticipatedSubmissionDate;
	private Date budgetEndDate;
	private Date projectStartDate;
	private Date projectEndDate;
	
	private String extPdEmailAddress;
	
	private String extPocEmailAddress;
	private String extPocFirstName;
	private String extPocLastName;
	private String intPocEmailAddress;
	private String intPocFirstName;
	private String intPocLastName;
	
	private List<RepositoryStatus> repositoryStatuses = new ArrayList<RepositoryStatus>(0);
	private List<ProjectsVw> subprojects = new ArrayList<ProjectsVw>();
	
	public ProjectsVw() {
	}

	public ProjectsVw(Long id, String docAbbreviation, String parentAccessionNum, Long versionNum,
			String subprojectFlag, ProjectsVw parentProject, String latestVersionFlag, Long projectGroupId,
			Long submissionReasonId, String projectSubmissionTitle, String gdsPlanPageStatusCode,
			String dataSharingExcepStatusCode, String icPageStatusCode, String bsiPageStatusCode,
			String repositoryPageStatusCode, String subprojectEligibleFlag, String projectStatusCode,
			String extGrantContractNum, String extPiInstitution, String extPiEmailAddress, String extPiFirstName,
			String extPiLastName, Long extPdNpnId, String extPdFirstName, String extPdLastName,
			String intGrantContractNum, String intPiInstitution, String intPiEmailAddress, String intPiFirstName,
			String intPiLastName, Long intPdNpnId, String intPdFirstName, String intPdLastName, Long subprojectCount,
			Long repoCount, boolean expandRepository, String createdBy, Date createdDate,
			Date anticipatedSubmissionDate, Date budgetEndDate, Date projectStartDate, Date projectEndDate,
			String extPdEmailAddress, String extPocEmailAddress, String extPocFirstName, String extPocLastName,
			String intPocEmailAddress, String intPocFirstName, String intPocLastName, Date projectPeriodStartDate,
			Date projectPeriodEndDate) {
		this.id = id;
		this.docAbbreviation = docAbbreviation;
		this.parentAccessionNum = parentAccessionNum;
		this.versionNum = versionNum;
		this.subprojectFlag = subprojectFlag;
		this.parentProject = parentProject;
		this.latestVersionFlag = latestVersionFlag;
		this.projectGroupId = projectGroupId;
		this.submissionReasonId = submissionReasonId;
		this.projectSubmissionTitle = projectSubmissionTitle;
		this.gdsPlanPageStatusCode = gdsPlanPageStatusCode;
		this.dataSharingExcepStatusCode = dataSharingExcepStatusCode;
		this.icPageStatusCode = icPageStatusCode;
		this.bsiPageStatusCode = bsiPageStatusCode;
		this.repositoryPageStatusCode = repositoryPageStatusCode;
		this.subprojectEligibleFlag = subprojectEligibleFlag;
		this.projectStatusCode = projectStatusCode;
		this.extGrantContractNum = extGrantContractNum;
		this.extPiInstitution = extPiInstitution;
		this.extPiEmailAddress = extPiEmailAddress;
		this.extPiFirstName = extPiFirstName;
		this.extPiLastName = extPiLastName;
		this.extPdNpnId = extPdNpnId;
		this.extPdFirstName = extPdFirstName;
		this.extPdLastName = extPdLastName;
		this.intGrantContractNum = intGrantContractNum;
		this.intPiInstitution = intPiInstitution;
		this.intPiEmailAddress = intPiEmailAddress;
		this.intPiFirstName = intPiFirstName;
		this.intPiLastName = intPiLastName;
		this.intPdNpnId = intPdNpnId;
		this.intPdFirstName = intPdFirstName;
		this.intPdLastName = intPdLastName;
		this.subprojectCount = subprojectCount;
		this.repoCount = repoCount;
		this.expandRepository = expandRepository;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.anticipatedSubmissionDate = anticipatedSubmissionDate;
		this.budgetEndDate = budgetEndDate;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.extPdEmailAddress = extPdEmailAddress;
		this.extPocEmailAddress = extPocEmailAddress;
		this.extPocFirstName = extPocFirstName;
		this.extPocLastName = extPocLastName;
		this.intPocEmailAddress = intPocEmailAddress;
		this.intPocFirstName = intPocFirstName;
		this.intPocLastName = intPocLastName;
	}

	@Id
	@Column(name = "ID", nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DOC_ABBREVIATION", length = 120)
	public String getDocAbbreviation() {
		return this.docAbbreviation;
	}

	public void setDocAbbreviation(String docAbbreviation) {
		this.docAbbreviation = docAbbreviation;
	}
	
	@Column(name = "PROGRAM_BRANCH", length = 120)
	public String getProgramBranch() {
		return programBranch;
	}

	public void setProgramBranch(String programBranch) {
		this.programBranch = programBranch;
	}


	@Column(name = "PARENT_ACCESSION_NUM", length = 120)
	public String getParentAccessionNum() {
		return this.parentAccessionNum;
	}

	public void setParentAccessionNum(String parentAccessionNum) {
		this.parentAccessionNum = parentAccessionNum;
	}

	@Column(name = "VERSION_NUM", nullable = false, precision = 10, scale = 0)
	public Long getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Long versionNum) {
		this.versionNum = versionNum;
	}

	@Column(name = "SUBPROJECT_FLAG", nullable = false, length = 4)
	public String getSubprojectFlag() {
		return this.subprojectFlag;
	}

	public void setSubprojectFlag(String subprojectFlag) {
		this.subprojectFlag = subprojectFlag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_PROJECT_ID", nullable = false)
	public ProjectsVw getParentProject() {
		return this.parentProject;
	}    
	
	public void setParentProject(ProjectsVw parentProject) {
		this.parentProject = parentProject;
	}

	@Column(name = "LATEST_VERSION_FLAG", length = 4)
	public String getLatestVersionFlag() {
		return this.latestVersionFlag;
	}

	public void setLatestVersionFlag(String latestVersionFlag) {
		this.latestVersionFlag = latestVersionFlag;
	}

	@Column(name = "PROJECT_GROUP_ID", nullable = false, precision = 10, scale = 0)
	public Long getProjectGroupId() {
		return this.projectGroupId;
	}

	public void setProjectGroupId(Long projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	@Column(name = "SUBMISSION_REASON_ID", nullable = false, precision = 10, scale = 0)
	public Long getSubmissionReasonId() {
		return this.submissionReasonId;
	}

	public void setSubmissionReasonId(Long submissionReasonId) {
		this.submissionReasonId = submissionReasonId;
	}

	@Column(name = "EXT_GRANT_CONTRACT_NUM", length = 120)
	public String getExtGrantContractNum() {
		return extGrantContractNum;
	}

	public void setExtGrantContractNum(String extGrantContractNum) {
		this.extGrantContractNum = extGrantContractNum;
	}

	@Column(name = "EXT_PI_INSTITUTION", length = 720)
	public String getExtPiInstitution() {
		return extPiInstitution;
	}

	public void setExtPiInstitution(String extPiInstitution) {
		this.extPiInstitution = extPiInstitution;
	}

	@Column(name = "EXT_PI_EMAIL_ADDRESS", length = 320)
	public String getExtPiEmailAddress() {
		return extPiEmailAddress;
	}

	public void setExtPiEmailAddress(String extPiEmailAddress) {
		this.extPiEmailAddress = extPiEmailAddress;
	}

	@Column(name = "EXT_PI_FIRST_NAME", length = 120)
	public String getExtPiFirstName() {
		return extPiFirstName;
	}

	public void setExtPiFirstName(String extPiFirstName) {
		this.extPiFirstName = extPiFirstName;
	}

	@Column(name = "EXT_PI_LAST_NAME", length = 120)
	public String getExtPiLastName() {
		return extPiLastName;
	}

	public void setExtPiLastName(String extPiLastName) {
		this.extPiLastName = extPiLastName;
	}

	@Column(name = "EXT_PD_NPN_ID", precision = 22, scale = 0)
	public Long getExtPdNpnId() {
		return extPdNpnId;
	}

	public void setExtPdNpnId(Long extPdNpnId) {
		this.extPdNpnId = extPdNpnId;
	}

	@Column(name = "EXT_PD_FIRST_NAME", length = 120)
	public String getExtPdFirstName() {
		return extPdFirstName;
	}

	public void setExtPdFirstName(String extPdFirstName) {
		this.extPdFirstName = extPdFirstName;
	}

	@Column(name = "EXT_PD_LAST_NAME", length = 120)
	public String getExtPdLastName() {
		return extPdLastName;
	}

	public void setExtPdLastName(String extPdLastName) {
		this.extPdLastName = extPdLastName;
	}

	@Column(name = "INT_GRANT_CONTRACT_NUM", length = 120)
	public String getIntGrantContractNum() {
		return intGrantContractNum;
	}

	public void setIntGrantContractNum(String intGrantContractNum) {
		this.intGrantContractNum = intGrantContractNum;
	}

	@Column(name = "INT_PI_INSTITUTION", length = 720)
	public String getIntPiInstitution() {
		return intPiInstitution;
	}

	public void setIntPiInstitution(String intPiInstitution) {
		this.intPiInstitution = intPiInstitution;
	}

	@Column(name = "INT_PI_EMAIL_ADDRESS", length = 320)
	public String getIntPiEmailAddress() {
		return intPiEmailAddress;
	}
	
	public void setIntPiEmailAddress(String intPiEmailAddress) {
		this.intPiEmailAddress = intPiEmailAddress;
	}

	@Column(name = "INT_PI_FIRST_NAME", length = 120)
	public String getIntPiFirstName() {
		return intPiFirstName;
	}

	public void setIntPiFirstName(String intPiFirstName) {
		this.intPiFirstName = intPiFirstName;
	}

	@Column(name = "INT_PI_LAST_NAME", length = 120)
	public String getIntPiLastName() {
		return intPiLastName;
	}

	public void setIntPiLastName(String intPiLastName) {
		this.intPiLastName = intPiLastName;
	}

	@Column(name = "INT_PD_NPN_ID", precision = 22, scale = 0)
	public Long getIntPdNpnId() {
		return intPdNpnId;
	}

	public void setIntPdNpnId(Long intPdNpnId) {
		this.intPdNpnId = intPdNpnId;
	}

	@Column(name = "INT_PD_FIRST_NAME", length = 120)
	public String getIntPdFirstName() {
		return intPdFirstName;
	}

	public void setIntPdFirstName(String intPdFirstName) {
		this.intPdFirstName = intPdFirstName;
	}

	@Column(name = "INT_PD_LAST_NAME", length = 120)
	public String getIntPdLastName() {
		return intPdLastName;
	}
	
	public void setIntPdLastName(String intPdLastName) {
		this.intPdLastName = intPdLastName;
	}
	
	@Column(name = "PI_FULL_NAME_SORT", length = 400)
	public String getPiFullNameSort() {
		return piFullNameSort;
	}

	public void setPiFullNameSort(String piFullNameSort) {
		this.piFullNameSort = piFullNameSort;
	}


	@Column(name = "PROJECT_SUBMISSION_TITLE", nullable = false, length = 400)
	public String getProjectSubmissionTitle() {
		return this.projectSubmissionTitle;
	}

	public void setProjectSubmissionTitle(String projectSubmissionTitle) {
		this.projectSubmissionTitle = projectSubmissionTitle;
	}

	@Column(name = "GDS_PLAN_PAGE_STATUS_CODE", length = 400)
	public String getGdsPlanPageStatusCode() {
		return gdsPlanPageStatusCode;
	}

	public void setGdsPlanPageStatusCode(String gdsPlanPageStatusCode) {
		this.gdsPlanPageStatusCode = gdsPlanPageStatusCode;
	}

	@Column(name = "IC_PAGE_STATUS_CODE", length = 400)
	public String getIcPageStatusCode() {
		return this.icPageStatusCode;
	}

	public void setIcPageStatusCode(String icPageStatusCode) {
		this.icPageStatusCode = icPageStatusCode;
	}

	@Column(name = "BSI_PAGE_STATUS_CODE", length = 400)
	public String getBsiPageStatusCode() {
		return this.bsiPageStatusCode;
	}

	public void setBsiPageStatusCode(String bsiPageStatusCode) {
		this.bsiPageStatusCode = bsiPageStatusCode;
	}

	@Column(name = "DATA_SHARING_EXCEP_STATUS_CODE", length = 400)
	public String getDataSharingExcepStatusCode() {
		return this.dataSharingExcepStatusCode;
	}

	public void setDataSharingExcepStatusCode(String dataSharingExcepStatusCode) {
		this.dataSharingExcepStatusCode = dataSharingExcepStatusCode;
	}

	@Column(name = "REPOSITORY_PAGE_STATUS_CODE", length = 400)
	public String getRepositoryPageStatusCode() {
		return repositoryPageStatusCode;
	}

	public void setRepositoryPageStatusCode(String repositoryPageStatusCode) {
		this.repositoryPageStatusCode = repositoryPageStatusCode;
	}

	@Column(name = "SUBPROJECT_ELIGIBLE_FLAG", length = 4)
	public String getSubprojectEligibleFlag() {
		return this.subprojectEligibleFlag;
	}

	public void setSubprojectEligibleFlag(String subprojectEligibleFlag) {
		this.subprojectEligibleFlag = subprojectEligibleFlag;
	}
	
	@Column(name = "VERSION_ELIGIBLE_FLAG", length = 1)
	public String getNewVersionEligibleFlag(){
		return newVersionEligibleFlag;
	}
	
	public void setNewVersionEligibleFlag(String newVersionEligibleFlag) {
		this.newVersionEligibleFlag = newVersionEligibleFlag;
	}
	
	@Formula(value="(SELECT count(*) FROM projects_t p WHERE p.parent_project_id = id AND p.latest_version_flag = 'Y')")
    public Long getSubprojectCount() {
		return subprojectCount;
	}
	
	public void setSubprojectCount(Long subprojectCount) {
		this.subprojectCount = subprojectCount;
	}
	
	@Formula(value="(SELECT count(*) FROM repository_statuses_t r WHERE r.project_id = id)")
    public Long getRepoCount() {
		return repoCount;
	}
	
	public void setRepoCount(Long repoCount) {
		this.repoCount = repoCount;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public List<RepositoryStatus> getRepositoryStatuses() {
		return this.repositoryStatuses;
	}

	public void setRepositoryStatuses(List<RepositoryStatus> repositoryStatuses) {
		this.repositoryStatuses = repositoryStatuses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="parentProject")
	public List<ProjectsVw> getSubprojects() {
		return subprojects;
	}

	public void setSubprojects(List<ProjectsVw> subprojects) {
		this.subprojects = subprojects;
	}
	
	/**
	 * This method returns Project status.
	 * @return String
	 */
	@Formula(value = "case when (SUBPROJECT_FLAG='Y' or GDS_PLAN_PAGE_STATUS_CODE='COMPLETED' or GDS_PLAN_PAGE_STATUS_CODE IS NULL) and (IC_PAGE_STATUS_CODE='COMPLETED' or IC_PAGE_STATUS_CODE IS NULL ) and (BSI_PAGE_STATUS_CODE='COMPLETED' or BSI_PAGE_STATUS_CODE IS NULL) and REPOSITORY_PAGE_STATUS_CODE ='COMPLETED' then 'COMPLETED' else 'INPROGRESS' end ")
	public String getProjectStatusCode(){
		return projectStatusCode;
	}
	
	public void setProjectStatusCode(String projectStatusCode){
		this.projectStatusCode = projectStatusCode;
	}

	@Transient
	public boolean isExpandRepository() {
		return expandRepository;
	}

	public void setExpandRepository(boolean expandRepository) {
		this.expandRepository = expandRepository;
	}
	
	/**
	 * This method is for displaying the pi full name and hyper link for the email.
	 * @return
	 */
	@Transient
	public String getExtPiFullName(){
		String lastName = extPiLastName;
		String firstName = extPiFirstName;
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
		String email = extPiEmailAddress;
		if(StringUtils.isBlank(fullName)){
			return "";
		}else if(email == null || fullName.trim().length() < 1 || email.trim().length() < 1 ){
			return fullName;
		}
		else{
			return "<a href='mailto:" + email + "'>" + fullName + "</a>";
		}
	}

	/**
	 * This method is for displaying the pi full name and hyper link for the email.
	 * @return
	 */
	@Transient
	public String getIntPiFullName(){
		String lastName = intPiLastName;
		String firstName = intPiFirstName;
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
		String email = intPiEmailAddress;
		if(StringUtils.isBlank(fullName)){
			return "";
		}else if(email == null || fullName.trim().length() < 1 || email.trim().length() < 1 ){
			return fullName;
		}
		else{
			return "<a href='mailto:" + email + "'>" + fullName + "</a>";
		}
	}
	
	@Column(name = "CREATED_BY", nullable = false, length = 120)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	@Column(name = "ANTICIPATED_SUBMISSION_DATE", length = 7)
	public Date getAnticipatedSubmissionDate() {
		return anticipatedSubmissionDate;
	}

	public void setAnticipatedSubmissionDate(Date anticipatedSubmissionDate) {
		this.anticipatedSubmissionDate = anticipatedSubmissionDate;
	}

	@Transient
	public String getAnticipatedSubmissionDateString() {
		if(anticipatedSubmissionDate != null){
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			return df.format(anticipatedSubmissionDate);
		}
		return "";
	}
	
	@Transient
	public String getAnticipatedSubmissionDatePast() {
		if(anticipatedSubmissionDate != null && anticipatedSubmissionDate.before(new Date()))
			return "Y";
		return "N";
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXT_BUDGET_END_DATE", length = 7)
	public Date getBudgetEndDate() {
		return budgetEndDate;
	}

	@Transient
	public String getBudgetEndDateString() {
		if(budgetEndDate != null){
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			return df.format(budgetEndDate);
		}
		return "";
	}
	
	public void setBudgetEndDate(Date budgetEndDate) {
		this.budgetEndDate = budgetEndDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXT_PROJECT_PERIOD_START_DATE", length = 7)
	public Date getProjectStartDate() {
		return projectStartDate;
	}

	@Transient
	public String getProjectStartDateString() {
		if(projectStartDate != null){
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			return df.format(projectStartDate);
		}
		return "";
	}
	
	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXT_PROJECT_PERIOD_END_DATE", length = 7)
	public Date getProjectEndDate() {
		return projectEndDate;
	}

	@Transient
	public String getProjectEndDateString() {
		if(projectEndDate != null){
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			return df.format(projectEndDate);
		}
		return "";
	}
	
	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	
	@Column(name = "EXT_PD_EMAIL_ADDRESS", length = 320)
	public String getExtPdEmailAddress() {
		return extPdEmailAddress;
	}

	public void setExtPdEmailAddress(String extPdEmailAddress) {
		this.extPdEmailAddress = extPdEmailAddress;
	}
	
	/**
	 * This method is for displaying the pd full name and hyper link for the email.
	 * @return
	 */
	@Transient
	public String getExtPdFullName(){
		String lastName = extPdLastName;
		String firstName = extPdFirstName;
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
		String email = extPdEmailAddress;
		if(StringUtils.isBlank(fullName)){
			return "";
		}else if(email == null || fullName.trim().length() < 1 || email.trim().length() < 1 ){
			return fullName;
		}
		else{
			return "<a href='mailto:" + email + "'>" + fullName + "</a>";
		}
	}

	@Column(name = "EXT_POC_EMAIL_ADDRESS", length = 80)
	public String getExtPocEmailAddress() {
		return extPocEmailAddress;
	}

	public void setExtPocEmailAddress(String extPocEmailAddress) {
		this.extPocEmailAddress = extPocEmailAddress;
	}

	@Column(name = "EXT_POC_FIRST_NAME", length = 30)
	public String getExtPocFirstName() {
		return extPocFirstName;
	}

	public void setExtPocFirstName(String extPocFirstName) {
		this.extPocFirstName = extPocFirstName;
	}

	@Column(name = "EXT_POC_LAST_NAME", length = 30)
	public String getExtPocLastName() {
		return extPocLastName;
	}

	public void setExtPocLastName(String extPocLastName) {
		this.extPocLastName = extPocLastName;
	}

	@Column(name = "INT_POC_EMAIL_ADDRESS", length = 80)
	public String getIntPocEmailAddress() {
		return intPocEmailAddress;
	}

	public void setIntPocEmailAddress(String intPocEmailAddress) {
		this.intPocEmailAddress = intPocEmailAddress;
	}

	@Column(name = "INT_POC_FIRST_NAME", length = 30)
	public String getIntPocFirstName() {
		return intPocFirstName;
	}

	public void setIntPocFirstName(String intPocFirstName) {
		this.intPocFirstName = intPocFirstName;
	}

	@Column(name = "INT_POC_LAST_NAME", length = 30)
	public String getIntPocLastName() {
		return intPocLastName;
	}

	public void setIntPocLastName(String intPocLastName) {
		this.intPocLastName = intPocLastName;
	}

}
