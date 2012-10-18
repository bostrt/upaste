package net.upaste.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.upaste.model.Paste;

public class PasteDAO extends BaseDAO<Paste>
{
	public Paste getByID(long ID) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Paste> query = builder.createQuery(Paste.class);
		Root baseModel = query.from(Paste.class);

		query.where(builder.equal(baseModel.get("ID"), ID));
		
		return em.createQuery(query).getSingleResult();
	}

	
	/**
	 * Get the 10 latest pastes.
	 * @return
	 */
	public List<Paste> getRecentPastes(int limit)
	{
		try {
			TypedQuery<Paste> query = em.createNamedQuery("RecentPastes", Paste.class);
			query.setMaxResults(limit);
			return query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
