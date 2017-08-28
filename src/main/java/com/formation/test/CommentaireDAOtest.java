package com.formation.test;

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
