package com.example.demo.DAO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@ToString
public class Abonne implements Serializable {

	public Abonne(String username, String password, String email, String firstname, String lastname, String num_tele,
			java.sql.Date date_nais, Date date_inscription) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.num_tele = num_tele;
		this.date_nais = date_nais;
		this.date_inscription = date_inscription;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_abonne;

	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String num_tele;
	@Temporal(TemporalType.DATE)
	private Date date_nais;
	@Temporal(TemporalType.DATE)
	private Date date_inscription;
	private String genre;

//@JsonProperty(access=Access.WRITE_ONLY)

	@OneToMany(mappedBy = "abonne", cascade = CascadeType.REMOVE)
	private List<CompteBancaire> compteBancaire;

	@OneToMany(mappedBy = "abonne")
	private List<Notification> notification;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "abonne", cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<InscriptionSéance> inscriptionSéance;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "abonne", cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<InscriptionAbonnement> inscriptionAbonnement;

	@OneToMany(mappedBy = "abonne", cascade = CascadeType.REMOVE)
	private List<Payements> payements;

}
