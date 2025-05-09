package com.sportsworld.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sportsworld.demo.DAO.Abonne;
import com.sportsworld.demo.DAO.Abonnement;
import com.sportsworld.demo.DAO.AbonnementDisciplines;
import com.sportsworld.demo.DAO.Coach;
import com.sportsworld.demo.DAO.InscriptionAbonnement;
import com.sportsworld.demo.DAO.InscriptionSéance;
import com.sportsworld.demo.DAO.Planning;
import com.sportsworld.demo.DAO.Seance;
import com.sportsworld.demo.Metier.Operations;
import com.sportsworld.demo.Repository.AbonneRepository;
import com.sportsworld.demo.Repository.AbonnementDisciplinesRepository;
import com.sportsworld.demo.Repository.AbonnementRepository;
import com.sportsworld.demo.Repository.CoachRepository;
import com.sportsworld.demo.Repository.DisciplineRepository;
import com.sportsworld.demo.Repository.InscriptionAbonnementRepository;
import com.sportsworld.demo.Repository.InscriptionSeanceRepository;
import com.sportsworld.demo.Repository.PlanningRepository;
import com.sportsworld.demo.Repository.SeanceRepository;
import com.sportsworld.demo.Services.EmailService;
import com.sportsworld.demo.Services.VisiteurService;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
	}


}

