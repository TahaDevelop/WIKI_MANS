package com.formation.wiki.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WikiEntityManager {
	
	public static final String PERSISTENCE_UNIT_NAME="PU_WIKI";

	private EntityManager em;
	
	public WikiEntityManager() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}
	  public static synchronized WikiEntityManager getInstance() {
	       
	            return new WikiEntityManager();
	        
	    }

	    public EntityManager getEntityManager() {
	        return em;
	    }
	
	

}
