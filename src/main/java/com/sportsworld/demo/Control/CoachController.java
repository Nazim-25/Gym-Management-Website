package com.example.demo.Control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.DataLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.CoachDisciplines;
import com.example.demo.DAO.CompteBancaire;
import com.example.demo.DAO.Discipline;
import com.example.demo.DAO.Gerant;
import com.example.demo.DAO.Mdp;
import com.example.demo.DAO.Planning;
import com.example.demo.DAO.Seance;
import com.example.demo.Metier.Operations;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.CoachRepository;
import com.example.demo.Repository.DisciplineRepository;
import com.example.demo.Repository.PlanningRepository;
import com.example.demo.Repository.SeanceRepository;
import com.example.demo.Services.EmailService;
import com.example.demo.Services.GerantService;
import com.example.demo.Services.VisiteurService;

@Controller
public class CoachController {
	// Repositories injected for database operations
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private GerantService gerantService;

	@Autowired
	private AbonneRepository abonneRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;

	@Autowired
	private PlanningRepository planningRepository;
	@Autowired
	private VisiteurService visiteurService;
	Operations op = new Operations();
	@Autowired
	private EmailService emailService;

	// Method to show the coach's dashbord
	@GetMapping(value = "/espacecoach")
	public String ShowespaceCoach(HttpSession session) {

		if ((String) session.getAttribute("CoachConnected") != null) {
			return "EspaceCoach";
		} else {
			return "redirect:/index";
		}

	}
	// Method to redirect to the coach's dashbord
	@GetMapping(value = "ModifierInfoCoach/espacecoach")
	public String ShowespaceCoach3() {
		return "redirect:/espacecoach";
	}
	// Method to show the coach's information for editing
	@GetMapping("ModifierInfoCoach/{id_coach}")
	public ModelAndView showEditAbonnePage(@PathVariable(name = "id_coach") int id_coach, HttpSession session) {
		if ((String) session.getAttribute("CoachConnected") != null) {
			session.removeAttribute("success");
			ModelAndView ModifierInfoCoach = new ModelAndView("ModifierInfoCoach");
			Coach coach = coachRepository.findById(id_coach);
			session.setAttribute("id_gerant", coach.getId_coach());

			System.out.println(coach.getId_coach());
			ModifierInfoCoach.addObject("coach", coach);
			return ModifierInfoCoach;

		} else {
			return new ModelAndView("redirect:/index");
		}

	}

	// Method to save coach information after editing
	@PostMapping(value = "/SaveCoachInfo")
	public String SaveGerant(@ModelAttribute("coach") Coach coach, HttpSession session) {
		if ((String) session.getAttribute("CoachConnected") != null) {
			Coach c1 = coachRepository.findById((int) session.getAttribute("id_coach"));
			int id_coach = c1.getId_coach();
			System.out.println(id_coach);
			String email = c1.getEmail();
			System.out.println(email);
			boolean Y = gerantService.UserExist2(coach, email);
			System.out.println(Y);
			if (Y) {
				session.setAttribute("EmailCoachExiste", " Email existe Déja  ");
				return "redirect:/ModifierInfoCoach/" + id_coach + "";
			} else {
				coach.setId_coach(id_coach);
				coach.setPassword(c1.getPassword());
				coach.setDate_nais(new java.sql.Date(coach.getDate_nais().getTime() + (1000 * 60 * 60 * 24)));
				coach.setDate_recrutement(c1.getDate_recrutement());
				coach.setSalaire(c1.getSalaire());
				coach.setExperience(c1.getExperience());
				coachRepository.save(coach);
				session.setAttribute("CoachModifier", " les informations ont été Modifiées  ");
//				if(!email.equals(coach.getEmail())) {
//				     String subject = "SPORTS WORLD" ;
//					 String message ="cher coach "+coach.getFirstname()+" suit au modification operer par vos soins votre nouvelle adresse mail est la suivant : "+coach.getEmail();
//					 String to=coach.getEmail();
//					 emailService.sendEmail(subject,message,to);
//				        	 }
				return "EspaceCoach";
			}

		} else {
			return "redirect:/index";
		}

	}

	// Method to show the form for changing the coach's password
	@GetMapping(value = "/ModifierInfoCoach/ChangePasswordCoach")
	public ModelAndView showForm(HttpSession session) {

		if ((String) session.getAttribute("CoachConnected") != null) {
			return new ModelAndView("ModifierMotDePasseCoach", "Mdp", new Mdp());

		} else {
			return new ModelAndView("redirect:/index");
		}

	}

	// Method to save the new coach password
	@PostMapping(value = "/SaveNewCoachPassword")
	public String ChangeMdp(@ModelAttribute("Mdp") Mdp Mdp, HttpSession session) {

		if ((String) session.getAttribute("CoachConnected") != null) {
			String mdpActuel = Mdp.getMdpActuel();
			String mdp1 = Mdp.getMdp1();
			String mdp2 = Mdp.getMdp2();
			Coach coach = coachRepository.findById((int) session.getAttribute("id_coach"));
			if (coach.getPassword().equals(mdpActuel)) {
				if (mdp1.equals(mdp2)) {
					coach.setPassword(mdp1);
					coachRepository.save(coach);
					session.setAttribute("PasswordCoachChanged", "le mot de passe a été modifié avec succès");
					return "EspaceCoach";
				} else {
					session.setAttribute("NewCoachMdpWrong",
							"la confirmation ne correspond pas avec le nouveau mot de passe");
					return "ModifierMotDePasseCoach";
				}

			} else {
				session.setAttribute("PasswordCoachWrong", "le mot de passe est faux");
				return "ModifierMotDePasseCoach";
			}
		} else {
			return "redirect:/index";
		}
	}

	// Method to display the coach's planned sessions
	@GetMapping("AffichePlanningCoach")
	public String AfficherPlanning(@ModelAttribute("Discipline") Discipline Discipline, HttpSession session,
			Model model) throws SQLException {
		if ((String) session.getAttribute("CoachConnected") != null) {

			List<Seance> ListSeances = visiteurService.getSeances();
			model.addAttribute("ListSeances", ListSeances);
			session.setAttribute("ListSeances", ListSeances);

			Coach coach1 = coachRepository.findById((int) session.getAttribute("id_coach"));
			List<CoachDisciplines> listDisciplines = coach1.getCoachDisciplines();
			model.addAttribute("listDisciplines", listDisciplines);

			List<Integer> seances = op.SeanceAbonné();
			List<Seance> ListSeancesReserverCoach = new ArrayList<Seance>();

			List<Integer> se = new ArrayList<Integer>();

			for (int seance : seances) {
				seanceRepository.getSeancesCoach(coach1).forEach(a -> {
					if (seance == a.getId_seance()) {
						se.add(seance);

					}
				});

			}

			for (int seance : se) {
				Seance c = seanceRepository.findById(seance);
				ListSeancesReserverCoach.add(c);

			}

			model.addAttribute("ListSeancesReserverCoach", ListSeancesReserverCoach);

			return "PlanningCoach";
		} else {
			return "redirect:/index";
		}
	}
	// Method to show the form for adding a new session
	@GetMapping("AjouterSeance/{date}/{time}")
	public String ShowAjouterSeance(@ModelAttribute("Discipline") Discipline Discipline,
			@PathVariable(name = "date") LocalDate date, @PathVariable(name = "time") LocalTime time,
			HttpSession session, Model model) throws SQLException {
		if ((String) session.getAttribute("CoachConnected") != null) {
			session.setAttribute("a", 0);

			List<Seance> ListSeances = visiteurService.getSeances();
			model.addAttribute("ListSeances2", ListSeances);
			session.setAttribute("date", date);
			session.setAttribute("time", time);
//			 Coach coach1=coachRepository.findById((int)session.getAttribute("id_coach"));
//			 
//			 List <CoachDisciplines> listDisciplines=coach1.getCoachDisciplines();
//			 model.addAttribute("listDisciplines",listDisciplines);

			return "redirect:/AffichePlanningCoach";
		} else {
			return "redirect:/index";
		}
	}
	// Method to save a new session
	@PostMapping("SaveNewSeance")
	public String AjouterSeance(@ModelAttribute("Discipline") Discipline Discipline, HttpSession session, Model model)
			throws SQLException {
		if ((String) session.getAttribute("CoachConnected") != null) {

			LocalDate date = (LocalDate) session.getAttribute("date");
			LocalTime time = (LocalTime) session.getAttribute("time");
			int id_coach = (int) session.getAttribute("id_coach");
			Coach coach = coachRepository.findById(id_coach);
			Discipline d = disciplineRepository.findById(Discipline.getId_discipline());
			// Planning planning =planningRepository.findById(1);

			int lastPlanning = op.LastPlanning();
			Planning planning = planningRepository.findById(lastPlanning);

			int beforeLast = op.BeforeLastPlanning();
			Planning planning2 = planningRepository.findById(beforeLast);

			LocalDate beforeLastDin = planning2.getDateFin();

			long day2 = ChronoUnit.DAYS.between(date, beforeLastDin);
			long month2 = ChronoUnit.MONTHS.between(date, beforeLastDin);
			long year2 = ChronoUnit.YEARS.between(date, beforeLastDin);

			int id_planning = 1;
			if (day2 >= 0 && month2 == 0 && year2 == 0) {
				id_planning = beforeLast;

			} else {
				id_planning = lastPlanning;
			}

			Planning p = planningRepository.findById(id_planning);

			Seance s = new Seance();
			s.setDiscipline(d);
			s.setDate(date.plusDays(1));
			s.setHeureDebut(time);
			s.setHeureFin(time.plusHours(2));
			s.setPlanning(p);
			s.setCoach(coach);
			seanceRepository.save(s);

			p.setDateDebut(p.getDateDebut().plusDays(1));
			p.setDateFin(p.getDateFin().plusDays(1));
			p.setNbrSeance(p.getNbrSeance() + 1);
			planningRepository.save(p);

			session.setAttribute("SeanceAdded", "La sénace à été ajoutée");
			return "redirect:/AffichePlanningCoach";

		} else {
			return "redirect:/index";
		}
	}
	// Method to delete a reservation made by a coach
	@GetMapping(value = "deleteReservationCoach/{id_seance}")
	public String DeletSeanceAbonne(@PathVariable(name = "id_seance") int id_seance, HttpSession session) {
		if ((String) session.getAttribute("CoachConnected") != null) {

			int id_coach = (int) session.getAttribute("id_coach");
			seanceRepository.findAll().forEach(a -> {
				if ((a.getCoach().getId_coach() == id_coach) && (a.getId_seance() == id_seance)) {
					seanceRepository.delete(a);
				}
			});

			session.setAttribute("ReservationDeleted", "La séance programmé a été supprimée");
			return "redirect:/AffichePlanningCoach";
		} else {
			return "redirect:/index";
		}
	}

}
