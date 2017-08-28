package com.formation.wiki.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.formation.wiki.entity.Article;
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

	// Methode d'ajout d'un article
	public void addArt(Article article) {
		tx.begin();
		article.setPublishDate(new Date());
		em.persist(article);
		tx.commit();
	}
//methode pour set un statut lors de la creation d'un article
	public Statut addNewStatutWithId(){
	Statut st=new Statut();
	st.setPublished(false);
	st.setWaitingforvalidation(true);
	st.setReportedasabused(false);
	tx.begin();
	em.persist(st);
	tx.commit();
	return st;
}
	// Methode pour supprimer d'un article
	public void suppArt(Article article) {
		tx.begin();
		em.remove(article);
		tx.commit();
	}

	// --------------------------------article approuve/desaprouve
	public void setPublishArticle(Article article) {
		Statut st = article.getStatut();

		if (st.getPublished() == false) {
			st.setPublished(true);
		} else {
			st.setPublished(false);
		}

		if (st.getWaitingforvalidation() == false) {
			st.setWaitingforvalidation(true);
		} else {
			st.setWaitingforvalidation(false);
		}

		tx.begin();
		em.merge(article);
		tx.commit();

	}

	// --------------------------------find by iD
	public Article findbyId(int id) {
		Query q = em.createQuery("SELECT a FROM Article a WHERE a.id= :id");
		q.setParameter("id", id);
		Article article = (Article) q.getSingleResult();
		return article;

	}

	// --------------------------------find by title
	public Article findbyTitle(String title) {
		Query q = em.createQuery("SELECT a FROM Article a WHERE a.title= :title");
		q.setParameter("title", title);
		Article article = (Article) q.getSingleResult();
		return article;
	}

	// --------------------------------gelAllArticle
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticle() {
		Query q = em.createQuery("SELECT a FROM Article a");

		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
	}

	// --------------------------------gelAllArticle pr un auteur
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticleByAuthor(String login) {
		Query q = em.createQuery("SELECT a FROM Article a, Utilisateur u WHERE u.login= :login AND u.idUser=a.user");

		q.setParameter("login", login);
		List<Article> articlesByAuthor = (List<Article>) q.getResultList();
		return articlesByAuthor;
	}
	//

	/// partie Abusers de notre
	/// WIKI------------------------------------------------------------------------
	/// ------------------------------------------------------------------------
	// --------------------------------article approuve/desaprouve pour un
	/// report
	public void setReportArticle(Article article) {
		Statut st = article.getStatut();

		if (st.getReportedasabused() == false) {
			st.setReportedasabused(true);
		} else {
			st.setReportedasabused(false);
		}

		tx.begin();
		em.merge(article);
		tx.commit();

	}

	// ---------------------------get all article with reported status
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticleReported() {
		Query q = em.createQuery("SELECT a FROM Article a WHERE a.reportNumber>10");
		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
	}

	// -----------------------Afficher articles avec statut reportedasabused
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticleReportedasabused() {
		Query q = em.createQuery("SELECT a FROM Article a WHERE a.statut.reportedasabused= true");
		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
	}
	
	// ************************** GetArticle en attendre d`aprove, LIUDMILA ********TESTER OK*********
	@SuppressWarnings("unchecked")
	public List<Article> getArticleAttendre() {
		String query = "SELECT art FROM Article art WHERE art.statut.waitingforvalidation =true";
		Query q = em.createQuery(query);
		List<Article> resultsAttendreAprove = q.getResultList();
		System.out.println(resultsAttendreAprove.size());
//		for(Article article : resultsAttendreAprove){
//		
//			System.out.println(article.getTitle());
//		} 
		return resultsAttendreAprove;
	}

	// ********************************** Article du mois, LIUDMILA **********TEST OK********
		public int articleDuMois() {
			String query = "SELECT art.id FROM Article art ORDER BY art.commentaires.size asc";
			Query q = em.createQuery(query);
			return (Integer) q.getResultList().get(0);

		}

	/// partie Statistiques de notre
	/// WIKI------------------------------------------------------------------------
	/// ------------------------------------------------------------------------
	// gelAllArticle by month
		@SuppressWarnings("unchecked")
		public Map<Integer, Long> getArticlebyMonth() {

			String script = "SELECT Month(a.publishDate) AS Mois, count(*) AS nb FROM Article a GROUP BY Month(a.publishDate)";
			Query query = em.createQuery(script);
			List<Object[]> listMonth = query.getResultList();
			Map<Integer, Long> hm = new HashMap<Integer, Long>();
			for (Object ligneAsObject : listMonth) {

				// ligne correspond à une des lignes du résultat
				Object[] ligne = (Object[]) ligneAsObject;
				hm.put((Integer) ligne[0], (Long) ligne[1]);
			}
			return hm;

		}
	//-------nombre d'articles par catÃ¯Â¿Â½gorie
		@SuppressWarnings("unchecked")
		public Map<String, Long> getAllArticlebyCatg() {
			Query q = em.createQuery("SELECT a.categorie, count(a.id) as number FROM Article a GROUP BY a.categorie");
			List<Object[]> listCatg = q.getResultList();
			System.out.println(listCatg.size());
			Map<String, Long> hm=new HashMap<String, Long>();
			for (Object ligneasObject : listCatg){
				Object[] ligne=(Object[]) ligneasObject;
				hm.put((String) ligne[0] , (Long) ligne[1]);
			}
			return hm;
		}
}
