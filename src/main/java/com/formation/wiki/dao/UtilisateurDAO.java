package com.formation.wiki.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
<<<<<<< HEAD
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

=======
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.formation.wiki.entity.Commentaire;
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
import com.formation.wiki.entity.Role;
import com.formation.wiki.entity.Utilisateur;

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
<<<<<<< HEAD
	// modified by SY : ajoutez Role dois par creationUser 
	
	public void addUser(Utilisateur user) {
		
		tx.begin();
		em.persist(user);
		// Suppression d'un objet em.remove(entity);
		// Mise � jour d'un objet em.merge(entity);
		// R�cup�ration d'un objet em.find(entityClass, primaryKey);
=======
	public void addUser(Utilisateur user) {
		Role role=new Role();
		role.setName("ADMIN");
		user.setRole(role);
		tx.begin();
		em.persist(user);
		// Suppression d'un objet em.remove(entity);
		// Mise ï¿½ jour d'un objet em.merge(entity);
		// Rï¿½cupï¿½ration d'un objet em.find(entityClass, primaryKey);
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
		tx.commit();
	}

	/*
	 * findById(id)
	 */
	public Utilisateur findbyId(int id) {
		Query q = em.createNamedQuery("Utilisateur.findById");
		q.setParameter("id", id);
		Utilisateur user = (Utilisateur) q.getSingleResult();
<<<<<<< HEAD
		System.out.println(user);
=======
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
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
=======
	
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
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
		//	Created by SY : Authentification to create User 
		//	If the user is deactivated else return null
			   
	public String authentificationUser(Utilisateur user) {
=======

		//	Created by SY : Authentification to create User 
		//	If the user is deactivated else return null
			   
	public String AuthentificationUser(Utilisateur user) {
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
	
		String role_user = null;
		
		if (user.getActiver() == true) {
			role_user = user.getRole().getName();
		}
		
		return role_user;
		
	}
	
<<<<<<< HEAD
	// Created by SY 24.08.2017 : Create User 
	// typeUser : "ADMIN" ou "MEMBRE"
	
	public void creationUser(Utilisateur user, String typeUser) {
=======
	// Created by SY : Create User 
	
	public void CreationUser(Utilisateur user, String typeUser) {
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c

		Role role=new Role();
		role.setName(typeUser);
		user.setRole(role);
		addUser(user);
		
	}
	
<<<<<<< HEAD
	// Created by SY 24.08.2017 : Activer User
=======
	// Created by SY : Activer User
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
	
	public void activerUser(Utilisateur user) {
		user.setActiver(true);
		tx.begin();
		em.merge(user);
		tx.commit();
	}
	
<<<<<<< HEAD
	// Created by SY 24.08.2017 : Deactiver User
=======
	// Created by SY : Deactiver User
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
	
	public void deactiverUser(Utilisateur user) {
		user.setActiver(false);
		tx.begin();
		em.merge(user);
		tx.commit();
	}
<<<<<<< HEAD
	
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


=======


	//COMMENCE ICI
	// PULL AND PUSH POUR SAVOIR QUELS PARAMETRES ONT ETE ENTRES PAR SOO YEON ?
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
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
	
<<<<<<< HEAD
	@SuppressWarnings("unchecked")
	public List<Utilisateur> getAllUsers(){
		
		Query query=em.createQuery("select user from Utilisateur user");

		List<Utilisateur> listeUtilisateur=(List<Utilisateur>)query.getResultList();
		return listeUtilisateur;
	}


=======
	public List<Utilisateur> getAllUsers(){
		
		Query query=em.createQuery("select user from Utilisateur user");
		List<Utilisateur> listeUtilisateur=query.getResultList();
		return listeUtilisateur;
	}
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
	/*
	 * UsersWaitingActivation()
	 */
	public List<Utilisateur> UsersWaitingActivation() {
		List<Utilisateur> lusers;
		
		Query q = em.createNamedQuery("Utilisateur.findNotActivated");
		lusers = q.getResultList();
		return lusers;
	}
}
<<<<<<< HEAD

=======
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c

