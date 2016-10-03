package gov.nih.nci.cbiit.scimgmt.gds.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

/**
 * GdsGrantsContracts generated by hbm2java
 */
@Immutable
@Entity
@Table(name = "GDS_GRANTS_CONTRACTS_MV")
public class GdsGrantsContracts {
	
	private Long applId;
	private String grantContractNum;
	private String lookupGrantContractNum;
	private String applTypeCode;
	private String activityCode;
	private String cayCode;
	

	private String adminPhsOrgCode;
	private Integer serialNum;
	private Integer supportYear;
	private String suffixCode;
	private String projectTitle;
	private String piFirstName;
	private String piLastName;
	private String piEmailAddress;
	private String piInstitution;
	private String pdFirstName;
	private String pdLastName;
	private Date projectPeriodStartDate;
	private Date projectPeriodEndDate;
	private String applClassCode;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public GdsGrantsContracts(){
		
	}
	
	public GdsGrantsContracts(Long applId, String grantContractNum, String lookupGrantContractNum, 
			String applTypeCode, String activityCode, String cayCode, String adminPhsOrgCode,
			Integer serialNum, Integer supportYear, String suffixCode, String projectTitle, String piFirstName,
			String piLastName, String piEmailAddress, String piInstitution, String pdFirstName, String pdLastName,
			Date projectPeriodStartDate, Date projectPeriodEndDate, String applClassCode) {
		super();
		this.applId = applId;
		this.grantContractNum = grantContractNum;
		this.lookupGrantContractNum = lookupGrantContractNum;
		this.applTypeCode = applTypeCode;
		this.activityCode = activityCode;
		this.cayCode=cayCode;
		this.adminPhsOrgCode = adminPhsOrgCode;
		this.serialNum = serialNum;
		this.supportYear = supportYear;
		this.suffixCode = suffixCode;
		this.projectTitle = projectTitle;
		this.piFirstName = piFirstName;
		this.piLastName = piLastName;
		this.piEmailAddress = piEmailAddress;
		this.piInstitution = piInstitution;
		this.pdFirstName = pdFirstName;
		this.pdLastName = pdLastName;
		this.projectPeriodStartDate = projectPeriodStartDate;
		this.projectPeriodEndDate = projectPeriodEndDate;
		this.applClassCode = applClassCode;
	}
	
	@Override
	public String toString() {
		return "{\"applId\":\"" + applId + "\",\"grantContractNum\":\"" + grantContractNum + "\",\"applClassCode\":\"" + applClassCode
				+ "\",\"lookupGrantContractNum\":\"" + lookupGrantContractNum + "\", \"applTypeCode\":\"" + applTypeCode
				+ "\",\"activityCode\":\"" + activityCode + "\",\"cayCode\":\"" + cayCode + "\", \"adminPhsOrgCode\":\"" + adminPhsOrgCode + "\", \"serialNum\":\"" + serialNum
				+ "\", \"supportYear\":\"" + supportYear + "\", \"suffixCode\":\"" + suffixCode + "\", \"projectTitle\":\"" + projectTitle
				+ "\", \"piFirstName\":\"" + piFirstName + "\",\"piLastName\":\"" + piLastName + "\",\"piEmailAddress\":\"" + piEmailAddress
				+ "\",\"piInstitution\":\"" + piInstitution + "\",\"pdFirstName\":\"" + pdFirstName + "\",\"pdLastName\":\"" + pdLastName
				+ "\",\"projectPeriodStartDate\":\"" + (projectPeriodStartDate != null ? dateFormat.format(projectPeriodStartDate) : projectPeriodStartDate) 
				+ "\",\"projectPeriodEndDate\":\"" + (projectPeriodEndDate != null ? dateFormat.format(projectPeriodEndDate) : projectPeriodEndDate)  +"\"}";
	}
	
	@Id
	@Column(name = "APPL_ID", length = 10)
	public Long getApplId() {
		return applId;
	}

	public void setApplId(Long applId) {
		this.applId = applId;
	}
	
	@Column(name = "GRANT_CONTRACT_NUM", length = 19)
	public String getGrantContractNum() {
		return grantContractNum;
	}
	
	public void setGrantContractNum(String grantContractNum) {
		this.grantContractNum = grantContractNum;
	}
	
	@Column(name = "LOOKUP_GRANT_CONTRACT_NUM", length = 19)
	public String getLookupGrantContractNum() {
		return lookupGrantContractNum;
	}

	public void setLookupGrantContractNum(String lookupGrantContractNum) {
		this.lookupGrantContractNum = lookupGrantContractNum;
	}

	@Column(name = "APPL_TYPE_CODE", length = 1)
	public String getApplTypeCode() {
		return applTypeCode;
	}
	
	public void setApplTypeCode(String applTypeCode) {
		this.applTypeCode = applTypeCode;
	}
	
	@Column(name = "ACTIVITY_CODE", length = 3)
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
	@Column(name = "ADMIN_PHS_ORG_CODE", length = 2)
	public String getAdminPhsOrgCode() {
		return adminPhsOrgCode;
	}
	
	public void setAdminPhsOrgCode(String adminPhsOrgCode) {
		this.adminPhsOrgCode = adminPhsOrgCode;
	}
	
	@Column(name = "SERIAL_NUM", length = 6)
	public Integer getSerialNum() {
		return serialNum;
	}
	
	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	
	@Column(name = "SUPPORT_YEAR", length = 2)
	public Integer getSupportYear() {
		return supportYear;
	}
	
	public void setSupportYear(Integer supportYear) {
		this.supportYear = supportYear;
	}
	
	@Column(name = "SUFFIX_CODE", length = 4)
	public String getSuffixCode() {
		return suffixCode;
	}
	
	public void setSuffixCode(String suffixCode) {
		this.suffixCode = suffixCode;
	}
	
	@Column(name = "PROJECT_TITLE", length = 200)
	public String getProjectTitle() {
		return projectTitle;
	}
	
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	
	@Column(name = "PI_FIRST_NAME", length = 30)
	public String getPiFirstName() {
		return piFirstName;
	}
	
	public void setPiFirstName(String piFirstName) {
		if(piFirstName == null) {
			this.piFirstName = "";
		} else {
			this.piFirstName = piFirstName;
		}
	}
	
	@Column(name = "PI_LAST_NAME", length = 30)
	public String getPiLastName() {
		return piLastName;
	}
	
	public void setPiLastName(String piLastName) {
		if(piLastName == null) {
			this.piLastName = "";
		} else {
			this.piLastName = piLastName;
		}
	}
	
	@Column(name = "PI_EMAIL_ADDRESS", length = 80)
	public String getPiEmailAddress() {
		return piEmailAddress;
	}
	
	public void setPiEmailAddress(String piEmailAddress) {
		if(piEmailAddress == null) {
			this.piEmailAddress = "";
		} else {
			this.piEmailAddress = piEmailAddress;
		}
	}
	
	@Column(name = "PI_INSTITUTION", length = 120)
	public String getPiInstitution() {
		return piInstitution;
	}
	
	public void setPiInstitution(String piInstitution) {
		if(piInstitution == null) {
			this.piInstitution = "";
		} else {
			this.piInstitution = piInstitution;
		}
	}
	
	@Column(name = "PD_FIRST_NAME", length = 30)
	public String getPdFirstName() {
		return pdFirstName;
	}
	
	public void setPdFirstName(String pdFirstName) {
		this.pdFirstName = pdFirstName;
	}
	
	@Column(name = "PD_LAST_NAME", length = 30)
	public String getPdLastName() {
		return pdLastName;
	}
	
	public void setPdLastName(String pdLastName) {
		this.pdLastName = pdLastName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROJECT_PERIOD_START_DATE", length = 7)
	public Date getProjectPeriodStartDate() {
		return projectPeriodStartDate;
	}
	
	public void setProjectPeriodStartDate(Date projectPeriodStartDate) {
		this.projectPeriodStartDate = projectPeriodStartDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROJECT_PERIOD_END_DATE", length = 7)
	public Date getProjectPeriodEndDate() {
		return projectPeriodEndDate;
	}
	
	public void setProjectPeriodEndDate(Date projectPeriodEndDate) {
		this.projectPeriodEndDate = projectPeriodEndDate;
	}

	@Column(name = "APPL_CLASS_CODE", length = 3)
	public String getApplClassCode() {
		return applClassCode;
	}
	
	public void setApplClassCode(String applClassCode) {
		this.applClassCode = applClassCode;
	}
}
