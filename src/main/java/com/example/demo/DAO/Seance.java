package com.example.demo.DAO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seance implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_seance;

	private LocalDate date;
	// @Temporal(TemporalType.TIMESTAMP)
	private LocalTime HeureDebut;
	// @Temporal(TemporalType.TIMESTAMP)
	private LocalTime HeureFin;

	@ManyToOne
	@NotNull
	private Planning planning;

	@OneToOne
	@NotNull
	private Discipline discipline;
	@OneToOne
	@NotNull
	private Coach coach;

	@OneToMany(mappedBy = "seance", cascade = CascadeType.REMOVE)
	private List<InscriptionSéance> inscriptionSéance;

}
