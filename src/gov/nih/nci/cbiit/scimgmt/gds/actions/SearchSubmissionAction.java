package gov.nih.nci.cbiit.scimgmt.gds.actions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants;
import gov.nih.nci.cbiit.scimgmt.gds.domain.GdsPd;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Organization;
import gov.nih.nci.cbiit.scimgmt.gds.domain.PlanAnswerSelection;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Project;
import gov.nih.nci.cbiit.scimgmt.gds.domain.ProjectsVw;
import gov.nih.nci.cbiit.scimgmt.gds.domain.RepositoryStatus;
import gov.nih.nci.cbiit.scimgmt.gds.model.ExportRow;
import gov.nih.nci.cbiit.scimgmt.gds.model.Submission;
import gov.nih.nci.cbiit.scimgmt.gds.model.SubmissionSearchCriteria;
import gov.nih.nci.cbiit.scimgmt.gds.model.SubmissionSearchResult;
import gov.nih.nci.cbiit.scimgmt.gds.services.LookupService;
import gov.nih.nci.cbiit.scimgmt.gds.services.ManageProjectService;
import gov.nih.nci.cbiit.scimgmt.gds.services.SearchProjectService;
import gov.nih.nci.cbiit.scimgmt.gds.util.DropDownOption;
import gov.nih.nci.cbiit.scimgmt.gds.util.ExcelExportProc;
import gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper;
import gov.nih.nci.cbiit.scimgmt.gds.util.RepositoryStatusComparator;

/**
 * This class is responsible for searching Projects.
 * @author dinhys
 */
@SuppressWarnings("serial")
public class SearchSubmissionAction extends BaseAction implements ServletRequestAware {

	private static final Logger logger = LogManager.getLogger(SearchSubmissionAction.class);

	private HttpServletRequest request;
	
	@Autowired
	private SearchProjectService searchProjectService;
	
	@Autowired
	private ManageProjectService manageProjectService; // for delete project

	@Autowired
	private LookupService lookupService;
	
	private SubmissionSearchCriteria criteria = new SubmissionSearchCriteria();
	
	private List<DropDownOption> pdList = new ArrayList<DropDownOption>();	

	private List<DropDownOption> submissionFromList = new ArrayList<DropDownOption>();
	
	private List<DropDownOption> submissionReasonList = new ArrayList<DropDownOption>();	
	
	private SubmissionSearchResult jsonResult = new SubmissionSearchResult();
	
    private int draw; // draw counter needed for server side processing data table
    
    private int start; // first record indicator, index of first record to retrieve for pagination
    
    private int length; // Number of records the table can display
    
    private List<RepositoryStatus> repoList = new ArrayList<RepositoryStatus>();
    
    private List<ProjectsVw> subprojectList = new ArrayList<ProjectsVw>();
      
	/**
	 * Navigate to Search Project.
	 * @return forward string
	 */
	public String execute() throws Exception {      

		logger.debug("execute");
		// Populate "Submission from" and "PD" lists
		setUpLists();
		
		return SUCCESS;
	}

	/**
	 * Navigate to Search Parent Project.
	 * @return forward string
	 */
	public String navigateToParentSearch() throws Exception {      

		logger.debug("navigateToParentSearch");
		// Populate "Submission from" and "PD" lists
		setUpLists();
		
		return SUCCESS;
	}
	
	/** 
	 * Search Project.
	 * @return forward string
	 */
	public String search() throws Exception {      

		logger.debug("search");
		
		logger.debug(criteria.toString());
		
		populateCriteria();
		
        jsonResult = searchProjectService.search(criteria);
        if(jsonResult == null) {
        	jsonResult = new SubmissionSearchResult();
        	jsonResult.setData(new ArrayList<Submission>());
        }
		jsonResult.setDraw(getDraw());
		
		logger.debug("total records = " + jsonResult.getRecordsTotal());
		
		logger.debug("end - search");
		
		return SUCCESS;
		
	}
	
	private void populateCriteria() {

		if(criteria.getSubmissionFromId() == ApplicationConstants.SEARCH_MY_SUBMISSIONS) {
			criteria.setPdNpnId(loggedOnUser.getUserRole().getPdNpnId());
		}
		if(criteria.getSubmissionFromId() == ApplicationConstants.SEARCH_SUBMISSION_FROM_MYDOC) {
			List<Organization> docListFromDb = (List<Organization>) lookupService.getDocList(ApplicationConstants.DOC_LIST.toUpperCase());	
			criteria.setDoc(GdsSubmissionActionHelper.getLoggedonUsersDOC(docListFromDb,loggedOnUser.getNihsac()));	
		}
		if(StringUtils.isNotBlank(criteria.getPdFirstAndLastName())) {
			//Parse Last, First names
			criteria.setPdLastName(StringUtils.substringBefore(criteria.getPdFirstAndLastName(), ","));
			criteria.setPdFirstName(StringUtils.substringBefore(StringUtils.substringAfter(criteria.getPdFirstAndLastName(), ", "), " "));
		}

		// Provide pagination information
		if(criteria.getSortBy() == null)
			criteria.setSortBy(getSortByProperty(request));
		if(criteria.getSortDir() == null)
			criteria.setSortDir(getSortByDirection(request));
		criteria.setStart(start);
		criteria.setLength(length);
        
        logger.debug("Fetching " + length + " record(s) from index " + start);
        logger.debug("Sort by: " + criteria.getSortBy());
        logger.debug("Sort direction: " + criteria.getSortDir());
        
	}

	/** 
	 * Validate Search Project.
	 * @return forward string
	 */
	public void validateSearch() throws Exception {      

		logger.debug("validateSearch");
		
		if(criteria == null || criteria.isBlank()) {
			jsonResult.setError(getText("error.search.criteria.required"));
			addActionError(getText("error.search.criteria.required"));
		}
		
		if(StringUtils.isNotBlank(criteria.getGrantContractNum()) && criteria.getGrantContractNum().length() < ApplicationConstants.GRANT_CONTRACT_NUM_MIN_SIZE){
			jsonResult.setError(getText("grantContractNum.min.size.error"));
			addActionError(getText("grantContractNum.min.size.error")); 
		}

		logger.debug("end - validateSearch");
	}
	
	/** 
	 * Export to excel.
	 * @return null
	 */
	public String export() throws Exception {      

		logger.debug("export");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		length = -1; // Fetch all data
		populateCriteria();

        jsonResult = searchProjectService.search(criteria);
        
		ExcelExportProc proc = new ExcelExportProc();
		List<ExportRow> rows = prepareExportData();
		proc.setData(rows);
		proc.doExportExcel(request, response);
		
		logger.debug("end - export");
		
		return null;
		
	}
	
	private List<ExportRow> prepareExportData() {
	       // Prepare rows of the export data
	     	List<ExportRow> rows = new ArrayList<>();
	     		
			// Prepare headers
	     	ExportRow exportRow = new ExportRow();
			List<String> header = new ArrayList<String>();
			header.add("Project Submission Title");
			header.add("Intramural/Grant/Contract");
			header.add("Principal Investigator Name");
			header.add("Principal Investigator Email");
			header.add("Genomic DSP");
			header.add("GDSP Exception");
			header.add("IC");
			header.add("BSI");
			header.add("Submission Status");
			exportRow.setRow(header);
			exportRow.setHeader(true);
			rows.add(exportRow);
			
			// Prepare data
			if(jsonResult != null) {
				for(Submission submission : jsonResult.getData()) {
					exportRow = new ExportRow();
					List<String> row = new ArrayList<>();
					row.add(submission.getProjectSubmissionTitle());
					row.add(submission.getExtGrantContractNum());
					row.add((submission.getExtPiLastName() == null ? "" : submission.getExtPiLastName() + ", " + submission.getExtPiFirstName()));
					row.add(submission.getExtPiEmailAddress());
					row.add((StringUtils.isBlank(submission.getGdsPlanPageStatusCode()) ? "N/A" : lookupService.getLookupByCode(ApplicationConstants.PAGE_STATUS_TYPE, submission.getGdsPlanPageStatusCode()).getDescription()));
					row.add((StringUtils.isBlank(submission.getDataSharingExcepStatusCode()) ? "N/A" : lookupService.getLookupByCode(ApplicationConstants.PAGE_STATUS_TYPE, submission.getDataSharingExcepStatusCode()).getDescription()));
					row.add((StringUtils.isBlank(submission.getIcPageStatusCode()) ? "N/A" : lookupService.getLookupByCode(ApplicationConstants.PAGE_STATUS_TYPE, submission.getIcPageStatusCode()).getDescription()));
					row.add((StringUtils.isBlank(submission.getBsiPageStatusCode()) ? "N/A" : lookupService.getLookupByCode(ApplicationConstants.PAGE_STATUS_TYPE, submission.getBsiPageStatusCode()).getDescription()));
					row.add((submission.getRepoCount() == null || StringUtils.isBlank(submission.getRepositoryPageStatusCode()) ? "N/A" : lookupService.getLookupByCode(ApplicationConstants.PAGE_STATUS_TYPE, submission.getRepositoryPageStatusCode()).getDescription()));
					exportRow.setRow(row);
					rows.add(exportRow);
				
				}
			}

			return rows;
	}
	
	/**
	 * Get all project ids.
	 * @return List
	 */
	public List<Long> getAllProjectIds(){
		return searchProjectService.getAllProjectIds();
	}

	
	/**
	 * Delete Project
	 * @throws Exception 
	 */
	public String deleteProject() throws Exception {
		logger.debug("deleteProject()");

		manageProjectService.delete(Long.valueOf(getProjectId()));
		inputStream = new ByteArrayInputStream(getText("project.delete.success").getBytes("UTF-8"));
		
		return SUCCESS;
	}
	
	/**
	 * Retrieve the project based on the projectId in order to get the repository information
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getRepoInfo() {
	
		String projectId  = getProjectId();
		if(StringUtils.isNotBlank(projectId)) {
			Project project = manageProjectService.findById(Long.valueOf(projectId));
			for(PlanAnswerSelection selection: project.getPlanAnswerSelections()) {
				for(RepositoryStatus repositoryStatus : selection.getRepositoryStatuses()){
					if(repositoryStatus.getProject().getId().longValue() == Long.valueOf(projectId).longValue())
						project.getRepositoryStatuses().add(repositoryStatus);
				}		
			}
			Collections.sort(project.getRepositoryStatuses(),new RepositoryStatusComparator());
			setRepoList(project.getRepositoryStatuses());
		}
		
		return SUCCESS;
	}
	
	/**
	 * Retrieve the subprojects based on the parent projectId and also get the repository information for the subproject
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSubprojects() {
	
		String parentProjectId  = getProjectId();
		if(StringUtils.isNotBlank(parentProjectId)) {
			subprojectList = searchProjectService.getSubprojects(Long.valueOf(parentProjectId));
			for(ProjectsVw subproject: subprojectList) {
				Collections.sort(subproject.getRepositoryStatuses(),new RepositoryStatusComparator());
				setRepoList(subproject.getRepositoryStatuses());
				if(StringUtils.isNotBlank(criteria.getAccessionNumber())) {
					for(RepositoryStatus r: subproject.getRepositoryStatuses()) {
						if(StringUtils.containsIgnoreCase(r.getAccessionNumber(), criteria.getAccessionNumber())) {
							subproject.setExpandRepository(true);
							break;
						}
					}
				}
			}
		}
		
		return SUCCESS;
	}
	
	/** 
	 * Validate Delete Project
	 * @return forward string
	 */
	public void validateDeleteProject() throws Exception {      

		logger.debug("validateDeleteProject");
		
		if(StringUtils.isBlank(getProjectId())) {
			inputStream = new ByteArrayInputStream(getText("project.delete.error").getBytes("UTF-8"));
			addActionError(getText("project.delete.error"));
		}

		logger.debug("end - validateDeleteProject");
	}
	
	/**
	 * This method sets up the "Submission from" and "PD" lists for Search
	 */
	private void setUpLists(){
		
		// initialize criteria
		String selectedTypeOfProject = criteria.getSelectedTypeOfProject();
		criteria = new SubmissionSearchCriteria();
		criteria.setSelectedTypeOfProject(selectedTypeOfProject);
		
		logger.debug("Setting up page lists.");
		List<GdsPd> pdListDb = lookupService.getPdList(ApplicationConstants.PD_LIST);
		if(pdList.isEmpty()){
			DropDownOption option = new DropDownOption();
			option.setOptionKey("");
			option.setOptionValue("Select Program Director");
			pdList.add(option);
			
			for(GdsPd pd: pdListDb) {
				option = new DropDownOption();
				option.setOptionKey(pd.getNpnId().toString());
				option.setOptionValue(pd.getPdFullNameDescrip());
				pdList.add(option);
			}
		}
		
		if(submissionFromList.isEmpty()){			
			submissionFromList = GdsSubmissionActionHelper.getLookupDropDownList(ApplicationConstants.SEARCH_SUBMISSION_FROM.toUpperCase());
			
			if(!StringUtils.equals(loggedOnUser.getUserRole().getGdsRoleCode(), ApplicationConstants.ROLE_GPA_CODE)) {
				submissionFromList.remove(0);
				if(!StringUtils.equals(loggedOnUser.getUserRole().getPdFlag(),"Y")) {
					submissionFromList.remove(0);
					if(!StringUtils.equals(loggedOnUser.getUserRole().getGdsRoleCode(), ApplicationConstants.ROLE_EDIT_USER_CODE)) {
						submissionFromList.remove(0);
					}
				}
			} else {
				if(!StringUtils.equals(loggedOnUser.getUserRole().getPdFlag(),"Y")) {
					submissionFromList.remove(1);
				}
			}
		}		

		if(submissionReasonList.isEmpty()){			
			submissionReasonList = GdsSubmissionActionHelper.getLookupDropDownList(ApplicationConstants.PROJECT_SUBMISSION_REASON_LIST.toUpperCase());	
		}
	}
	
    public String getSortByProperty(HttpServletRequest request) {
        String sSortProperty = null;
        String sSortIndex = request.getParameter("order[0][column]");
        if (sSortIndex != null && !sSortIndex.isEmpty()) {
            sSortProperty = request.getParameter("columns[" + sSortIndex + "][data]");
        }
        return sSortProperty;
    }

    public String getSortByDirection(HttpServletRequest request) {
        return request.getParameter("order[0][dir]");
    }
	
	public SubmissionSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SubmissionSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public List<DropDownOption> getSubmissionFromList() {
		return submissionFromList;
	}

	public void setSubmissionFromList(List<DropDownOption> submissionFromList) {
		this.submissionFromList = submissionFromList;
	}

	public List<DropDownOption> getPdList() {
		return pdList;
	}
	
	public List<DropDownOption> getSubmissionReasonList() {
		return submissionReasonList;
	}
	
	public void setSubmissionReasonList(List<DropDownOption> submissionReasonList) {
		this.submissionReasonList = submissionReasonList;
	}

	public void setPdList(List<DropDownOption> pdList) {
		this.pdList = pdList;
	}

	public SubmissionSearchResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(SubmissionSearchResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<RepositoryStatus> getRepoList() {
		return repoList;
	}

	public void setRepoList(List<RepositoryStatus> repoList) {
		this.repoList = repoList;
	}

	public List<ProjectsVw> getSubprojectList() {
		return subprojectList;
	}

	public void setSubprojectList(List<ProjectsVw> subprojectList) {
		this.subprojectList = subprojectList;
	}
}
