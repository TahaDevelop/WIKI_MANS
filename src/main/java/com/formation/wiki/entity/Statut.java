package com.formation.wiki.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Statut implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Boolean published;
	
	private Boolean waitingforvalidation;
	
	private Boolean reportedasabused;

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Boolean getWaitingforvalidation() {
		return waitingforvalidation;
	}

	public void setWaitingforvalidation(Boolean waitingforvalidation) {
		this.waitingforvalidation = waitingforvalidation;
	}

	public Boolean getReportedasabused() {
		return reportedasabused;
	}

	public void setReportedasabused(Boolean reportedasabused) {
		this.reportedasabused = reportedasabused;
	}

	public Statut() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
