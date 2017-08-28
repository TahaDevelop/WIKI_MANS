package com.formation.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.dao.WikiEntityManager;
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;

public class UtilisateurDAOtest {

	private Utilisateur testU;
	private Utilisateur testUref;
	private UtilisateurDAO testUDAO;
	Role testRole;
	String role_name;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void executeBeforeTest() {
		
		role_name = null;
		testU = new Utilisateur();
		testUref = new Utilisateur();
		testUDAO = new UtilisateurDAO();
		testRole = new Role();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	// Created by SY
	
	@Test
	public void authentificationUsertest() {
		
		testU = testUDAO.findbyId(2);
		assertEquals(testU.getRole().getName(), "ADMIN");
		
	}
	
	// Created by SY
	@Test
	public void creationUsertest() {

		// une seule fois ça  marche cet test car login est unique
		testU.setLogin("testUT");
		testU.setPassword("1234");
		testU.setNom("testUT");
		testU.setPrenom("testuserprenom");

		testUDAO.creationUser(testU, "MEMBRE");
		
		testUref = testUDAO.getUserByLogin("testUT");
		assertEquals(testU.getLogin(), testUref.getLogin());
		
	}
	
	// Created by SY
	@Test
	public void activerUsertest() {
		
		boolean acivateUser = true;
		testU = testUDAO.findbyId(1);
		
		
		testU.setActiver(acivateUser);
		testUDAO.activerUser(testU);
		
		testUref = testUDAO.findbyId(1);
		assertTrue(testUref.getActiver());
	
	}
	
	// Created by SY
	@Test
	public void deactiverUsertest() {
		
		boolean acivateUser = false;
		testU = testUDAO.findbyId(1);
		
		testUDAO.deactiverUser(testU);
		testUref = testUDAO.findbyId(1);
			
		assertFalse(testUref.getActiver());
	}
	
	// Created by SY
	@Test
	public void addCountAbusertest() {
		
		int backupReportAbuser=0;
		testU = testUDAO.findbyId(1);
		backupReportAbuser = testU.getReportAbuser();
		
		testUDAO.addReportAbuser(testU);
				
		testU = testUDAO.findbyId(1);
		
		assertEquals(backupReportAbuser+1, testU.getReportAbuser());
	
	}
	
	// Created by SY
	@Test
	public void checkIsAbusertest () {
		testU = testUDAO.findbyId(1);
		assertTrue(testUDAO.checkIsAbuser(testU));
	}

}
