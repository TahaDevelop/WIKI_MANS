package com.formation.wiki;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import com.formation.wiki.dao.UtilisateurDAO;
import com.formation.wiki.entity.Utilisateur;


public class Main {

	public static void main(String[] args) {
		
		//Test création et insertion USER OK
		Utilisateur user=new Utilisateur();
		//Taha COmment GIT
		user.setLogin("Taha");
		user.setPassword("taa");
		user.setActiver(true);	
		user.setEmail("belbattach.taha");
		UtilisateurDAO dao=new UtilisateurDAO();
		dao.addUser(user);
		
		try {
			System.out.println(dao.isUserExist("taha", "taha"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		System.out.println(dao.findbyId(1).getActiver());
		dao.changerEtatUser(dao.findbyId(1));
		System.out.println(dao.findbyId(1).getActiver());
	}

}
