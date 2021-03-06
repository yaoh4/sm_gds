/**
 * 
 */
package gov.nih.nci.cbiit.scimgmt.gds.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.xml.sax.SAXException;

import gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Document;
import gov.nih.nci.cbiit.scimgmt.gds.domain.InstitutionalCertification;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Lookup;
import gov.nih.nci.cbiit.scimgmt.gds.domain.NedPerson;
import gov.nih.nci.cbiit.scimgmt.gds.domain.PlanAnswerSelection;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Project;
import gov.nih.nci.cbiit.scimgmt.gds.domain.RepositoryStatus;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Study;
import gov.nih.nci.cbiit.scimgmt.gds.services.FileUploadService;
import gov.nih.nci.cbiit.scimgmt.gds.services.ManageProjectService;

/**
 * @author menons2
 *
 */
public class GdsPageStatusUtil {
	
	private static final Logger logger = LogManager.getLogger(GdsPageStatusUtil.class);
	
	@Autowired
	protected FileUploadService fileUploadService;
	
	@Autowired
	protected ManageProjectService manageProjectService;
	
	@Autowired
	protected NedPerson loggedOnUser;
	
	private static GdsPageStatusUtil instance;
   	private static boolean loaded = false;
	
   	
   	public void init() throws IOException, SAXException {
		if (!loaded) {
			instance = this;
			loaded = true;
		}
		BeanUtilsBean.getInstance().getConvertUtils().register(false, true, -1);
	}
   	
   	
   	/**
	 * Gets the singleton
	 * 
	 * @return GdsPageStatusUtil singleton
	 */
	public static GdsPageStatusUtil getInstance() {
		return instance;
	}
	
	/**
	 * Returns the status of the GDSPlan page if present.
	 * Else returns null
	 * 
	 * @param project
	 * @return String The status if present
	 */
	public String computeGdsPlanStatus(Project project) {
		
		if(ApplicationConstants.SUBMISSION_REASON_NONNIHFUND.equals(project.getSubmissionReasonId())
				|| project.getParentProjectId() != null) {
			//If submission reason is non-NIH funded OR this is 
			//a sub-project, then there is no GDS Plan.
			return null;
		}
		
		//No data has been entered
		if(StringUtils.isBlank(project.getPlanComments()) && 
			CollectionUtils.isEmpty(project.getPlanAnswerSelections())) {
			return ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
		}
		
		Long submissionReasonId = project.getSubmissionReasonId();
		List<Document> exceptionMemo = 
			fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_EXCEPMEMO, project.getId());
			
		List<Document> gdsPlan = 
				fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_GDSPLAN, project.getId());
				
		if(ApplicationConstants.SUBMISSION_REASON_GDSPOLICY.equals(submissionReasonId)
				 || ApplicationConstants.SUBMISSION_REASON_GWASPOLICY.equals(submissionReasonId)) {
		
			//Data sharing exception request not indicated, OR Data sharing exception requested  
			//but not yet approved OR data sharing exception approved but memo not loaded
			if(CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SHARING_EXCEPTION_ID))
				|| 
				(project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SHARING_EXCEPTION_YES_ID) != null
					&& (project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_EXCEPTION_APPROVED_PENDING_ID) != null
						|| CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_EXCEPTION_APPROVED_ID))))
				||
				(project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_EXCEPTION_APPROVED_YES_ID) != null
					&& (CollectionUtils.isEmpty(exceptionMemo) || CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SUBMITTED_ID)))
						)){
				return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
			}
			
			//Data Sharing Plan not loaded or not reviewed
			if(ApplicationConstants.SUBMISSION_REASON_GDSPOLICY.equals(submissionReasonId)
					&& 
					(project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SHARING_EXCEPTION_NO_ID) != null
					  || project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_EXCEPTION_APPROVED_NO_ID) != null
					  || project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SUBMITTED_YES_ID) != null 
					)) {
				if(CollectionUtils.isEmpty(gdsPlan) || project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_GPA_REVIEWED_YES_ID) == null) {
					return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
				}
			}
		}		
		
		//Exception not requested, or requested but not approved, or requested
		//and approved but still data needs to be submitted
		if(ApplicationConstants.SUBMISSION_REASON_NIHFUND.equals(submissionReasonId)
				 || ApplicationConstants.SUBMISSION_REASON_NONNIHFUND.equals(submissionReasonId)
				 || (project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SHARING_EXCEPTION_NO_ID) != null
			|| 	project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_EXCEPTION_APPROVED_NO_ID) != null
			||	project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SUBMITTED_YES_ID) != null)) {
					
			if(CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_SPECIMEN_ID))
					|| CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_TYPE_ID)) 
					|| CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_ACCESS_ID))
					|| CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_REPOSITORY_ID)))  {
				
				return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;			
			}
		}	

		return ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
	}
	
	
	/**
	 * Returns the status of the IC List page if present.
	 * Else returns null.
	 * 
	 * @param project
	 * @return String The status if present
	 */
	public String computeIcListStatus(Project project) {
		
		List<InstitutionalCertification> icList = project.getInstitutionalCertifications();
		List<Study> studies = project.getStudies();
		
		//If user selects "Non-human" only,
		//OR if the answer to "Will there be any data submitted?" is No.
		//there is no IC
		if((project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_SPECIMEN_HUMAN_ID) == null &&
			 project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_SPECIMEN_NONHUMAN_ID) != null)
			||
			(project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SUBMITTED_NO_ID) != null)) {
			return null;
		}
		
		if(ApplicationConstants.FLAG_YES.equals(project.getSubprojectFlag())) {
			Project parentProject=manageProjectService.findById(project.getParentProjectId());
			if((parentProject.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_SPECIMEN_HUMAN_ID) == null &&
					parentProject.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_SPECIMEN_NONHUMAN_ID) != null)) {
					return null;
				}
		}
		// If user selects ONLY the "Other" repository in the "What repository will the data be submitted to?" question GDS plan page, 
		// there is no IC
		Set<PlanAnswerSelection> repoSet = project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_REPOSITORY_ID);
		if(!CollectionUtils.isEmpty(repoSet) && repoSet.size() == 1) {
			PlanAnswerSelection repo = repoSet.iterator().next();
			if(repo.getPlanQuestionsAnswer().getId().longValue() == ApplicationConstants.PLAN_QUESTION_ANSWER_REPOSITORY_OTHER_ID.longValue()) {
				return null;
			}
		}
		
		//There are no studies
		if(ApplicationConstants.FLAG_NO.equals(project.getSubprojectFlag()) && CollectionUtils.isEmpty(studies) ) {
			return ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
		}
		
		//There are studies but no ICSs
		if(CollectionUtils.isEmpty(icList) ) {
			if(ApplicationConstants.FLAG_NO.equals(project.getSubprojectFlag()))
				return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
			else
				return ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
		}
		
		
		if(ApplicationConstants.FLAG_NO.equals(project.getSubprojectFlag())) {
			//All studies have not received ICs
			for(Study study: studies) {
				if(CollectionUtils.isEmpty(study.getInstitutionalCertifications())) {
					return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
				}
			}
				
		
			//All studies have received ICs. So proceed to check if 
			//the ICs are all ok.This check is only for projects
			for(InstitutionalCertification ic: icList) {
				if(ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS.equals(ic.getStatus())) {
					return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
				}
			}
		}
		
		return ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
	}
	
	
	/**
	 * Returns the status of the BSI Study Info page if present.
	 * Else returns null.
	 * 
	 * @param project
	 * @return String The status if present.
	 */
	public String computeBsiStudyInfoStatus(Project project) {
		
		//If the answer to "Will there be any data submitted?" is No, 
		//there is no BSI
		if(project.getPlanAnswerSelectionByAnswerId(ApplicationConstants.PLAN_QUESTION_ANSWER_DATA_SUBMITTED_NO_ID) != null) {
			return null;
		}
		
		List<Document> docs = 
				fileUploadService.retrieveFileByDocType(ApplicationConstants.DOC_TYPE_BSI, project.getId());
		
		if(project.getBsiReviewedId() == null
				&& StringUtils.isBlank(project.getBsiComments()) 
				&& CollectionUtils.isEmpty(docs)) {
			//If no data has been entered
			if(!ApplicationConstants.FLAG_YES.equals(project.getSubprojectFlag())) {
			 if(ApplicationConstants.SUBMISSION_REASON_NONNIHFUND.equals(project.getSubmissionReasonId())) {
				 if(CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_REPOSITORY_ID))){
				 return ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
				 } else {
					 return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
				 }
			 }
			}
			return ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
		}  
		else if(ApplicationConstants.BSI_NA.equals(project.getBsiReviewedId()) && !project.getSubmissionReasonId().equals(ApplicationConstants.SUBMISSION_REASON_NONNIHFUND)) {
			return ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
		}
		else {
			//If GPA has not reviewed or GPA has reviewed but no document has been uploaded
			if(ApplicationConstants.BSI_NO.equals(project.getBsiReviewedId())
					|| (ApplicationConstants.BSI_YES.equals(project.getBsiReviewedId())
							&& CollectionUtils.isEmpty(docs)) || (project.getBsiReviewedId() == null && !CollectionUtils.isEmpty(docs)) || (project.getBsiReviewedId() == null && CollectionUtils.isEmpty(docs))) {
				return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
			} else if(ApplicationConstants.FLAG_NO.equals(project.getSubprojectFlag()) && project.getSubmissionReasonId().equals(ApplicationConstants.SUBMISSION_REASON_NONNIHFUND) && CollectionUtils.isEmpty(project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_REPOSITORY_ID))){
					return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
			}
		}
		
		return ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
	}

	/**
	 * Returns the status of the Repository Status page if present.
	 * Else returns null.
	 * 
	 * @param project
	 * @return String The status if present.
	 */
public String computeRepositoryStatus(Project project) {
		
		// If there are no repositories selected, status should be not started.
		if (project.getPlanAnswerSelectionByQuestionId(ApplicationConstants.PLAN_QUESTION_ANSWER_REPOSITORY_ID).isEmpty()) {
			return ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
		}
		
		List<RepositoryStatus> repositoryStatuses1 = new ArrayList<RepositoryStatus>();
		for(PlanAnswerSelection selection: project.getPlanAnswerSelections()) {
			for(RepositoryStatus repositoryStatus : selection.getRepositoryStatuses()){
				if(project.getId() == repositoryStatus.getProject().getId()) 
				//if(repositoryStatus.getProject().getId() == project.getId())
					repositoryStatuses1.add(repositoryStatus);
			}		
		}
		
		String status = ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
		Boolean studyReleasedYesFlag = true;
		Boolean regStatusNotStartedFlag = true;
		
		List<RepositoryStatus> repositoryStatuses = repositoryStatuses1;
		for(RepositoryStatus repoStatus: repositoryStatuses) {
			
			Lookup registrationStatus = repoStatus.getLookupTByRegistrationStatusId();
			Lookup submissionStatus = repoStatus.getLookupTBySubmissionStatusId();
			Lookup studyReleased = repoStatus.getLookupTByStudyReleasedId();
			
			if(!ApplicationConstants.REGISTRATION_STATUS_NOTSTARTED_ID.equals(registrationStatus.getId())) {
				regStatusNotStartedFlag = false;
			}
			
			if(ApplicationConstants.PROJECT_STUDY_RELEASED_NO_ID.equals(studyReleased.getId())) {
				studyReleasedYesFlag = false;
			}
		}
		
		    if(studyReleasedYesFlag) {
			    status = ApplicationConstants.PAGE_STATUS_CODE_COMPLETED;
		    } else if(regStatusNotStartedFlag) {
			    status = ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED;
		}
		
		if(project.getAnticipatedSubmissionDate() != null &&
				ApplicationConstants.PAGE_STATUS_CODE_NOT_STARTED.equals(status)) {
			return ApplicationConstants.PAGE_STATUS_CODE_IN_PROGRESS;
		}
		
		return status;
	}
	
	
	

}
