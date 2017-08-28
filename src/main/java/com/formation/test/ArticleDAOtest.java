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
import javax.persistence.Query;

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
	


