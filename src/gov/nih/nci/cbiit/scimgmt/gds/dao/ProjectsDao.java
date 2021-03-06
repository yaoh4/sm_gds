package gov.nih.nci.cbiit.scimgmt.gds.dao;
// Generated Mar 7, 2016 1:12:03 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants;
import gov.nih.nci.cbiit.scimgmt.gds.domain.GdsGrantsContracts;
import gov.nih.nci.cbiit.scimgmt.gds.domain.NedPerson;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Organization;
import gov.nih.nci.cbiit.scimgmt.gds.domain.PlanAnswerSelection;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Project;
import gov.nih.nci.cbiit.scimgmt.gds.domain.ProjectGrantContract;
import gov.nih.nci.cbiit.scimgmt.gds.domain.ProjectsVw;
import gov.nih.nci.cbiit.scimgmt.gds.domain.RepositoryStatus;


/**
 * Dao object for domain model class Project.
 * @see gov.nih.nci.cbiit.scimgmt.gds.domain.Project
 * @author Hibernate Tools
 */
@Component
public class ProjectsDao {

	private static final Logger logger = LogManager.getLogger(ProjectsDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	protected NedPerson loggedOnUser;	
	
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Project transientInstance) {
		logger.debug("persisting Project instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void delete(Project persistentInstance) {
		Long projectId = persistentInstance.getId();
		logger.info("Deleting Project instance "  + projectId);
		try {
			if(persistentInstance.getSubprojectFlag().equalsIgnoreCase("N")) {
				//Delete the answer selections if parent project only
				for (Iterator<PlanAnswerSelection> iterator = persistentInstance.getPlanAnswerSelections().iterator(); iterator.hasNext();) {
					PlanAnswerSelection ans = iterator.next();
					sessionFactory.getCurrentSession().delete(ans); // it will remove all answers and repository
					iterator.remove();
				}
			} else {
				// If subproject, just remove the repository but not the answers
				for(PlanAnswerSelection ans : persistentInstance.getPlanAnswerSelections()) {
					Iterator<RepositoryStatus> iterator = ans.getRepositoryStatuses().iterator();
					while (iterator.hasNext()) {
						RepositoryStatus repo = iterator.next();
						if(repo.getProject().getId().longValue() == persistentInstance.getId().longValue()) {
							sessionFactory.getCurrentSession().delete(repo);
							iterator.remove();
						}
					}
					// Also remove the reference to the answers
					ans.removeProject(persistentInstance);
				}
				
			}
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.info("Delete successful for project " + projectId);
			logger.info("Deletion performed by user: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			
		} catch (RuntimeException re) {
			logger.error("Delete failed for project " + projectId, re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());
			throw re;
		}
	}

	public Project merge(Project detachedInstance) {
		Long id = detachedInstance.getId();
		logger.info("Merging Project instance " + id);
		try {
			Hibernate.initialize(detachedInstance.getPlanAnswerSelections());
			if(id != null){
				//Already saved submission
				Hibernate.initialize(detachedInstance.getProjectGrantsContracts()); 				
				sessionFactory.getCurrentSession().evict(sessionFactory.getCurrentSession().get(Project.class, id));
				detachedInstance.setLastChangedBy(loggedOnUser.getAdUserId());	
				detachedInstance.setLastChangedDate(new Date());
				for(ProjectGrantContract grantContract: detachedInstance.getProjectGrantsContracts()) {
					if(StringUtils.isEmpty(grantContract.getCreatedBy())) {
						//This is for associated grants, which get deleted and re-saved.
						grantContract.setCreatedBy(loggedOnUser.getAdUserId());
					}
					if(grantContract.getCreatedDate() == null) {
					grantContract.setCreatedDate(new Date());
					}
					grantContract.setLastChangedBy(loggedOnUser.getAdUserId());
					grantContract.setLastChangedDate(new Date());
					grantContract.setProject(detachedInstance);
				}
			}
			else{
				//New submission
				detachedInstance.setCreatedBy(loggedOnUser.getAdUserId());	
				detachedInstance.setCreatedDate(new Date());
				for(ProjectGrantContract grantContract: detachedInstance.getProjectGrantsContracts()) {
					grantContract.setCreatedBy(loggedOnUser.getAdUserId());
					grantContract.setCreatedDate(new Date());
					grantContract.setProject(detachedInstance);
				}
			}				
			Project result = (Project) sessionFactory.getCurrentSession().merge(detachedInstance);
			for (Iterator<PlanAnswerSelection> iterator = result.getPlanAnswerSelections().iterator(); iterator.hasNext();) {
				PlanAnswerSelection afterMerge = iterator.next();
				boolean found = false;
				for(PlanAnswerSelection beforeMerge : detachedInstance.getPlanAnswerSelections()) {
					if(afterMerge.getPlanQuestionsAnswer().getId().longValue() == beforeMerge.getPlanQuestionsAnswer().getId().longValue()) {
						if(afterMerge.getPlanQuestionsAnswer().getDisplayText().equalsIgnoreCase(ApplicationConstants.OTHER)) {
							if(StringUtils.equalsIgnoreCase(afterMerge.getOtherText(), beforeMerge.getOtherText())) {
								found = true;
								break;
							}
						}
						else {
							found = true;
							break;
						}
					}
				}
				if (!found) {
					sessionFactory.getCurrentSession().delete(afterMerge);
					iterator.remove();
				}
			}
			result = (Project) sessionFactory.getCurrentSession().merge(result); // have to merge again because we removed the deleted object from association
			logger.info("Merge successful for project " + result.getId());
			logger.info("Merge performed by user: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());				
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());	
			throw re;
		}
	}

	public Project findById(Long id) {
		logger.debug("getting Project instance with id: " + id);
		try {
			Project instance = (Project) sessionFactory.getCurrentSession()
					.get(Project.class, id);
			if (instance == null) {
				logger.debug("No instance found with id " + id);
			} else {
				Hibernate.initialize(instance.getPlanAnswerSelections());
				Hibernate.initialize(instance.getRepositoryStatuses());				
				Hibernate.initialize(instance.getPageStatuses());
				logger.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.error("Retrieval failed for project " + id, re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());
			throw re;
		}
	}
	
	
	public ProjectsVw findProjectsVwById(Long id) {
		ProjectsVw projectsVw = null;
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProjectsVw.class);		
			criteria.add(Restrictions.eq("id", id));
			projectsVw = (ProjectsVw)criteria.uniqueResult();
		} catch(Throwable e) {
			logger.error("Unable to obtain ProjectsVw for id " + id, e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			throw e;
		}
		
		return projectsVw;
	}
	
	/**
	 * Gets the IC by icId
	 * 
	 * @param icId
	 * @return
	 */
	public RepositoryStatus findRepositoryById(Long repoId) {
		logger.debug("getting RepositoryStatus instance with repoId: " + repoId);
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RepositoryStatus.class);
			criteria.add(Restrictions.eq("id", repoId));
			 RepositoryStatus repoStatus = (RepositoryStatus) criteria.uniqueResult();
			return repoStatus;
		} catch (RuntimeException re) {
			logger.error("Unable to find IC by id " + repoId, re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			throw re;
		}
	}
	
	/**
	 * This method retrieves Grant / Contract List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GdsGrantsContracts> getGrantOrContractList(String grantContractNum,String applClassCode){

		logger.debug("Retrieving  Grant / Contract List from DB for grantContractNum: "+grantContractNum);
		logger.debug("Retrieving  ApplClassCode "+applClassCode);
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GdsGrantsContracts.class);	
			criteria.add(Restrictions.ilike("lookupGrantContractNum", grantContractNum.replaceAll("\\s",""),MatchMode.ANYWHERE));
			if(applClassCode !=null) {
				criteria.add(Restrictions.or(
					Restrictions.eq("applClassCode", applClassCode),
					Restrictions.eq("applClassCode", ApplicationConstants.APPL_CLASS_CODE_CONTRACT)));
			}
			List<GdsGrantsContracts> grantsListlist = criteria.list();
			
			//If multiple records exist then always pick the latest grant.
			if(grantsListlist.size() > 1){	
				criteria.add(Restrictions.eqProperty("lookupGrantContractNum","grantContractNum"));
				grantsListlist = criteria.list();
			}
			
			return grantsListlist;

		}catch (RuntimeException re) {
			logger.error("Retrieving  Grant / Contract List failed for grantNum " + grantContractNum, re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());
			throw re;
		}
	}
	
	/**
	 * This method returns latest grantContract from the
	 * segment containing the given applId
	 * @param applId
	 * @return
	 */
	public GdsGrantsContracts getGrantOrContract(Long applId){

		logger.info("Retrieving  Grant / Contract from DB for applId: "+applId);
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GdsGrantsContracts.class);	
			criteria.add(Restrictions.eq("lookupApplId", applId));
			GdsGrantsContracts grantContract = (GdsGrantsContracts) criteria.uniqueResult();
			return grantContract;

		}catch (RuntimeException re) {
			logger.error("Retrieving  Grant / Contract failed for applId: "+ applId, re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());				
			throw re;
		}
	}
	
	
	
	/**
	 * This method retrieves list of already linked submissions for a given grant.
	 * 
	 * @param grantContractNum
	 * @return
	 */
	public List<ProjectsVw> getPrevLinkedSubmissionsForGrant(String grantContractNum, String projectId){
		
		logger.debug("Retrieving already linked submissions for grantContractNum: "+grantContractNum);
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProjectsVw.class);	
			criteria.add(Restrictions.or(
					Restrictions.ilike("extGrantContractNum", grantContractNum,MatchMode.ANYWHERE),
					Restrictions.ilike("intGrantContractNum", grantContractNum,MatchMode.ANYWHERE)));
			if(!projectId.isEmpty()){
			criteria.add(Restrictions.ne("id", Long.valueOf(projectId)));
			criteria.add(Restrictions.or(
					   Restrictions.ne("parentProject.id",Long.valueOf(projectId)),
					   Restrictions.isNull("parentProject.id")));
			}
			List<ProjectsVw> grantsListlist = criteria.list();
			return grantsListlist;

		}catch (RuntimeException re) {
			logger.error("Error occurred while retrieving linked submissions for grantContractNum: " + grantContractNum, re);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			throw re;
		}
	}
	
	/**
	 * Retrieve Sub-projects based on parent project ID.
	 * @param parentProjectId
	 * @return List<ProjectsVw>
	 */
	@SuppressWarnings("unchecked")
	public List<ProjectsVw> getSubprojectsVw(Long parentProjectId) {
		
		List<ProjectsVw> list =  new ArrayList<ProjectsVw>();
		
		try {
			Criteria criteria = null;
			criteria = sessionFactory.getCurrentSession().createCriteria(ProjectsVw.class);
			criteria.add(Restrictions.eq("subprojectFlag", "Y"));
			criteria.add(Restrictions.eq("parentProject.id", parentProjectId));
			list =  (List<ProjectsVw>) criteria.list();
			return list;
			
		} catch (Throwable e) {
			logger.error("Error while searching for subproject submission views ", e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());
			logger.error("Pass-in parameters: Parent ProjectId - " + parentProjectId);
			logger.error("Outgoing parameters: Subproject List - " + list);
			
			throw e;
		}
	}
	
	
	/**
	 * Retrieve Sub-projects based on parent project ID.
	 * @param parentProjectId
	 * @return List<Project>
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getSubprojects(Long parentProjectId, boolean latestVersionOnly) {
		
		List<Project> list =  new ArrayList<Project>();
		
		try {
			Criteria criteria = null;
			criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
			criteria.add(Restrictions.eq("parentProjectId", parentProjectId));
			criteria.add(Restrictions.eq("subprojectFlag", "Y"));			
			if(latestVersionOnly) {
				criteria.add(Restrictions.eq("latestVersionFlag", "Y"));
			}
			list =  (List<Project>) criteria.list();
			return list;
			
		} catch (Throwable e) {
			logger.error("Error while searching for subproject submission ", e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());
			logger.error("Pass-in parameters: Parent ProjectId - " + parentProjectId);
			logger.error("Outgoing parameters: Subproject List - " + list);
			
			throw e;
		}
	}
	
	
	/**
	 * Retrieve Sub-projects based on parent project ID.
	 * @param parentProjectId
	 * @return List<Project>
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getVersions(Long projectGroupId, Long parentProjectId) {
		
		List<Project> list =  new ArrayList<Project>();
		
		try {
			Criteria criteria = null;
			criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
			criteria.add(Restrictions.eq("projectGroupId", projectGroupId));
			if(parentProjectId != null) {
				criteria.add(Restrictions.eq("parentProjectId", parentProjectId));
			}
			criteria.addOrder(Order.desc("createdDate"));
			list =  (List<Project>) criteria.list();
			return list;
			
		} catch (Throwable e) {
			logger.error("Error while searching for project versions ", e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());
			logger.error("Pass-in parameters: projectGroupId - " + projectGroupId + ", parentProjectId - " + parentProjectId);
			logger.error("Outgoing parameters: Version List - " + list);
			
			throw e;
		}
	}
	
	
	public Organization getOrganizationByDoc(String doc) {
		logger.debug("Retrieving  organization for docAbbreviation: " + doc);
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Organization.class);	
			criteria.add(Restrictions.eq("nihorgpath", doc));
			Organization  organization = (Organization) criteria.uniqueResult();
			return organization;

		}catch (Throwable e) {
			logger.error("Error getting organization for doc " + doc, e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			throw e;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> getSubOrgList(String sacCode) {
		logger.debug("Retrieving  subOrg list for sacCode: " + sacCode);
		List<String> result = null;
		Session session = sessionFactory.getCurrentSession();
		try {		
			final String hql = "select ltrim(sys_connect_by_path(nihOuAcronym,'/'),'/')"
				+ " from ned_orgunit orgunit where orgunit.inactive_date is null"
				+ " start with orgunit.nihsac = ?"
				+ " connect by prior orgunit.nihsac = orgunit.nihparentsac";
			
			SQLQuery query = session.createSQLQuery(hql);
			query.setString(0, sacCode);

			result = query.list();

		} catch (Throwable e) {
			logger.error("Error retrieving subOrg list for sacCode: " + sacCode, e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			throw e;
		}
		return result;
	}
	
	
	public Project getCurrentLatestVersion(Long projectGroupId) {
		logger.debug("Retrieving  latest version of project group: " + projectGroupId);
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class);	
			criteria.add(Restrictions.eq("projectGroupId", projectGroupId));
			criteria.add(Restrictions.eq("latestVersionFlag", "Y"));
			Project  project = (Project) criteria.uniqueResult();
			return project;

		}catch (Throwable e) {
			logger.error("Error getting latest version of project group: " + projectGroupId, e);
			logger.error("user ID: " + loggedOnUser.getAdUserId() + "/" + loggedOnUser.getFullName());			
			throw e;
		}
	}
}
