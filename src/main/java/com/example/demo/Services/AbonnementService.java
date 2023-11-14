package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.Discipline;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.AbonnementRepository;

@Service
public class AbonnementService {
	@Autowired
	private AbonnementRepository abonnementRepository;



}
