package com.example.demo.Control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.Abonnement;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.AbonnementRepository;

@RestController
public class rest {

	@Autowired
	private AbonnementRepository abonnementRepository;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public List<Abonnement> index() {

		return abonnementRepository.findByTarif(4000);
	}
	// <---------------------------------------------------------------->
}
