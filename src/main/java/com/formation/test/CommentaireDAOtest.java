package com.formation.test;

<<<<<<< HEAD
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.CommentaireDAO;
import com.formation.wiki.dao.RoleDAO;
import com.formation.wiki.entity.Article;
import com.formation.wiki.entity.Commentaire;
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;

public class CommentaireDAOtest {

	private Role role;
	private Utilisateur user;
	private Article article;
	private Commentaire testComment;
	
	private RoleDAO roleDAO = new RoleDAO();
	private CommentaireDAO commentDAO = new CommentaireDAO();
	
	@Before
	public void executeBeforeTest() {
		 role = new Role();
		 role = roleDAO.getByName("ADMIN");
		 user = new Utilisateur();
		 user.setRole(role);
	}
	
	@Test
	public void nbCommentPeriodTest() throws TimeoutException,SQLException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		
		Date dateDeb = sdf.parse("2016-01-01");
		Date dateFin = sdf.parse("2016-12-31");
		Assert.assertEquals(commentDAO.nbCommentPeriod(dateDeb, dateFin), 1);
	}

}
=======
import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.ArticleDAO;
import com.formation.wiki.dao.CommentaireDAO;
import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.entity.Article;
import com.formation.wiki.entity.Commentaire;
import com.formation.wiki.entity.Utilisateur;

public class CommentaireDAOtest {
	
	private EntityManager em;
	private EntityTransaction tx;
	private CommentaireDAO commentaireDAO;
	private ArticleDAO articleDAO;
	private UtilisateurDAO userDAO;
	
	@Before
	public void init(){
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx=em.getTransaction();
		 commentaireDAO=new CommentaireDAO();
		 articleDAO=new ArticleDAO();
		 userDAO=new UtilisateurDAO();
	}

	@Test
	public void createCommenttest(){
		
		Article article=new Article(); 
		article.setContent("Premier article de test");
		article.setTitle("Java Livre");
		article.setCategorie("Informatique");
		articleDAO.addArt(article);
		
		Utilisateur user=new Utilisateur();
		user.setLogin("Mary_ComDAOTest_login");
		user.setPassword("Mary_ComDAOTest_password");
		user.setPrenom("Mary_ComDAOTest_prenom");
		userDAO.addUser(user);
		
		Commentaire comment=new Commentaire();
		comment.setComment("Cet article me plait bcp!!");
		int compteur=commentaireDAO.getAllComments().size();
		commentaireDAO.createComment(comment, article, user);
		assertEquals(compteur+1,commentaireDAO.getAllComments().size());
	}
	
//	----------------------------------MODIFY----------------------------	
	@Test
	public void modifierObjet() {
		

//		Début de modification
		Commentaire comment=commentaireDAO.getCommentById(3);
		comment.setComment("Remodification commentaire");
		commentaireDAO.modifyComment(comment, comment.getArticle(), comment.getUser());
		assertEquals(comment.getComment(),"Remodification commentaire");
		comment.setComment(null);
	}
	
//	----------------------------------DELETE----------------------------
	
	@Test
	public void deleteCommenttest(){

		//Création de commentaire
		Article article=new Article(); 
		article.setContent("Premier article de test");
		article.setTitle("Java Livre");
		article.setCategorie("Informatique");
		articleDAO.addArt(article);
		
		Utilisateur user=new Utilisateur();
		user.setLogin("Mary_ComDAOTest_login");
		user.setPassword("Mary_ComDAOTest_password");
		user.setPrenom("Mary_ComDAOTest_prenom");
		userDAO.addUser(user);
		
		Commentaire comment=new Commentaire();
		comment.setComment("Suppression commentaire");
		commentaireDAO.createComment(comment, article, user);
		//Fin création commentaire
		
		int compteur=commentaireDAO.getAllComments().size();
		commentaireDAO.deleteComment(comment);
		assertEquals(compteur-1,commentaireDAO.getAllComments().size());

	}
}


>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
