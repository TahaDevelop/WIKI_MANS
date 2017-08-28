<<<<<<< HEAD
package com.formation.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.ArticleDAO;
import com.formation.wiki.entity.Article;

public class ArticleDAOtest {
	
private ArticleDAO artDAO;

	@Before
	public void executeBefore() {
		artDAO = new ArticleDAO();
	}
	
	@Test
	public void getAllArticleReportedtest() {
		int compteur=0;
		for(Article article : artDAO.getAllArticle()){
			if(article.getReportNumber()>10){
				compteur+=1;
			}
		}
		assertEquals(compteur, artDAO.getAllArticleReported().size());
			
	}
	@Test
	public void getAllArticleReportedasabusedtest(){
		int compteur=0;
		for(Article article : artDAO.getAllArticle()){
			if(article.getStatut().getReportedasabused()){
				compteur +=1;
			}
		}
		assertEquals(compteur, artDAO.getAllArticleReportedasabused().size());
		
	}
	
	@Test
	public void getAllArticlebyCatgtest(){
		Long compteur=0L;
		Long nbParCategorie=0L;
		for(Article article : artDAO.getAllArticle()){
			if(article.getCategorie().equals("aventure")){
				compteur +=1;
			}
		}

		Map<String, Long> artA = artDAO.getAllArticlebyCatg();
		for (Map.Entry elementCategorie : artA.entrySet()) {
	           System.out.println("clé: "+elementCategorie.getKey() 
	                              + " | valeur: " + elementCategorie.getValue());
	           if(elementCategorie.getKey().equals("aventure")){
	        	  nbParCategorie=(Long) elementCategorie.getValue();
	           }
	        }
		assertEquals(compteur, nbParCategorie);
	}
}
=======
package com.formation.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.formation.wiki.dao.ArticleDAO;
import com.formation.wiki.entity.Article;
import com.formation.wiki.entity.Commentaire;
import com.formation.wiki.entity.Statut;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;

public class ArticleDAOtest {
	
	private Article articleTester;
	private Commentaire commentTester;
	private Statut statutTester;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void executeBeforeTest() {
		commentTester = new Commentaire();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void getArticleAttendretest() {
		ArticleDAO art = new ArticleDAO();
		int nbArticlesWaiting=0;
		List<Article> allArticles=art.getAllArticle();	
		for (Article article : allArticles){
			if (article.getStatut().getWaitingforvalidation()){
				nbArticlesWaiting = nbArticlesWaiting + 1;
			}	
		}
		assertEquals(nbArticlesWaiting, art.getArticleAttendre().size());

	}
	
	@Test
	public void articleDuMoisTest() {
		ArticleDAO art = new ArticleDAO();
		List<Article> allArticles = art.getAllArticle();
		Article articleDuMois=null;
		Article article=art.findbyId(art.articleDuMois());
		for(Article  articleTemp : allArticles){
			if(articleTemp.getCommentaires().size() > article.getCommentaires().size()){
				articleDuMois=articleTemp;
			}
			else {
				articleDuMois=article;
			}
		}
		assertEquals(article, articleDuMois);

		}	
}
	


>>>>>>> 1e25f20bf23858d5e6c19760ae27b94c9804334e
