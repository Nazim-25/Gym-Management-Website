package com.example.demo.DAO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@Transactional
public class AbonnementDisciplines implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_AbonnementDiscipline;

	@ManyToOne
	@NotNull
	private Abonnement abonnement;

	@ManyToOne
	@NotNull
	private Discipline discipline;
}
