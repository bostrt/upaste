package net.upaste;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final String PERSISTENCE_UNIT = "upaste";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

	private EMF() {}
	
	public static EntityManagerFactory getInstance() {
		return emf;
	}
}
