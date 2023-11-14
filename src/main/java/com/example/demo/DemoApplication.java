package com.example.demo;

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

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.AbonnementDisciplines;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.InscriptionAbonnement;
import com.example.demo.DAO.InscriptionSéance;
import com.example.demo.DAO.Planning;
import com.example.demo.DAO.Seance;
import com.example.demo.Metier.Operations;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.AbonnementDisciplinesRepository;
import com.example.demo.Repository.AbonnementRepository;
import com.example.demo.Repository.CoachRepository;
import com.example.demo.Repository.DisciplineRepository;
import com.example.demo.Repository.InscriptionAbonnementRepository;
import com.example.demo.Repository.InscriptionSeanceRepository;
import com.example.demo.Repository.PlanningRepository;
import com.example.demo.Repository.SeanceRepository;
import com.example.demo.Services.EmailService;
import com.example.demo.Services.VisiteurService;

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

