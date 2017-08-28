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
	private UtilisateurDAO utilisateurDAOtest;
	Role testRole;
	String role_name;
	Utilisateur user;
	
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
 	
 	/**Autheur  : Sahobi
	 * Objectif : verifier l'utilisateur à partir d'un IdUser
	 * */
 	@Test
 	public void getUserByIdTest(){
 		utilisateurDAOtest.getUserById(1);
 	}
}



