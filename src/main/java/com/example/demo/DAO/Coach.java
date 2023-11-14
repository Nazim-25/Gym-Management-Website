package com.example.demo.DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Coach implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_coach;
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String num_tele;
	@Temporal(TemporalType.DATE)
	private Date date_nais;
	private Date date_recrutement;
	private int salaire;
	private String genre;
	private int age;
	private int experience;

	@OneToMany(mappedBy = "coach")
	private List<Notification> notification;

	@OneToMany(mappedBy = "coach", cascade = CascadeType.REMOVE)
	private List<CoachDisciplines> coachDisciplines;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "coach", cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Seance> seance;

}
