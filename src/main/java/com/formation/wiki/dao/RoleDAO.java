package com.formation.wiki.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.formation.wiki.entity.Commentaire;
import com.formation.wiki.entity.Role;

/*
 * Auteur JP Alonso
 */
public class RoleDAO {
	private EntityManager em;
	private EntityTransaction tx;
	
	public RoleDAO() {
		super();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx=em.getTransaction();
	}

	/*
	 * getByName()
	 */
	public Role getByName(String roleName) {
		Role role = null;
		Query query = em.createQuery("select role from Role r WHERE name=:name");
		query.setParameter("name", roleName);
		role =(Role)query.getSingleResult();
		return role;
	}
}
