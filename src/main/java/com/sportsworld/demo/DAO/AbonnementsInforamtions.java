package com.example.demo.DAO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbonnementsInforamtions {

	private int id_abonnement;
	private int tarif;
	private int duree;
	private String nomDiscipline;
	private int id_discipline;

}
