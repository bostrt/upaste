package net.bostrt.lanbin.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import net.bostrt.lanbin.model.Paste;

public class PasteDAO extends BaseDAO<Paste>
{
	/**
	 * Get the 10 latest pastes.
	 * @return
	 */
	public List<Paste> getRecentPastes()
	{
		try {
			TypedQuery<Paste> query = em.createNamedQuery("RecentPastes", Paste.class);
			query.setMaxResults(10);
			return query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get a {@link Paste} by the UUID
	 */
	public Paste getByUUID(String uuid)
	{
		try {
			TypedQuery<Paste> query = em.createNamedQuery("ByUUID", Paste.class);
			query.setParameter("uuid", uuid);
			
			return query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
