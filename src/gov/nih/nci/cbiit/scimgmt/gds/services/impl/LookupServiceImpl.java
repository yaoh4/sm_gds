package gov.nih.nci.cbiit.scimgmt.gds.services.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import gov.nih.nci.cbiit.scimgmt.gds.dao.PropertyListDao;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Lookup;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Organization;
import gov.nih.nci.cbiit.scimgmt.gds.domain.PlanQuestionsAnswer;
import gov.nih.nci.cbiit.scimgmt.gds.domain.Property;
import gov.nih.nci.cbiit.scimgmt.gds.services.LookupService;

/**
 * Class to support retrieval and caching of lookup data from the database.
 * 
 * @author menons2
 *
 */
@Component
@EnableCaching
@CacheConfig(cacheNames = "lookupLists")
public class LookupServiceImpl implements LookupService {
	
	
	private static final Logger logger = LogManager.getLogger(LookupServiceImpl.class);
	
	@Autowired
	private PropertyListDao propertyListDAO;
	
	
	/**
	 * Get lookup list for a given list name. Retrieve 
	 * from the cache if present, else from the DB
	 */
	@Cacheable(key = "#listName")
	public List<Lookup> getLookupList(String listName) {
	  	
		logger.info("Loading Lookup list from DB");
		return propertyListDAO.searchLookup(listName);
	  	
	}
	
	
	
	/**
	 * Fetch entire lookup lists from the DB and the caller stores in
	 * cache. Invoked during application initialization.
	 */
	public List<Lookup> getAllLookupLists() {
		
		logger.info("Loading lookup data from LOOKUP_T");
		List<Lookup> allLookups = propertyListDAO.getAllLookupLists();
		return allLookups;
	}


	/**
	 * Update the given lookup list in the cache.
	 * @param listName
	 * @param lookupList
	 * @return
	 */
	@CachePut(key="#listName")
	public List<Lookup> updateLookupList(String listName, List<Lookup> lookupList) {
		return lookupList;
	}
	
	
	/**
	 * Retrieves the properties from DB. Invoked during
	 * application initialization and for reloading
	 * from sysAdmin action.
	 */
	public List<Property> loadPropertiesList() {
		
		logger.info("Loading Properties list from DB");
		return propertyListDAO.getPropertiesList();
	}

	/**
	 * Fetch entire lookup lists from the DB and the caller stores in
	 * cache. Invoked during application initialization.
	 */
	public List<PlanQuestionsAnswer> getAllPlanQuestionsAnswers() {
		
		logger.info("Loading lookup data from PLAN_QUESTIONS_ANSWERS_T");
		List<PlanQuestionsAnswer> allPlanQuestionsAnswers = propertyListDAO.getAllPlanQuestionsAnswers();
		return allPlanQuestionsAnswers;
	}
	
	/**
	 * Get Lookup object by list name and code
	 * 
	 * @param listName
	 * @param code
	 * @return
	 */
	public Lookup getLookupByCode(String listName, String code) {
		List<Lookup> list = (List<Lookup>) getLookupList(listName);
		for(Lookup entry: list) {
			if (entry.getCode().equalsIgnoreCase(code))
				return entry;
		}
		return null;
	}
	
	/**
	 *  Get docList.  
	 *  Retrieve from the cache if present, else from the DB
	 */
	@Cacheable(key = "#docList")
	public List<Organization> getDocList(String docList) {
	  	
		logger.info("Loading docList from DB");
		return propertyListDAO.getDocList(docList);	  	
	}
	
	/**
	 * Update the given docList in the cache.
	 * @param docList
	 * @param lookupList
	 * @return
	 */
	@CachePut(key="#docList")
	public List<Organization> updateDocList(String docList, List<Organization> updatedDocList) {
		return updatedDocList;
	}
	
}
