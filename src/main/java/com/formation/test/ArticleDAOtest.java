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
