package com.formation.wiki.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.formation.wiki.entity.Article;
import com.formation.wiki.entity.Commentaire;
import com.formation.wiki.entity.Statut;

public class ArticleDAO {

	private EntityManager em;
	private EntityTransaction tx;

	public ArticleDAO() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	
	// GetArticle en attendre d`aprove
	public List<Article> getArticleAttendre() {	
		String query = "SELECT art FROM Article art WHERE art.statut.waitingforvalidation =:true";
		Query q = em.createQuery(query);
		List<Article> resultsAttendreAprove = q.getResultList();
		return resultsAttendreAprove;		
	}

	// Article du mois
	public Object[] articleDuMois(){
		String query = "SELECT art.commentaires.size,art FROM Article art ORDER BY art.commentaires.size DESC";
		Query q = em.createQuery(query);
	//	Article a = (Article) q.getFirstResult();
		System.out.println(q.getResultList());
		return null;		
	}

}
