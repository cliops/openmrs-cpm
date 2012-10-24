package org.openmrs.module.cpm.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.cpm.ProposedConceptPackageResponse;
import org.openmrs.module.cpm.api.db.ProposedConceptPackageResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate specific database methods for {@link ProposedConceptPackageResponseDAO}
 */
@Repository
public class HibernateProposedConceptPackageResponseDAO implements ProposedConceptPackageResponseDAO {
	
	private static Log log = LogFactory.getLog(HibernateProposedConceptPackageDAO.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.getAllConceptProposalPackageResponses()
	 */
	@Override
	public List<ProposedConceptPackageResponse> getAllConceptProposalPackageResponses() {
		@SuppressWarnings("unchecked")
        List<ProposedConceptPackageResponse> result = (List<ProposedConceptPackageResponse>) sessionFactory.getCurrentSession().createQuery("from ProposedConceptPackageResponse").list();
		if (log.isDebugEnabled()) { log.info("getAllConceptProposalPackageResponses returned: " + result.size() + " results");
		}
		return result;
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.getConceptProposalPackageResponseById(Integer id)
	 */
	@Override
	public ProposedConceptPackageResponse getConceptProposalPackageResponseById(Integer id) {
		if (id != null) {
			Query query = sessionFactory.getCurrentSession().createQuery("from ProposedConceptPackageResponse package where package.id = :id");
			query.setInteger("id", id);
			ProposedConceptPackageResponse result = (ProposedConceptPackageResponse) query.uniqueResult();
			if (log.isDebugEnabled()) { log.debug("getConceptProposalPackageResponseById returned: " + result); }
			return result;
		} else {
			log.warn("Attempting to get package with null id");
			return null;
		}
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.getConceptProposalPackageResponseByProposalUuid(Integer uuid)
	 */
	@Override
	public ProposedConceptPackageResponse getConceptProposalPackageResponseByProposalUuid(String uuid) {
		if (uuid != null) {
			Query query = sessionFactory.getCurrentSession().createQuery("from ProposedConceptPackageResponse package where package.proposedConceptPackageUuid = :uuid");
			query.setString("uuid", uuid);
			ProposedConceptPackageResponse result = (ProposedConceptPackageResponse) query.uniqueResult();
			if (log.isDebugEnabled()) { log.debug("getConceptProposalPackageResponseByProposalUuid returned: " + result); }
			return result;
		} else {
			log.warn("Attempting to get package with null uuid");
			return null;
		}
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.saveConceptProposalPackageResponse(ProposedConceptPackage conceptPackageResponse)
	 */
	@Override
	public ProposedConceptPackageResponse saveConceptProposalPackageResponse(ProposedConceptPackageResponse conceptPackageResponse) {
		if (conceptPackageResponse != null) {
			sessionFactory.getCurrentSession().saveOrUpdate(conceptPackageResponse);
			return conceptPackageResponse; 
		} else {
			log.warn("Attempting to delete null package");
			return null;
		}
	}
	
	/**
	 * @see org.openmrs.module.metadatasharing.api.ConceptProposalService.deleteConceptProposalPackageResponse(ProposedConceptPackageResponse conceptPackageResponse)
	 */
	@Override
	public void deleteConceptProposalPackageResponse(ProposedConceptPackageResponse conceptPackageResponse) {
		if (conceptPackageResponse != null) {
			sessionFactory.getCurrentSession().delete(conceptPackageResponse);
		} else {
			log.warn("Attempting to delete null package");
		}
		
	}


}