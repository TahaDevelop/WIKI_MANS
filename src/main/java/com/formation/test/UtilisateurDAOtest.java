package com.formation.test;

import static org.junit.Assert.*;

import javax.persistence.NoResultException;

import org.junit.*;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;
import com.formation.wiki.dao.*;

import junit.framework.TestCase;

 
public class UtilisateurDAOtest {	
	private Utilisateur testU;
<<<<<<< HEAD
	private Role testRole;
	private String role_name;
=======
	private UtilisateurDAO utilisateurDAOtest;
	Role testRole;
	String role_name;
	Utilisateur user;
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
	
	@Before
	public void executeBeforeTest() {
		 role_name=null;
		 testU = new Utilisateur();
		 testRole = new Role();
		 utilisateurDAOtest=new UtilisateurDAO();
		 testRole.setName("ADMIN");
		 testU.setRole(testRole);	 
		 
		 testU.setLogin("Sahobi");
 		 testU.setIdUser(1);
		 
	}
	
 
	/**Autheur  : Sahobi
	 * Objectif : verifier l'utilisateur à partir du Login="Sahobi"
	 * */
 	@Test(expected=NoResultException.class)
	public void getUserByLoginTest(){		
 		assertNotNull(utilisateurDAOtest.getUserByLogin("Sahobi"));
 		assertNull(utilisateurDAOtest.getUserByLogin("NON"));
 		
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
 		utilisateurDAOtest.getUserById(1);
 	}
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
}



