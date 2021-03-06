package gov.nih.nci.cbiit.scimgmt.gds.services;

import java.util.List;

import gov.nih.nci.cbiit.scimgmt.gds.domain.GdsGrantsContracts;
import gov.nih.nci.cbiit.scimgmt.gds.domain.InstitutionalCertification;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Project;
import gov.nih.nci.cbiit.scimgmt.gds.domain.ProjectsVw;
import gov.nih.nci.cbiit.scimgmt.gds.domain.RepositoryStatus;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Study;

public interface ManageProjectService {

	/**
	 * Inserts or Updates the Project
	 * 
	 * @param project
	 * @return saved Project
	 */
	public Project saveOrUpdate(Project project);

	/**
	 * Deletes the Project given an ID
	 * 
	 * @param projectId
	 */
	public void delete(Long projectId);
	
	/**
	 * Deletes the sub-projects with Parent Id
	 * 
	 */
	public void deleteSubProjects(Long parentId);

	/**
	 * Retrieve Project given an ID
	 * 
	 * @param projectId
	 * @return Project
	 */
	public Project findById(Long projectId);
	
	
	/**
	 * Retrieve ProjectsVw given an ID
	 * 
	 * @param projectId
	 * @return ProjectsVw
	 */
	public ProjectsVw findProjectsVwById(Long projectId);
	
	
	/**
	 * Retrieve RepositoryStatus given an ID
	 * 
	 * @param repoId
	 * @return RepositoryStatus
	 */
	public RepositoryStatus findRepositoryById(Long repoId);
	
	/**
	 * Retrieve IC given an ID
	 * 
	 * @param icId
	 * @return InstitutionCertification
	 */
	public InstitutionalCertification findIcById(Long icId);
	
	
	
	
	/**
	 * Inserts or Updates the IC
	 * 
	 * @param IC
	 * @return saved IC
	 */
	public InstitutionalCertification saveOrUpdateIc(InstitutionalCertification ic);
	
     /**
      * inserts and updates study
      * @param study
      * @return saved study
      */

	public Study saveStudy(Study study);

	/**
	 * Deletes the IC given an ID and parent project
	 * 
	 * @param icId
	 * @param project
	 */
	public boolean deleteIc(Long icId, Project project);

      /**
	   * Deletes the Study given an ID and parent project
	   * 
	   * @param studyId
	   * @param project
	   */
	public boolean deleteStudy(Long studyId, Project project);
	
	/**
	 * This method retrieves Intramural / Grant / Contract List
	 * @return
	 */
	public List<GdsGrantsContracts> getGrantOrContractList(String grantContractNum,String applClassCode);
	
	/**
	 * This method returns grantContract for given applId
	 * @param applId
	 * @return
	 */
	public GdsGrantsContracts getGrantOrContract(Long applId);
		
	/**
	 * This method retrieves list of already linked submissions for a given grant.
	 * 
	 * @param grantContractNum
	 * @return
	 */
	public List<ProjectsVw> getPrevLinkedSubmissionsForGrant(String grantContractNum, String projectId);

	
	/**
	 * This method retrieves list of program/branch for the doc.
	 * 
	 * @param doc
	 * @return
	 */
	public List<String> getSubOrgList(String doc);

	/**
	 * Retrieve all Sub-projects based on parent project ID.
	 * @param parentProjectId
	 * @return List<Project>
	 */
	public List<Project> getSubprojects(Long parentProjectId);
	
	
	/**
	 * Retrieve Sub-projects based on parent project ID.
	 * @param parentProjectId
	 * @param latestVersionOnly
	 * @return List<Project>
	 */
	public List<Project> getSubprojects(Long parentProjectId, boolean latestVersionOnly);
	
	/**
	 * Retrieve Sub-project views based on parent project ID.
	 * @param parentProjectId
	 * @return List<ProjectsVw>
	 */
	public List<ProjectsVw> getSubprojectsVw(Long parentProjectId);
	
	/**
	 * Retrieve versions based on project group ID.
	 * @param projectGroupId
	 * @param parentProjectId
	 * @return List<Project>
	 */
	public List<Project> getVersions(Long projectGroupId, Long parentProjectId);
	
	/**
	 * Retrieve the latest version of the project within
	 * the given project group ID.
	 * @param projectGroupId
	 * @return Project
	 */
	public Project getCurrentLatestVersion(Long projectGroupId);
}
