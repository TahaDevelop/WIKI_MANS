package com.formation.wiki.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;
import com.formation.wiki.dao.WikiEntityManager;

public class UtilisateurDAO {

	private EntityManager em;
	private EntityTransaction tx;

	public UtilisateurDAO() {
		em = WikiEntityManager.getInstance().getEntityManager();
		tx = em.getTransaction();
	}

	/*
	 * isUserExist(login, mdp)
	 */
	public String isUserExist(String login, String mdp) throws SQLException, TimeoutException,NoResultException {

		String role_user = null;
		Query q = em.createQuery(
				"select user from Utilisateur user where user.login = :login and " + "user.password= :mdp");
		q.setParameter("login", login);
		q.setParameter("mdp", mdp);
		Utilisateur user_exist = (Utilisateur) q.getSingleResult();
		if (user_exist != null) {
			role_user = user_exist.getRole().getName();
		}
		return role_user;
	}

	/*
	 * addUser(user) : ajout d'un utilisateur
	 */

	// modified by SY : ajoutez Role dois par creationUser 
	
	public void addUser(Utilisateur user) {
		
		tx.begin();
		em.persist(user);

		tx.commit();
	}

	/*
	 * findById(id)
	 */
	public Utilisateur findbyId(int id) {
		Query q = em.createNamedQuery("Utilisateur.findById");
		q.setParameter("id", id);
		Utilisateur user = (Utilisateur) q.getSingleResult();
		return user;
	}

	/*
	 * changeEtatUser(user)
	 */
	public void changerEtatUser(Utilisateur user) {
		if (user.getActiver() == false) {
			user.setActiver(true);
		} else {
			user.setActiver(false);
		}
		tx.begin();
		em.merge(user);
		tx.commit();
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
	
=======
>>>>>>> c41b79f37927497694129f75eebb1bc0c1225e6a
	/** Autheur: Sahobi
	 * methode getUserById
	 */
	public Utilisateur getUserById(int id) {	
		Utilisateur user = null;
		Query q = em.createNamedQuery("Utilisateur.getById");
		q.setParameter("id", id);
		user = (Utilisateur) q.getSingleResult();
		System.out.println("l'article dont l'id " + id + " est : " + user);
		return user;
	}
	
	/** Autheur: Sahobi
	 *  methode getUserByLogin
	 */
	public Utilisateur getUserByLogin(String login) {	
		Utilisateur user = null;
		Query q = em.createNamedQuery("Utilisateur.getByLogin");
		q.setParameter("login", login);
		user = (Utilisateur) q.getSingleResult();
		System.out.println("l'utilisateur  dont le login " + login + " est : " + user);
		return user;
	}

	
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
		//	Created by SY : Authentification to create User 
		//	If the user is deactivated else return null
			   
	public String authentificationUser(Utilisateur user) {

	
		String role_user = null;
		
		if (user.getActiver() == true) {
			role_user = user.getRole().getName();
		}
		
		return role_user;
		
	}
	

	// Created by SY 24.08.2017 : Create User 
	// typeUser : "ADMIN" ou "MEMBRE"


	public void creationUser(Utilisateur user, String typeUser) {

		Role role=new Role();
		role.setName(typeUser);
		user.setRole(role);
		addUser(user);
		
	}
	

	
	public void activerUser(Utilisateur user) {
		user.setActiver(true);
		tx.begin();
		em.merge(user);
		tx.commit();
	}
	

	
	public void deactiverUser(Utilisateur user) {
		user.setActiver(false);
		tx.begin();
		em.merge(user);
		tx.commit();
	}

<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
	//COMMENCE ICI
	// PULL AND PUSH POUR SAVOIR QUELS PARAMETRES ONT ETE ENTRES PAR SOO YEON ?
=======
	
	// Created by SY 25.08.2017 : Increase count by 1 for this abuser
	
	public void addReportAbuser (Utilisateur user) {
		
		user.setReportAbuser(user.getReportAbuser()+1);
		tx.begin();
		em.merge(user);
		tx.commit();
		
	}
	
	// Created by SY 25.08.2017 : Check is this user is an abuser 
	// condition is more than 10 times reported
	
	public boolean checkIsAbuser (Utilisateur user) {
		
		boolean isAbuser = false; 
		
		if (user.getReportAbuser() > 10) {
			isAbuser = true; 
		}
		
		return isAbuser;
	}

>>>>>>> c41b79f37927497694129f75eebb1bc0c1225e6a
	public void modifyUser(String login, String mdp, Role role, Utilisateur user) {
	
		tx.begin();
		user.setLogin(login);
		user.setPassword(mdp);
		user.setRole(role);
		em.merge(user);
		tx.commit();
	}

	public void deleteUser(Utilisateur user) {
	
		tx.begin();
		em.remove(user);
		tx.commit();
	}
	


	public List<Utilisateur> getAllUsers(){
		
		Query query=em.createQuery("select user from Utilisateur user");
		List<Utilisateur> listeUtilisateur=query.getResultList();
		return listeUtilisateur;
	}
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
=======

>>>>>>> c41b79f37927497694129f75eebb1bc0c1225e6a
	/*
	 * UsersWaitingActivation()
	 * auteur : JP Alonso
	 */
	@SuppressWarnings("unchecked")
	public List<Utilisateur> UsersWaitingActivation() {
		List<Utilisateur> lusers = new ArrayList<Utilisateur>();
		
		Query q = em.createNamedQuery("Utilisateur.findNotActivated");
		lusers = (List<Utilisateur>) q.getResultList();
		return lusers;
<<<<<<< HEAD
<<<<<<< HEAD

	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> UsersBolqued() {
		List<Utilisateur> lusers = new ArrayList<Utilisateur>();
		
		Query q = em.createNamedQuery("Utilisateur.findBloqued");
		lusers = (List<Utilisateur>) q.getResultList();
		return lusers;

=======
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
	}
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
=======
	public void addReportAbuser (Utilisateur user) {
		
		user.setReportAbuser(user.getReportAbuser()+1);
		tx.begin();
		em.merge(user);
		tx.commit();
		
	}
	
	// Created by SY 25.08.2017 : Check is this user is an abuser 
	// condition is more than 10 times reported
	
	public boolean checkIsAbuser (Utilisateur user) {
		
		boolean isAbuser = false; 
		
		if (user.getReportAbuser() > 10) {
			isAbuser = true; 
		}
		
		return isAbuser;
	}
>>>>>>> c5b63cbf927fdb223307264664f82b7dfb627574
=======
	}

>>>>>>> c41b79f37927497694129f75eebb1bc0c1225e6a
}


