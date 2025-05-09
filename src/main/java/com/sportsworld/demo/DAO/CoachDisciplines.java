package com.example.demo.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class CoachDisciplines {

	public CoachDisciplines(Coach coach, Discipline discipline) {
		super();
		this.coach = coach;
		this.discipline = discipline;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_coachDisciplines;

	@ManyToOne
	private Coach coach;

	@ManyToOne
	private Discipline discipline;
}
