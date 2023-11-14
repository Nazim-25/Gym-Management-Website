package com.example.demo.DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Abonnement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_abonnement;

	private int tarif;
	private int duree;
	@ManyToOne
	private Discipline discipline;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "abonnement", cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<InscriptionAbonnement> inscriptionAbonnement;

	@OneToMany(mappedBy = "abonnement", cascade = CascadeType.REMOVE)
	private List<Payements> payements;

	@OneToMany(mappedBy = "abonnement", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<AbonnementDisciplines> abonnementDisciplines;

}
