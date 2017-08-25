package com.formation.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;

public class UtilisateurDAOtest {
	

	private Utilisateur testU;
	Role testRole;
	String role_name;
	
	@Before
	public void executeBeforeTest() {
		 role_name=null;
		 testU = new Utilisateur();
		 testRole = new Role();
		 testRole.setName("ADMIN");
		 testU.setRole(testRole);
	}
	
	@Test
	public static void isUserExist() throws TimeoutException,SQLException {
	}
	
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
	
}



