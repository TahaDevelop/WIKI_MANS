package com.formation.wiki.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.formation.wiki.entity.Article;

public class ArticleDAO {
	private EntityManager em;
	private EntityTransaction tx;
	public ArticleDAO() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	// Methode d'ajout d'un article
	public void addArt(Article id) {
		tx.begin();
		em.persist(id);	
		tx.commit();
	}
	
	// Methode pour supprimer d'un article
	public void suppArt(Article id) {
		tx.begin();
		em.remove(id);	
		tx.commit();
	}
	
}
