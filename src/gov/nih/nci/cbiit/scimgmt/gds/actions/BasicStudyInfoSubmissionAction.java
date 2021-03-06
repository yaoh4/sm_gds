package gov.nih.nci.cbiit.scimgmt.gds.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Document;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Project;
import gov.nih.nci.cbiit.scimgmt.gds.util.DropDownOption;
import gov.nih.nci.cbiit.scimgmt.gds.util.GdsMissingDataUtil;
import gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper;


/**
 * Basic Study Information Page Action Class
 * 
 * @author dinhys
 *
 */
@SuppressWarnings("serial")
public class BasicStudyInfoSubmissionAction extends ManageSubmission {
	
	static Logger logger = LogManager.getLogger(BasicStudyInfoSubmissionAction.class);
	
	private File bsi;

	private String bsiFileName;

	private String bsiContentType;

	private List<Document> bsiFile;

	private String comments;

	private Long bsiReviewedId;
	
	private List<DropDownOption> bsiOptions = new ArrayList<DropDownOption>();

	
	


	/**
	 * Execute method for Basic Study Info.  
	 * Invoked from GDS Plan page Next button or IC page Next button or
	 * Submission Status page Next button or Navigation tab for Basic Study Info or 
	 * Submission Details page edit.
	 * 
	 * The following will be performed.
	 * 1. Retrieve project from database based on project id.
	 * 3. Navigate to Basic Study Info page.
	 * 
	 * @return forward string
	 */
	public String execute() throws Exception {

		logger.debug("execute");
        
		if(StringUtils.isEmpty(getProjectId())) {
			throw new Exception();
		}
		setProject(retrieveSelectedProject());
		
		setUpPageData();
		
        return SUCCESS;
	}

	
	/**
	 * Save Basic Study Info 
	 * Invoked from Basic Study Info page Save button.
	 * 
	 * The following will be performed.
	 * 1. Saves user-selected answers for "Has the GPA reviewed the Basic Study Information?".
	 * 2. Navigate back to Basic Study Info page.
	 * 
	 * @return forward string
	 */
	public String save() throws Exception {
		
		logger.info("Saving Basic Study Info");
		
		setProject(retrieveSelectedProject());
		
		// Save user answer and comments
		getProject().setBsiReviewedId(bsiReviewedId);
		getProject().setBsiComments(comments); 

		if(getProject().getSubprojectFlag().equalsIgnoreCase(ApplicationConstants.FLAG_NO) && getProject().getSubmissionReasonId().equals(ApplicationConstants.SUBMISSION_REASON_NONNIHFUND)) {
			populateSelectedRemovedSets(false); // Re-populate the new and old set for save.
			populatePlanAnswerSelection();
			setupRepositoryStatuses(getProject());
		}
		
		super.saveProject(getProject(), ApplicationConstants.PAGE_CODE_BSI);
		
		setProject(retrieveSelectedProject());
		
		setUpPageData();
		
		addActionMessage(getText("project.save.success"));
		
        return SUCCESS;
	}
	
	/**
	 * Validate Save Basic Study Info
	 * @throws Exception 
	 */
	public void validateSave() throws Exception {
		
		logger.debug("Validate save Basic Study Info");
		
		//Comments cannot be greater than 2000 characters.
		if (!StringUtils.isEmpty(comments)) {
			if (comments.length() > ApplicationConstants.COMMENTS_MAX_ALLOWED_SIZE) {
				this.addActionError(getText("error.comments.size.exceeded"));
			}
		}

		if(hasErrors()) {
			setProject(retrieveSelectedProject());
			bsiOptions = GdsSubmissionActionHelper.getLookupDropDownList(ApplicationConstants.BSI_REVIEWED.toUpperCase());
			bsiFile = fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_BSI, getProject().getId());
		}
		
	}

	/**
	 * Save Basic Study Info and Navigate to Submission Details
	 * Invoked from Basic Study Info Save & Next button.
	 * 
	 * @return forward string
	 */
	public String saveAndNext() throws Exception {
		logger.info("Saving Basic Study Info and navigating to next page.");
		
		save();
		
		if(showPage(ApplicationConstants.PAGE_TYPE_STATUS))
			return ApplicationConstants.PAGE_TYPE_STATUS.toLowerCase();
		
        return SUCCESS;
	}
	
	/**
	 * Validate Save & Next Basic Study Info 
	 * @throws Exception 
	 */
	public void validateSaveAndNext() throws Exception {
		
		validateSave();
	}
	
	/**
	 * Upload Basic Study Info Document
	 * 
	 * @return forward string
	 */
	public String uploadBasicStudyInfo() {
		logger.info("uploadBasicStudyInfo()");
		Document doc = null;
		
		if (!validateUploadFile(bsi, bsiContentType))
			return INPUT;
		
		try {
			doc = fileUploadService.storeFile(Long.valueOf(getProjectId()), ApplicationConstants.DOC_TYPE_BSI, bsi, bsiFileName);
			bsiFile = fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_BSI, Long.valueOf(getProjectId()));
		
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream(getText("error.doc.upload").getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				return INPUT;
			}
			return INPUT;
		}
				
		logger.info("===> docId: " + doc.getId());
		logger.info("===> fileName: " + doc.getFileName());
		logger.info("===> docTitle: " + doc.getDocTitle());
		logger.info("===> uploadDate: " + doc.getUploadedDate());

		return SUCCESS;
	}
	
	/**
	 * Delete Basic Study Info Document
	 * 
	 * @return forward string
	 */
	public String deleteBsiFile() {
		logger.info("deleteBsiFile()");
		
		try {
			if (getDocId() == null) {
				inputStream = new ByteArrayInputStream(
						getText("error.doc.id").getBytes("UTF-8"));

				return INPUT;
			}
			fileUploadService.deleteFile(getDocId());
			bsiFile = fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_BSI, Long.valueOf(getProjectId()));
			
		} catch (UnsupportedEncodingException e) {
			try {
				inputStream = new ByteArrayInputStream(getText("error.doc.delete").getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				return INPUT;
			}
			return INPUT;
		}

		return SUCCESS;
	}

	
	/**
	 * This method sets up all data for Basic Study Info page.
	 * @throws Exception 
	 */
	private void setUpPageData() throws Exception{

		populateAnswersMap();
		
		bsiFile = fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_BSI, getProject().getId());
		
		bsiReviewedId = getProject().getBsiReviewedId();
		
		// Set comments
		comments = getProject().getBsiComments();
		
		bsiOptions = GdsSubmissionActionHelper.getLookupDropDownList(ApplicationConstants.BSI_REVIEWED.toUpperCase());	

	}


	public File getBsi() {
		return bsi;
	}


	public void setBsi(File bsi) {
		this.bsi = bsi;
	}


	public String getBsiFileName() {
		return bsiFileName;
	}


	public void setBsiFileName(String bsiFileName) {
		this.bsiFileName = bsiFileName;
	}


	public String getBsiContentType() {
		return bsiContentType;
	}


	public void setBsiContentType(String bsiContentType) {
		this.bsiContentType = bsiContentType;
	}


	public List<Document> getBsiFile() {
		return bsiFile;
	}


	public void setBsiFile(List<Document> bsiFile) {
		this.bsiFile = bsiFile;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}
     
    public List<DropDownOption> getBsiOptions() {
		return bsiOptions;
	}


	public void setBsiOptions(List<DropDownOption> bsiOptions) {
		this.bsiOptions = bsiOptions;
	}

	public Long getBsiReviewedId() {
		return bsiReviewedId;
	}


	public void setBsiReviewedId(Long bsiReviewedId) {
		this.bsiReviewedId = bsiReviewedId;
	}
	
	public String getPageStatusCode() {
		return super.getPageStatusCode(ApplicationConstants.PAGE_CODE_BSI);
	}
	
	public String computePageStatus(Project project) {
		String status = ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
		
		//Check if document has been loaded
		List<Document> docs = 
				fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_BSI, project.getId());
		
		if(ApplicationConstants.BSI_NA.equals(project.getBsiReviewedId())) {
			
			return ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
		
		}	else if(!ApplicationConstants.BSI_YES.equals(project.getBsiReviewedId()) 
				|| CollectionUtils.isEmpty(docs)) {
			return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
		}
		
		return status;
	}
	
	public String getMissingBsiData() {
		
		setPage(getLookupByCode(ApplicationConstants.PAGE_TYPE, 
			ApplicationConstants.PAGE_CODE_BSI));
		
		Project project = retrieveSelectedProject();
		setMissingDataList(GdsMissingDataUtil.getInstance().getMissingBsiData(project));
		
		return SUCCESS;
	}
}
