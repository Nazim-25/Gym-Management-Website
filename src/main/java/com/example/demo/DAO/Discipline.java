package com.example.demo.DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discipline implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_discipline;

	private String nomDiscipline;
	private String description;

	@OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
	private List<Abonnement> abonnement;

	@OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
	private List<CoachDisciplines> coachDisciplines;

	@OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
	private List<Offre> offre;

	@OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
	private List<AbonnementDisciplines> AbonnementDisciplines;

}
