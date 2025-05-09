package com.example.demo.Control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.AbonnementsInforamtions;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.CoachDisciplines;
import com.example.demo.DAO.Discipline;
import com.example.demo.DAO.Seance;
import com.example.demo.Repository.AbonnementRepository;
import com.example.demo.Repository.CoachDisciplinesRepository;
import com.example.demo.Repository.CoachRepository;
import com.example.demo.Repository.DisciplineRepository;
import com.example.demo.Services.VisiteurService;
//Controller managing visitor-related functionalities
@Controller
public class VisiteurController {

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private AbonnementRepository abonnementRepository;

	@Autowired
	private CoachDisciplinesRepository coachDisciplinesRepository;

	@Autowired
	private VisiteurService visiteurService;

	// Handles the request to display disciplines
	@GetMapping("/ConsulterDisciplines")
	public String AfficherDisciplines(Model model) {
		// Retrieves and adds the list of disciplines to the model
		List<Discipline> listDisciplines = disciplineRepository.findAll();
		model.addAttribute("listDisciplines", listDisciplines);

		return "AfficheDisciplines";// Returns the corresponding view
	}
	
	// Handles the request to display subscriptions
	@GetMapping("/ConsulterAbonnements")
	public String AfficheAbonnements(Model model) {

		List<Abonnement> listAbonnements = abonnementRepository.findAll();
		model.addAttribute("listAbonnements2", listAbonnements);

		return "AfficheAbonnements";// Returns the corresponding view
	}

	// Handles the request to display coaches
	@GetMapping("/ConsulterCoachs")
	public String AfficherCoachs(Model model) {
		List<Coach> listCoachs = coachRepository.findAll();
		model.addAttribute("listCoachs", listCoachs);

		List<Discipline> listDisciplines2 = disciplineRepository.findAll();
		model.addAttribute("listDisciplines2", listDisciplines2);

		List<CoachDisciplines> listCD = coachDisciplinesRepository.findAll();
		model.addAttribute("listCD2", listCD);

		return "AfficheCoachs";// Returns the corresponding view
	}

	// Handles the request to display the planning
	@GetMapping("/ConsulterPlanning")
	public String AfficherPlanning(Model model, HttpSession session) {

		List<Seance> ListSeances = visiteurService.getSeances();
		model.addAttribute("ListSeances", ListSeances);
		session.setAttribute("ListSeances", ListSeances);
		return "AffichePlanning";// Returns the corresponding view
	}

}
