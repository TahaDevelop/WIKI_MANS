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
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;

public class UtilisateurDAOtest {

	private Utilisateur testU;
	private UtilisateurDAO testUDAO;
	Role testRole;
	String role_name;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void executeBeforeTest() {
		role_name = null;
		testU = new Utilisateur();
		testRole = new Role();
		testRole.setName("ADMIN");
		testU.setRole(testRole);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@Test
	public void authentificationUsertest() {
		assertEquals(testU.getRole().getName(), "ADMIN");
	}

	@Test
	public void creationUsertest() {
		assertNull(testU);
	}

	@Test
	public void activerUsertest() {
		
		boolean user_active = false;
		testU = testUDAO.findbyId(1);
		user_active=testU.getActiver();
		assertTrue(user_active);
		System.out.println("user_active :"+user_active);
		
	}
	
	@Test
	public void addCountAbusertest() {
		
		int backupReportAbuser=0;
		testU = testUDAO.findbyId(1);
		backupReportAbuser = testU.getReportAbuser();
		
		testUDAO.addReportAbuser(testU);
				
		testU = testUDAO.findbyId(1);
		
		assertEquals(backupReportAbuser+1, testU.getReportAbuser());
	
	}
	

}
