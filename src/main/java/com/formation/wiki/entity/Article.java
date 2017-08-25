package com.formation.wiki.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Article  implements Serializable{

	private static final long serialVersionUID = 1L;
//	attentionrtzerze
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	private Integer reportNumber;
	private String categorie;
	private String keywords;
	
	public Integer getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(Integer reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Temporal(TemporalType.DATE)
	private Date publishDate;
	
	private String content;
	
	@Lob
	@Column(name="PHOTO")
	private Serializable photo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Utilisateur user;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Commentaire> commentaires;
	
	@OneToOne
	private Statut statut;
	
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Serializable getPhoto() {
		return photo;
	}

	public void setPhoto(Serializable photo) {
		this.photo = photo;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
