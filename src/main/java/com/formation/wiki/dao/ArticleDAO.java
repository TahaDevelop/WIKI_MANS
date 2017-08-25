package com.formation.wiki.dao;

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

	// --------------------------------article approuvé/désaprouvé
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
		Query q = em.createQuery("SELECT a FROM article a");

		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
	}

	// --------------------------------gelAllArticle pr un auteur
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticleByAuthor(String user) {
		Query q = em.createQuery("SELECT a FROM Article a WHERE a.user= :user");
		q.setParameter("user", user);
		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
	}
	//

	/// partie Abusers de notre
	/// WIKI------------------------------------------------------------------------
	/// ------------------------------------------------------------------------
	// --------------------------------article approuvé/désaprouvé pour un report
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
	
	//-----------------------Afficher articles avec statut reportedasabused
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticleReportedasabused() {
		Query q = em.createQuery("SELECT a FROM Article a, Statut s WHERE a.statut=s.id and s.reportedasabused= true");
		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
	}
	

	/// partie Statistiques de notre
	/// WIKI------------------------------------------------------------------------
	/// ------------------------------------------------------------------------
	// gelAllArticle by month
<<<<<<< HEAD
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticlebyMonth() {
		Query q = em.createQuery("SELECT Month(a.publishDate) AS Mois, count(*) AS nb FROM Article a GROUP BY Month(a.publishDate);");
		//verifier cette syntaxe type de retour incorrect
		List<Article> listArticles = (List<Article>) q.getResultList();
		return listArticles;
=======
@SuppressWarnings("unchecked")
public Map<String,Integer> getArticlebyMonth() {

		String script = "SELECT Month(a.publishDate) AS Mois, count(*) AS nb FROM Article a GROUP BY Month(a.publishDate)";
		Query query = em.createQuery(script);
		List<Object[]> listMonth = query.getResultList();
		 Map<String,Integer> hm= new HashMap<String,Integer>();
		 for (Object ligneAsObject : listMonth) {

		     // ligne correspond à une des lignes du résultat
		    Object[] ligne = (Object[])ligneAsObject ;
		    hm.put((String)ligne[0], (Integer)ligne[1] );
		 }
		return hm;
	

>>>>>>> 31da4317b38e79713c96260b3de9863cee6784a0
	}

}
