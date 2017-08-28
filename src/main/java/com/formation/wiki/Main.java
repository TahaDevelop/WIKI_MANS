package com.formation.wiki;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;


public class Main {

	// test methode for SY
	public static void syTestUtilisateur() {
		
		Utilisateur u = new Utilisateur();
		UtilisateurDAO uDAO = new UtilisateurDAO();
		Role r = new Role();
		
		u.setLogin("david");
		u.setPassword("1234");
		u.setNom("david");
		u.setPrenom("manon");
		u.setActiver(true);
		u.setEmail("ace@gmail.com");
		r.setName("MEMBRE");
		u.setRole(r);
		
		//uDAO.addUser(u);
		
		System.out.println(uDAO.AuthentificationUser(u));
		uDAO.CreationUser(u, "ADMIN");
		
	}
	
	public static void main(String[] args) {
		
//		//Test creation et insertion USER OK
//		Utilisateur user=new Utilisateur();
//		//Taha COmment GIT
//		user.setLogin("moi");
//		user.setPassword("toto");
//		user.setActiver(true);	
//		user.setEmail("belbattach.taha");
//		UtilisateurDAO dao=new UtilisateurDAO();
//		dao.addUser(user);
//		
//		try {
//			System.out.println(dao.isUserExist("moi", "toto"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(dao.findbyId(1).getActiver());
//		dao.changerEtatUser(dao.findbyId(1));
//		System.out.println(dao.findbyId(1).getActiver());
//		
//		syTestUtilisateur();
		
	}

	
}
