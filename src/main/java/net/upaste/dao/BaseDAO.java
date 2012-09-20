package net.upaste.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.upaste.EMF;
import net.upaste.model.BaseModel;

public abstract class BaseDAO<ENTITY extends BaseModel> {
	protected EntityManagerFactory emf = EMF.getInstance();
	protected EntityManager em = emf.createEntityManager();

	/**
	 * Get an ENTITY by it's ID.
	 * @param ID
	 * @return
	 */
	public ENTITY getByID(long ID) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BaseModel> query = builder.createQuery(BaseModel.class);
		Root baseModel = query.from(BaseModel.class);

		query.where(builder.equal(baseModel.get("ID"), ID));
		
		return (ENTITY) em.createQuery(query).getSingleResult();
	}
	
	public void insert(ENTITY entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
	
	public void update(ENTITY entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}
}
