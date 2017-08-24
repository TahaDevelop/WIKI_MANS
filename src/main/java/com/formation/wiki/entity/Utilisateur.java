package com.formation.wiki.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
@NamedQueries({
	@NamedQuery(name="Utilisateur.findById",query="SELECT u FROM Utilisateur u WHERE u.idUser= :id"),
	@NamedQuery(name="Utilisateur.findByLogin",query="SELECT u FROM Utilisateur u WHERE u.login= :login")

})
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idUser;
	@Column(length=20,nullable=false,unique=true)
	private String login;
	@Column(length=25,nullable=false)
	private String password;
	@Column(name="nom_utilisateur")
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	
	private Boolean activer;
	//Mapping entre Utilisateur <--> Article
	@OneToMany(fetch=FetchType.LAZY)
	private List<Article> articles;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Commentaire> commentaires;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Role role;
	
	public Utilisateur() {
	}
	
	public Boolean getActiver() {
		return activer;
	}



	public void setActiver(Boolean activer) {
		this.activer = activer;
	}



	public List<Article> getArticles() {
		return articles;
	}



	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public List<Commentaire> getCommentaires() {
		return commentaires;
	}



	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}

	public Utilisateur(int idUser, String login, String password, String nom, String prenom, String email, String tel) {
		super();
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUser=" + idUser + ", login=" + login + ", password=" + password + ", nom=" + nom
				+ ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + "]";
	}
	
	
	
	
	
	
}
