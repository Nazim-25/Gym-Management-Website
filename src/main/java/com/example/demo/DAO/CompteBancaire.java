package com.example.demo.DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class CompteBancaire implements Serializable {

	@Id
	private String No_Carte;
	private String pin;
	private double solde;
	@ManyToOne
	private Abonne abonne;

	@OneToMany(mappedBy = "compteBancaire")
	private List<Payements> payements;

}
