package com.formation.wiki.dao;


<<<<<<< HEAD
import java.util.Date;
=======
import java.sql.SQLException;
>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.formation.wiki.entity.Article;
import com.formation.wiki.entity.Commentaire;
<<<<<<< HEAD
import com.formation.wiki.entity.Utilisateur;



public class CommentaireDAO {
	private EntityManager em;
	private EntityTransaction tx;
	
	public CommentaireDAO() {
		super();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx=em.getTransaction();
	}
	
	public void createComment(Commentaire comment,Article article,Utilisateur user){
		
		tx.begin();
		comment.setArticle(article);
		comment.setUser(user);
		em.persist(comment);
		tx.commit();
	}
	
	public void modifyComment(Commentaire comment,Article article,Utilisateur user){
		
		tx.begin();
		comment.setArticle(article);
		comment.setUser(user);
		em.merge(comment);
		tx.commit();
	}
	
	public void deleteComment(Commentaire comment) {
		
		tx.begin();
		em.remove(comment);
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Commentaire> getAllComments(){
		
		Query query=em.createQuery("select commentaire from Commentaire commentaire");
		List<Commentaire> listeCommentaires=query.getResultList();
		return listeCommentaires;
	}
	
	public Commentaire getCommentById(int id) {

		Query query = em.createQuery("select commentaire from Commentaire commentaire WHERE Id=:id");
		query.setParameter("id", id);
		Commentaire commentaire=(Commentaire)query.getSingleResult();
		return commentaire;

//		List<Commentaire> listeCommentaires=query.getResultList();
//		return listeCommentaires ;
	}
	
	/*
	 * nbCommentPeriod()
	 * JP Alonso
	 */
	public int nbCommentPeriod (Date date_deb, Date date_fin) {
		int nbComment = 0;
		
		Query query = em.createQuery("select count(*) from Commentaire c WHERE commentDate>=:dateDeb AND commentDate <= :dateFin");
		query.setParameter("dateDeb", date_deb);
		query.setParameter("dateFin", date_fin);
		nbComment= (Integer)query.getSingleResult();		
		return nbComment;
		
	}
	
	/*
	 * nbCommentArticle()
	 * JP Alonso
	 */
	public int nbCommentArticle (Article article) {
		int nbComment = 0;
		
		Query query = em.createQuery("select count(*) from Commentaire c WHERE article=:article");
		query.setParameter("article", article);
		nbComment= (Integer)query.getSingleResult();		
		return nbComment;
		
	}
}
=======
import com.formation.wiki.entity.Utilisateur;



public class CommentaireDAO {
	private EntityManager em;
	private EntityTransaction tx;
	
	public CommentaireDAO() {
		super();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("PU_WIKI");
		em = emf.createEntityManager();
		tx=em.getTransaction();
		// TODO Auto-generated constructor stub
	}
	
	//Méthode "Créer Commentaire" : testée et validée par Mary OK
	public void createComment(Commentaire comment,Article article,Utilisateur user){
		
		tx.begin();
		comment.setArticle(article);
		comment.setUser(user);
		em.persist(comment);
		tx.commit();
	}
	
	//Méthode "Modifier Commentaire" : testée et validée par Mary NOK
	public void modifyComment(Commentaire comment,Article article,Utilisateur user){
		
		tx.begin();
		comment.setArticle(article);
		comment.setUser(user);
		em.merge(comment);
		tx.commit();
	}
	
	//Méthode "Supprimer Commentaire" : testée et validée par Mary NOK
	public void deleteComment(Commentaire comment) {
		
		tx.begin();
		em.remove(comment);
		tx.commit();
	}
	
	//Méthode "GetAll Commentaire" : testée et validée par Mary
	@SuppressWarnings("unchecked")
	public List<Commentaire> getAllComments(){
		
		Query query=em.createQuery("select commentaire from Commentaire commentaire");
		List<Commentaire> listeCommentaires=query.getResultList();
		return listeCommentaires;
	}
	
	//Méthode "GetById Commentaire" : testée et validée par Mary
	public Commentaire getCommentById(int id) {

		Query query = em.createQuery("select commentaire from Commentaire commentaire WHERE Id=:id");
		query.setParameter("id", id);
		Commentaire commentaire=(Commentaire)query.getSingleResult();
		return commentaire;

//		List<Commentaire> listeCommentaires=query.getResultList();
//		return listeCommentaires ;
	}
<<<<<<< HEAD
	
	

	// created by SY
		public List<Commentaire> getByPeriod(Date fromDate, Date toDate) {

			Query query=em.createQuery("select c from Commentaire c WHERE c.commentDate BETWEEN :fromDate AND :toDate ORDER BY c.commentDate asc");
			
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			
			List<Commentaire> listeCommentaires=query.getResultList();
			
//			for(Commentaire commentaire : listeCommentaires){
//			
//				System.out.println(commentaire.getCommentDate());
//			} 
			
			return listeCommentaires;
			
		}
=======
>>>>>>> f8ba79caaa2a78bfb9468dbab695d82f4b7c771c
}


>>>>>>> branch 'master' of https://github.com/TahaDevelop/WIKI_MANS.git
