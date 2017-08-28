package com.formation.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.dao.WikiEntityManager;
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;

public class UtilisateurDAOtest {

	private Utilisateur testU;
<<<<<<< HEAD
<<<<<<< HEAD
	private Role testRole;
	private String role_name;
=======
	private UtilisateurDAO utilisateurDAOtest;
	Role testRole;
	String role_name;
	Utilisateur user;
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
=======
	private Utilisateur testUref;
	private UtilisateurDAO testUDAO;
	Role testRole;
	String role_name;
	private EntityManager em;
	private EntityTransaction tx;
>>>>>>> c5b63cbf927fdb223307264664f82b7dfb627574
	
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
	/**Autheur  : Sahobi
	 * Objectif : verifier l'utilisateur à partir du Login="Sahobi"
	 * */
 	@Test(expected= NoResultException.class)
	public void getUserByLoginTest(){		
 		assertNotNull(testUDAO.getUserByLogin("Sahobi"));
 		assertNull(testUDAO.getUserByLogin("NON"));
 		
	}
<<<<<<< HEAD
	
	@Test
	public void authentificationUsertest() {
		assertEquals(testU.getRole().getName(),"ADMIN");
	}
	
	@Test
	public void creationUsertest() {
		assertNull(testU);
	}
	
	@Test
	public void activerUsertest() {
		
	}
	
	@Test
	/*
	 * UsersWaitingActivationTest()
	 * JP Alonso
	 */
	public void UsersWaitingActivationTest() {
		
	}
=======
 	
 	/**Autheur  : Sahobi
	 * Objectif : verifier l'utilisateur à partir d'un IdUser
	 * */
 	@Test
 	public void getUserByIdTest(){
 		testUDAO.getUserById(1);
 	}
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
}


=======
>>>>>>> c5b63cbf927fdb223307264664f82b7dfb627574

}
