package com.formation.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.ArticleDAO;
import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.entity.Article;

public class ArticleDAOtest {
	
private ArticleDAO artDAO;

	@Before
	public void executeBefore() {
		artDAO = new ArticleDAO();
	}
	@Test
	public void addArttest() {
		int size = artDAO.getAllArticle().size();
		Article art1 = new Article();
		art1.setTitle("addition des tests");
		artDAO.addArt(art1);
		int size2 = artDAO.getAllArticle().size();
		assertEquals(size + 1, size2);
		artDAO.suppArt(art1);
	}

	@Test
	public void suppArttest() {
		Article art1 = new Article();
		art1.setTitle("suppression des tests");
		artDAO.addArt(art1);
		int size = artDAO.getAllArticle().size();
		artDAO.suppArt(art1);
		int size2 = artDAO.getAllArticle().size();
		assertEquals(size - 1, size2);
	}

	@Test
	public void setPublishArticletest() {
		Article artdemo = new Article();
		artdemo.setTitle("DemoSetpublished");
		artdemo.setStatut(artDAO.addNewStatutWithId());
		artDAO.addArt(artdemo);
		
		Article arttest = artDAO.findbyTitle("DemoSetpublished");
		boolean bool = arttest.getStatut().getPublished();
		
		
		if (bool == false) {
			artDAO.setPublishArticle(arttest);
			assertTrue(arttest.getStatut().getPublished());
			assertFalse(arttest.getStatut().getWaitingforvalidation());
		} else {
			artDAO.setPublishArticle(arttest);
			assertFalse(arttest.getStatut().getPublished());
			assertTrue(arttest.getStatut().getWaitingforvalidation());
		}
		artDAO.suppArt(artdemo);
	}

	@Test
	public void findbyTitle() {
		Article artdemo = new Article();
		artdemo.setTitle("DemoTitle");
		artdemo.setStatut(artDAO.addNewStatutWithId());

		artDAO.addArt(artdemo);
		assertTrue(artDAO.findbyTitle("DemoTitle").getClass().isInstance(new Article()));
//		assertNull(artDAO.findbyTitle(null));
//		assertNull(artDAO.findbyTitle("_**"));
		artDAO.suppArt(artdemo);
	}

	@Test
	public void findbyId() {
		assertTrue(artDAO.findbyId(1).getClass().isInstance(new Article()));
	}

	@Test
	public void getAllArticle() {
		Article artdemo2 = new Article();
		artdemo2.setTitle("Demo2");
		artdemo2.setStatut(artDAO.addNewStatutWithId());
		artDAO.addArt(artdemo2);
		if (!artDAO.getAllArticle().isEmpty())
			for (int i = 0; i < artDAO.getAllArticle().size(); i++) {
				assertTrue(artDAO.getAllArticle().get(i).getClass().isInstance(new Article()));
//				System.out.println(i);
			}
		else {
			fail("pas de BDD");
		}
		artDAO.suppArt(artdemo2);
	}

	@Test
	public void getAllArticleByAuthor() {
	
		List<Article> listTest = artDAO.getAllArticleByAuthor("benji");
		if (!listTest.isEmpty()){
			for (int i = 0; i < listTest.size(); i++) {
				System.out.println(i);
				listTest.get(i).getId();
				listTest.get(i).getUser();
			}
		}
		else {
			fail("pas de BDD");
		}
	}

	@Test
	public void setReportArticle() {
		Article artdemo = new Article();
		artdemo.setTitle("DemoSet");
		artdemo.setStatut(artDAO.addNewStatutWithId());

		artDAO.addArt(artdemo);
		Article arttest = artDAO.findbyTitle("DemoSet");
		boolean bool = arttest.getStatut().getReportedasabused();
		if (bool == false) {
			artDAO.setReportArticle(arttest);
			assertTrue(arttest.getStatut().getReportedasabused());
		} else {
			artDAO.setPublishArticle(arttest);
			assertFalse(arttest.getStatut().getReportedasabused());
		}
		artDAO.suppArt(artdemo);
	}

		@Test
		public void getAllArticlebymonthtest(){
			Long compteur=0L;
			Long nbParCategorie=0L;
			Calendar cal = Calendar.getInstance();
			for(Article article : artDAO.getAllArticle()){
				cal.setTime(article.getPublishDate());
				System.out.println(cal.get(Calendar.MONTH)+1);
				if(cal.get(Calendar.MONTH)+1==8){
					compteur +=1;
				}
			}

			Map<Integer, Long> artA = artDAO.getArticlebyMonth();
			for (Map.Entry elementMonth : artA.entrySet()) {
		           System.out.println("clé: "+elementMonth.getKey() 
		                              + " | valeur: " + elementMonth.getValue());
		           if(elementMonth.getKey().equals(8)){
		        	  nbParCategorie=(Long) elementMonth.getValue();
		           }
		        }
			assertEquals(compteur, nbParCategorie);
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
	           System.out.println("clÃ©: "+elementCategorie.getKey() 
	                              + " | valeur: " + elementCategorie.getValue());
	           if(elementCategorie.getKey().equals("aventure")){
	        	  nbParCategorie=(Long) elementCategorie.getValue();
	           }
	        }
		assertEquals(compteur, nbParCategorie);
	}
}

