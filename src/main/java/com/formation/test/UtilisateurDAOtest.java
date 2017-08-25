package com.formation.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.entity.Utilisateur;

public class UtilisateurDAOtest {
	
	private Utilisateur testU;
	
	@Before
	public void executeBeforeTest() {
		 testU = new Utilisateur();
	}
	
	@Test
	public static void isUserExist() throws TimeoutException,SQLException {
	}
	
	@Test
	public void AuthentificationUsertest() {
		
	}
	
	@Test
	public void CreationUsertest() {
		assertNull(testU);
	}
}



