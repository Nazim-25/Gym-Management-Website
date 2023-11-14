package com.example.demo.Control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.AbonnementDisciplines;
import com.example.demo.DAO.AbonnementsInforamtions;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.CoachDisciplines;
import com.example.demo.DAO.Discipline;
import com.example.demo.DAO.Gerant;
import com.example.demo.DAO.InscriptionAbonnement;
import com.example.demo.DAO.InscriptionSéance;
import com.example.demo.DAO.Mdp;
import com.example.demo.DAO.Planning;
import com.example.demo.DAO.Recherche;
import com.example.demo.DAO.Seance;
import com.example.demo.Metier.Operations;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.AbonnementDisciplinesRepository;
import com.example.demo.Repository.AbonnementRepository;
import com.example.demo.Repository.CoachDisciplinesRepository;
import com.example.demo.Repository.CoachRepository;
import com.example.demo.Repository.DisciplineRepository;
import com.example.demo.Repository.GerantRepository;
import com.example.demo.Repository.InscriptionSeanceRepository;
import com.example.demo.Repository.PlanningRepository;
import com.example.demo.Repository.SeanceRepository;
import com.example.demo.Services.EmailService;
import com.example.demo.Services.GerantService;
import com.example.demo.Services.VisiteurService;

@Controller
public class GerantController {

	boolean b = false;
	Operations op = new Operations();

	// Repositories injected for database operations
	@Autowired
	private EmailService emailService;
	@Autowired
	private AbonneRepository abonneRepository;

	@Autowired
	private GerantService gerantService;

	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private CoachDisciplinesRepository coachDisciplinesRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private AbonnementRepository abonnementRepository;
	@Autowired
	private AbonnementDisciplinesRepository abonnementDisciplinesRepository;

	@Autowired
	private InscriptionSeanceRepository inscriptionSeanceRepository;

	@Autowired
	private GerantRepository gerantRepository;

	@Autowired
	private PlanningRepository planningRepository;

	@Autowired
	private VisiteurService visiteurService;

	// Redirect to the Coach dashboard
	@GetMapping(value = "/espacegerant")
	public String ShowespaceGerant(HttpSession session) {

		if ((String) session.getAttribute("GerantConnected") != null) {

			session.removeAttribute("AbonneAdded");
			return "EspaceGerant";
		} else {
			return "redirect:/index";
		}
	}

	// Get displays the list of Abonnés
	@GetMapping("/AfficheAbonnes")
	public String AfficheListAbonnées(@ModelAttribute("Recherche") Recherche Recherche, Model model,
			HttpSession session) {
		// Check if Gerant is connected
		if ((String) session.getAttribute("GerantConnected") != null) {

			String keyword = Recherche.getKeyword();
			if (keyword != null) {
				// Search for Abonnés based on keyword
				model.addAttribute("listAbonnés", gerantService.findByKeyword(keyword));
			} else {
				// Retrieve all Abonnés (Subscribers)
				List<Abonne> listAbonnés = abonneRepository.findAll();
				model.addAttribute("listAbonnés", listAbonnés);
			}

			return "AfficheAbonnés";
		} else {
			// Redirect to the index page if Gerant is not connected
			return "redirect:/index";
		}
	}

	// Deletes an Abonné (Subscriber)
	@GetMapping(value = "/delete/{id}")
	public String deletestudent(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			Abonne abonne = abonneRepository.findById(id);
			abonneRepository.deleteById(id);
			session.setAttribute("AbonneSupprimé", "L'abonné a été supprimé");
			
			// Send email notification to the deleted Abonné
//	       	String subject = "SPORTS WORLD" ;
//	    	String message ="Cher client "+abonne.getFirstname()+" nous vous informons que votre compte à été fermé";
//			String to=abonne.getEmail(); 
//			emailService.sendEmail(subject,message,to);

			return "redirect:/AfficheAbonnes";

		} else {
			// Redirect to the index page if Gerant is not connected
			return "redirect:/index";
		}
	}
	// Displays the edit Abonne (Subscriber) page
	@GetMapping(value = "edit/{id}")
	public ModelAndView showEditAbonnePage2(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			ModelAndView ModifierAbonne = new ModelAndView("ModifierAbonne");
			Abonne ab = abonneRepository.findById(id);
			session.setAttribute("id", ab.getId_abonne());
			ModifierAbonne.addObject("abonne", ab);
			return ModifierAbonne;
		} else {
			// Redirect to the index page if Gerant is not connected
			return new ModelAndView("redirect:/index");
		}
	}

	// save the new informations of the abonné (subscriber) in the database
	@PostMapping(value = "/Save")
	public String saveAbonne(@ModelAttribute("abonne") Abonne abonne, HttpSession session) {
		// Get the id of the Abonne from the session
		int id_abonne = (int) session.getAttribute("id");
		// Find the Abonne by id
		Abonne ab = abonneRepository.findById(id_abonne);
		// Get the email of the Abonne
		String email = ab.getEmail();
		 // Check if the updated email already exists for another user
		boolean Y = gerantService.UserExistAbonne(abonne, email);
		if (Y) {
			// If the email exists, set an attribute and redirect to the edit page
			session.setAttribute("EmailAbonneExiste2", " L'email existe Déja  ");
			return "redirect:/edit/" + id_abonne + "";
		} else {
			// If the email doesn't exist, update the Abonne and set a success message
			abonne.setId_abonne(id_abonne);
			op.ModifierAbonne(abonne);
			session.setAttribute("AbonneModifier2", " les informations ont été Modifiées  ");
			// Send an email if the email has been changed
//	        	 if(!email.equals(abonne.getEmail())) {
//		        	 String subject = "SPORTS WORLD" ;
//					 String message ="Cher client "+abonne.getFirstname()+" suit a votre demande votre nouvelle adresse mail est la suivant : "+abonne.getEmail();
//					 String to=abonne.getEmail();
//					 
//					emailService.sendEmail(subject,message,to);
//	        	 }
			return "redirect:/AfficheAbonnes";
		}

	}
	// Show the view for adding a new Abonne
	@GetMapping(value = "/ajouterAbonne")
	public ModelAndView showAjouterAbonne(HttpSession session) {
		// Check if a Gerant is connected
		if ((String) session.getAttribute("GerantConnected") != null) {
			return new ModelAndView("ajouterAbonne", "Abonne", new Abonne());
		} else {
			// If not connected, redirect to the index page
			return new ModelAndView("redirect:/index");
		}
	}

	// Add a new Abonne
	@PostMapping(value = "/AddAbonne")
	public String AddAbonne(@ModelAttribute("Abonne") Abonne Abonne, HttpSession session) throws SQLException {

		boolean Y = gerantService.UserExist2(Abonne);
		if (Y) {
			session.setAttribute("SameEmail", "L'email existe déja   ");
			return "ajouterAbonne";

		} else {
			op.addUser(Abonne);
			session.setAttribute("AbonneAdded", " L'abonné a été ajouté  ");
//				 		 
//			String subject = "SPORTS WORLD" ;
//			String message ="Cher client "+Abonne.getFirstname()+" suit a votre demande nous vous informons qu'un compte a votre nom à été crée sur notre site";
//			String to=Abonne.getEmail();						 
//			emailService.sendEmail(subject,message,to);

			return "redirect:/AfficheAbonnes";
		}

	}
	// Display the list of Coachs
	@GetMapping(value = "/AfficheCoachs")
	public String AfficheListCoachs(@ModelAttribute("Recherche") Recherche Recherche, Model model,
			HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			String keyword = Recherche.getKeyword();
			if (keyword != null) {
				List<CoachDisciplines> listCD = coachDisciplinesRepository.findAll();
				model.addAttribute("listCoachD", listCD);

				List<Discipline> listDisciplines2 = disciplineRepository.findAll();
				model.addAttribute("listOfDisciplines", listDisciplines2);

				model.addAttribute("listOfCoachs", gerantService.findByKeyword2(keyword));
			} else {
				List<Coach> listCoachs = coachRepository.findAll();
				model.addAttribute("listOfCoachs", listCoachs);

				List<CoachDisciplines> listCD = coachDisciplinesRepository.findAll();
				model.addAttribute("listCoachD", listCD);

				List<Discipline> listDisciplines2 = disciplineRepository.findAll();
				model.addAttribute("listOfDisciplines", listDisciplines2);
			}
			return "ListeCoachs";
		} else {
			return "redirect:/index";
		}
	}
	
	// Redirect to the Coach list page
	@GetMapping(value = "/editCoach/AfficheCoachs")
	public String RedirectAfficheListCoachs(@ModelAttribute("Recherche") Recherche Recherche, Model model,
			HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			return "redirect:/AfficheCoachs";

		} else {
			return "redirect:/index";
		}
	}
	// Delete a Coach
	@GetMapping(value = "/deleteCoach/{id}")
	public String deleteCoach(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			System.out.println();
			session.setAttribute("CoachDeleted", " Le coach a été supprimé  ");
			Coach coach = coachRepository.findById(id);
			coachRepository.deleteById(id);


//		    String subject = "SPORTS WORLD" ;
//			String message ="Cher coach "+coach.getFirstname()+" nous vous informons que votre compte à été fermé";
//			String to=coach.getEmail();		 
//			emailService.sendEmail(subject,message,to);
			return "redirect:/AfficheCoachs";

		} else {
			return "redirect:/index";
		}
	}
	// Show the page for editing a Coach
	@GetMapping(value = "editCoach/{id}")
	public ModelAndView showEditCoachPage(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			ModelAndView ModifierAbonne = new ModelAndView("ModifierCoach");
			Coach coach = coachRepository.findById(id);
			session.setAttribute("EmailCoach", coach.getEmail());
			session.setAttribute("id_coach2", coach.getId_coach());
			ModifierAbonne.addObject("coach2", coach);
			return ModifierAbonne;
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	// Show the page for adding a new Coach
	@GetMapping(value = "/ajouterCoach")
	public ModelAndView showAjouterCoach(HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			return new ModelAndView("ajouterCoach", "Coach", new Coach());
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	// Add Coach Disciplines
	@GetMapping(value = "AjouterCoachDisciplines")
	public String AddCoachDiscipline(@ModelAttribute("Coach") Coach Coach, HttpSession session, Model model)
			throws SQLException {
		if ((String) session.getAttribute("GerantConnected") != null) {
			List<Discipline> listDisciplines = disciplineRepository.findAll();
			model.addAttribute("listDisciplines", listDisciplines);
			return "AjouterCoachDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	
	//handle the addition of a new coach
	@PostMapping(value = "/AddCoach")
	public String AddCoach(@ModelAttribute("Coach") Coach Coach, HttpSession session, Model model) throws SQLException {
		b = false;
		model.addAttribute(Coach);
		b = gerantService.UserExist(Coach);
		if (b) {
			session.setAttribute("SameEmailCoach", "L'email existe déja   ");
			return "redirect:/ajouterCoach";

		} else {
			gerantService.addCoach(Coach);
			Coach c = coachRepository.findByEmail(Coach.getEmail());
						
//	  	    String subject = "SPORTS WORLD" ;
//			String message ="Cher coach "+c.getFirstname()+" nous vous informons qu'un compte a votre nom à été crée sur notre site";
//			String to=Coach.getEmail();
//			emailService.sendEmail(subject,message,to);

			session.setAttribute("id_coach1", c.getId_coach());
			session.setAttribute("emailcoach", c.getEmail());

			List<Discipline> listDisciplines = disciplineRepository.findAll();
			model.addAttribute("listDisciplines", listDisciplines);
			return "AjouterCoachDisciplines";
		}

	}
	// Variable to track whether a discipline exists
	boolean Dc = false;

	//Adding a discipline to a coach
	@GetMapping(value = "/AddDiscipline/{id}")
	public String showAddDisciplinePage(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			Coach coach = coachRepository.findById((int) session.getAttribute("id_coach1"));

			boolean D = gerantService.addDisciplineCoach(coach, id);
			if (D) {
				session.setAttribute("DisciplineExiste", "Cette discipline existe déja ");
			} else {
				session.setAttribute("DisciplineAjouter", "La discipline a été  ajouté  ");
			}
			return "redirect:/AjouterCoachDisciplines";

		} else {
			return "redirect:/index";
		}
	}

	// Save modifications to a coach's details
	@PostMapping(value = "/SaveCoach")
	public String saveCoach(@ModelAttribute("coach2") Coach coach2, HttpSession session) {
		String email = (String) session.getAttribute("EmailCoach");
		int id_coach = (int) session.getAttribute("id_coach2");

		Coach coach3 = coachRepository.findById(id_coach);
		coach2.setId_coach(id_coach);
		System.out.println(id_coach);
		boolean Y = gerantService.UserExist2(coach2, email);

		if (Y) {
			session.setAttribute("EmailCoachExiste", " L'email Existe Déja  ");
			return "redirect:/editCoach/" + id_coach + "";
		} else {
			coach2.setDate_nais(new java.sql.Date(coach2.getDate_nais().getTime() + (1000 * 60 * 60 * 24)));
			coach2.setDate_recrutement(coach3.getDate_recrutement());

			coachRepository.save(coach2);
			session.setAttribute("CoachModifier", " Le coach a été Modifié  ");

//		     if(!email.equals(coach2.getEmail())) {
//		        String subject = "SPORTS WORLD" ;
//		    	String message ="Cher coach "+coach2.getFirstname()+" suit a votre demande votre nouvelle adresse mail est la suivant : "+coach2.getEmail();
//			    String to=coach2.getEmail();
//				emailService.sendEmail(subject,message,to);
//	        }
			return "redirect:/AfficheCoachs";
		}
	}
	//Display the page for editing coach disciplines
	@GetMapping(value = "editCoach/ModifierDisciplines")
	public String showEditDisciplinePage(HttpSession session, Model model) {

		if ((String) session.getAttribute("GerantConnected") != null) {
			int id_coach = (int) session.getAttribute("id_coach2");
			Coach coach = coachRepository.findById(id_coach);

			List<CoachDisciplines> listCD = coach.getCoachDisciplines();
			List<Discipline> listCD2 = new ArrayList<Discipline>();

			for (CoachDisciplines cd : listCD) {
				Discipline D1 = cd.getDiscipline();
				System.out.println(D1.getId_discipline());
				System.out.println(D1.getNomDiscipline());
				// Discipline D=new Discipline();
				// D.setId_discipline( D1.getId_discipline());
				// D.setNomDiscipline( D1.getNomDiscipline());
				listCD2.add(D1);

			}
			model.addAttribute("listDisciplineCoachD2", listCD2);
			List<Discipline> listDisciplines = disciplineRepository.findAll();
			model.addAttribute("listDisciplines2", listDisciplines);
			return "EditCoachDisciplines";
		} else {
			return "redirect:/index";
		}

	}

	// Delete a discipline associated with a coach
	@GetMapping(value = "editCoach/SupprimerDisciplineCoach/{id}")
	public String deleteCoachDiscipline(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			int id_coach = (int) session.getAttribute("id_coach2");
			Coach coach = coachRepository.findById(id_coach);

			coachDisciplinesRepository.findAll().forEach(a -> {
				Coach co = a.getCoach();
				Discipline D = a.getDiscipline();
				if (co.getId_coach() == coach.getId_coach() && D.getId_discipline() == id) {

					coachDisciplinesRepository.deleteById(a.getId_coachDisciplines());
					session.setAttribute("CoachDisciplineDelected", "La discipline a été supprimée");
				}
			});
			return "redirect:/editCoach/ModifierDisciplines";
		} else {
			return "redirect:/index";
		}
	}

	// show the page for adding a discipline to a coach during editing
	@GetMapping(value = "editCoach/AddDiscipline2/{id}")
	public String showEditDisciplinePage(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			Coach coach = coachRepository.findById((int) session.getAttribute("id_coach2"));

			boolean D = gerantService.addDisciplineCoach(coach, id);
			if (D) {
				session.setAttribute("DisciplineExiste", " Cette discipline existe déja");
			} else {
				session.setAttribute("DisciplineAjouter", "La discipline a été  ajoutée  ");
			}
			return "redirect:/editCoach/ModifierDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	
	// Method to display the list of disciplines
	@GetMapping("gereDisciplines")
	public String AfficheListDiscipline(Model model, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			List<Discipline> listDisciplines = disciplineRepository.findAll();
			model.addAttribute("listDisciplines2", listDisciplines);

			return "ListeDisciplines";

		} else {
			return "redirect:/index";
		}
	}

	// Method to delete a discipline
	@GetMapping("/deleteDiscipline/{id}")
	public String deleteDiscipline(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			disciplineRepository.deleteById(id);
			session.setAttribute("discplineSupprimer", "La discpline a été Supprimée");
			return "redirect:/gereDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	// Method to show the page for editing a discipline
	@GetMapping("editDiscipline/{id}")
	public ModelAndView showEditDisciplinePage2(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			ModelAndView ModifierDiscipline = new ModelAndView("ModifierDiscipline");
			Discipline discipline = disciplineRepository.findById(id);
			session.setAttribute("id_discpline", discipline.getId_discipline());
			ModifierDiscipline.addObject("discipline", discipline);
			return ModifierDiscipline;

		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	// Method to save modifications to a discipline
	@PostMapping(value = "/SaveDiscipline")
	public String SaveDiscipline(@ModelAttribute("discipline") Discipline discipline, HttpSession session) {
		// String nomDiscipline=(String)session.getAttribute("EmailCoach");
		int id_discipline = (int) session.getAttribute("id_discpline");
		System.out.println(id_discipline);
		Discipline discipline2 = disciplineRepository.findById(id_discipline);
		String NomDiscipline = discipline2.getNomDiscipline();

		System.out.println(NomDiscipline);
		boolean Y = gerantService.DisciplineExist(discipline, NomDiscipline);

		if (Y) {
			session.setAttribute("DisciplineExiste", "Cette discipline exist déja  ");
			return "redirect:/gereDisciplines";
		} else {
			discipline2.setNomDiscipline(discipline.getNomDiscipline());
			discipline2.setDescription(discipline.getDescription());
			// discipline.setId_discipline(discipline2.get);
			disciplineRepository.save(discipline2);
			session.setAttribute("DisciplineModifier", " La discipline a été modifiée  ");
			return "redirect:/gereDisciplines";
		}

	}
	// Method to display the page for adding a new discipline
	@GetMapping("/AjouterDiscipline")
	public ModelAndView showAddDisciplinePage(HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			return new ModelAndView("ajouterDiscipline", "Discipline", new Discipline());
		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	// Method to save a new discipline
	@PostMapping(value = "/SaveNewDiscipline")
	public String SaveNewDiscipline(@ModelAttribute("Discipline") Discipline discipline, HttpSession session) {
//		int id_discipline=discipline.getId_discipline();
//		Discipline d=disciplineRepository.findById(id_discipline);

		String nomDiscipline = discipline.getNomDiscipline();
		boolean Y = gerantService.DisciplineExist2(nomDiscipline);

		if (Y) {
			session.setAttribute("DisciplineExiste", " Cette discipline exist déja ");
			return "redirect:/gereDisciplines";
		} else {

			disciplineRepository.save(discipline);
			session.setAttribute("DisciplineAjouter", " La discipline a été ajoutée  ");
			return "redirect:/gereDisciplines";
		}

	}
	// Method to redirect to the "gereAbonnements" page
	@GetMapping("editAbonnement/gereAbonnements")
	public String redirectgereAbonnement(HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			return "redirect:/gereAbonnements";
		} else {
			return "redirect:/index";
		}
	}

	// Method to display the list of abonnements
	@GetMapping("/gereAbonnements")
	public String AfficheAbonnements(Model model, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			List<Abonnement> listAbonnements = abonnementRepository.findAll();
			model.addAttribute("listAbonnements4", listAbonnements);
			return "ListAbonnements";
		} else {
			return "redirect:/index";
		}

	}

	// Method to delete an abonnement
	@GetMapping("/deleteAbonnement/{id}")
	public String deleteAbonnement(@PathVariable(name = "id") int id, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			abonnementRepository.deleteById(id);
			session.setAttribute("AbonnementSupprimer", "L'abonnement a été Supprimé");
			return "redirect:/gereAbonnements";

		} else {
			return "redirect:/index";
		}
	}
	// Method to show the page for editing an abonnement
	@GetMapping("editAbonnement/{id}")
	public ModelAndView showEditAbonnementPage(@PathVariable(name = "id") int id, HttpSession session, Model model) {

		if ((String) session.getAttribute("GerantConnected") != null) {
			ModelAndView ModifierAbonnement = new ModelAndView("ModifierAbonnement");

			List<Discipline> listDisciplines = disciplineRepository.findAll();
			model.addAttribute("listDisciplines3", listDisciplines);

			session.setAttribute("id_abonnement", id);
			Abonnement abonnement = abonnementRepository.findById(id);

			ModifierAbonnement.addObject("abonnement", abonnement);

			return ModifierAbonnement;

		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	// Method to save modifications to an abonnement
	@PostMapping(value = "/SaveAbonnement")
	public String SaveAbonnement(
			@ModelAttribute("abonnementsInforamtions") AbonnementsInforamtions abonnementsInforamtions,
			HttpSession session) {
		// String nomDiscipline=(
		Abonnement abonnement = abonnementRepository.findById((int) session.getAttribute("id_abonnement"));
		System.out.println(abonnementsInforamtions.getTarif());
		abonnement.setTarif(abonnementsInforamtions.getTarif());

		System.out.println(abonnementsInforamtions.getDuree());
		abonnement.setDuree(abonnementsInforamtions.getDuree());

		abonnementRepository.save(abonnement);
		session.setAttribute("AbonnementModifier", "L'abonnement a été modifié");
		return "redirect:/gereAbonnements";
	}
	// Method to display the page for adding or removing disciplines associated with an abonnement
	@GetMapping(value = "editAbonnement/ModifierAbonnementDisciplines")
	public String showAddAbonnementDisciplinesPage(Model model, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			int id_abonnement = (int) session.getAttribute("id_abonnement");
			Abonnement ab = abonnementRepository.findById(id_abonnement);

			List<AbonnementDisciplines> listCD = ab.getAbonnementDisciplines();
			model.addAttribute("listOfAbonnementdisciplines", listCD);

			List<Discipline> listDisciplines2 = disciplineRepository.findAll();
			model.addAttribute("listOfDisciplines", listDisciplines2);
			return "ModifierAbonnementDisciplines";
		} else {
			return "redirect:/index";
		}
	}
	// Method to remove a discipline associated with an abonnement
	@GetMapping(value = "editAbonnement/SupprimerAbonnementDiscipline/{id}")
	public String showdeleteAbonnementDiscipline(@PathVariable(name = "id") int id, Model model, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			int id_abonnement = (int) session.getAttribute("id_abonnement");

			abonnementDisciplinesRepository.deleteDiscipline(id_abonnement, id);
			session.setAttribute("abonnementDisciplineSupprimé", "La discipline à été supprimée");
			session.setAttribute("AbonnementModifier", "L'abonnement a été modifié");
			return "redirect:/editAbonnement/ModifierAbonnementDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	// Method to add a discipline to an abonnement
	@GetMapping(value = "editAbonnement/AddAbonnementDiscipline/{id}")
	public String AddAbonnementDiscipline(@PathVariable(name = "id") int id, Model model, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			b = false;
			int id_abonnement = (int) session.getAttribute("id_abonnement");

			abonnementDisciplinesRepository.findAll().forEach(a -> {
				if (a.getAbonnement().getId_abonnement() == id_abonnement
						&& a.getDiscipline().getId_discipline() == id) {
					b = true;
					return;
				}
			});
			if (b) {
				session.setAttribute("DisciplineExiste", "La discipline existe déja ");
			} else {
				Abonnement a = abonnementRepository.findById(id_abonnement);
				Discipline d = disciplineRepository.findById(id);
				AbonnementDisciplines ab = new AbonnementDisciplines();
				ab.setAbonnement(a);
				ab.setDiscipline(d);
				abonnementDisciplinesRepository.save(ab);
				session.setAttribute("DisciplineAjouter", "La discipline à été ajoutée");
			}

			session.setAttribute("AbonnementModifier", "L'abonnement a été modifiée");
			return "redirect:/editAbonnement/ModifierAbonnementDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	// Method to display the page for adding a new abonnement
	@GetMapping(value = "/ajouterAbonnement")
	public String showAddAbonnementPage(@ModelAttribute("abonnement") Abonnement abonnement, Model model,
			HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			return "AjouterAbonnement";
		} else {
			return "redirect:/index";
		}
	}

	// Method to save a new abonnement
	@PostMapping(value = "/SaveNewAbonnement")
	public String SaveNewAbonnement(@ModelAttribute("abonnement") Abonnement abonnement, HttpSession session,
			Model model) {
		Abonnement abonnement1 = new Abonnement();
		System.out.println(abonnement.getTarif());
		abonnement1.setTarif(abonnement.getTarif());

		System.out.println(abonnement.getDuree());
		abonnement1.setDuree(abonnement.getDuree());
		abonnementRepository.save(abonnement1);
		session.setAttribute("id_ab", abonnement1.getId_abonnement());

		session.setAttribute("AbonnementAjouter", " L'abonnement a été ajouté  ");

		return "redirect:/NewAbonnementDisciplines";
	}
	// Method to display the page for selecting disciplines for a new abonnement
	@GetMapping("NewAbonnementDisciplines")
	public String NewAbonnementDisciplines(HttpSession session, Model model) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			List<Discipline> listDisciplines5 = disciplineRepository.findAll();
			model.addAttribute("listDisciplines5", listDisciplines5);
			return "NewAbonnementDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	// Method to save selected disciplines for a new abonnement
	@GetMapping(value = "/AddAbonnementDiscipline/{id}")
	public String SaveNewAbonnementDisciplines(@PathVariable(name = "id") int id, HttpSession session, Model model) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			b = false;
			Abonnement a = abonnementRepository.findById((int) session.getAttribute("id_ab"));
			Discipline d = disciplineRepository.findById(id);
			List<AbonnementDisciplines> ab = a.getAbonnementDisciplines();

			for (AbonnementDisciplines ads : ab) {
				if (ads.getAbonnement().getId_abonnement() == a.getId_abonnement()
						&& ads.getDiscipline().getId_discipline() == d.getId_discipline()) {
					b = true;
					session.setAttribute("AbonnementDisciplineExiste", "La discipline existe déja ");
					break;
				}
			}

			if (!b) {
				AbonnementDisciplines abd = new AbonnementDisciplines();
				abd.setAbonnement(a);
				abd.setDiscipline(d);
				abonnementDisciplinesRepository.save(abd);
				session.setAttribute("AbonnementDisciplineAdded", "La discipline à été ajoutée");
			}

			return "redirect:/NewAbonnementDisciplines";

		} else {
			return "redirect:/index";
		}
	}
	// Method to display the page for editing gerant information
	@GetMapping("ModifierInfoGerant/{id_gerant}")
	public ModelAndView showEditAbonnePage(@PathVariable(name = "id_gerant") int id_gerant, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			ModelAndView ModifierInfoGerant = new ModelAndView("ModifierInfoGerant");
			Gerant gerant = gerantRepository.findById(id_gerant);
			session.setAttribute("id_gerant", gerant.getId_gerant());

			System.out.println(gerant.getId_gerant());
			ModifierInfoGerant.addObject("gerant", gerant);

			return ModifierInfoGerant;

		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	// Method to save modifications to gerant information
	@PostMapping(value = "/SaveGerant")
	public String SaveGerant(@ModelAttribute("gerant") Gerant gerant, HttpSession session) {
		Gerant G1 = gerantRepository.findById((int) session.getAttribute("id_gerant"));
		int id_gerant = G1.getId_gerant();
		System.out.println(id_gerant);
		String email = G1.getEmail();
		System.out.println(email);
		boolean Y = gerantService.UserExist3(gerant, email);
		System.out.println(Y);
		if (Y) {
			session.setAttribute("EmailGerantExiste", " Email Existe Déja  ");
			return "redirect:/ModifierInfoGerant/" + id_gerant + "";
		} else {
			gerant.setId_gerant(id_gerant);
			gerant.setPassword(G1.getPassword());
			gerant.setDate_nais(new java.sql.Date(gerant.getDate_nais().getTime() + (1000 * 60 * 60 * 24)));
			gerantRepository.save(gerant);
			session.setAttribute("GerantModifier", " les informations ont été Modifiées  ");
//				        	 if(!email.equals(gerant.getEmail())) {
//					        	 String subject = "SPORTS WORLD" ;
//								 String message =".";
//								 String to=gerant.getEmail();
//								emailService.sendEmail(subject,message,to);
//				        	 }
			return "EspaceGerant";
		}

	}
	// Method to display the page for changing gerant password
	@GetMapping(value = "/ModifierInfoGerant/ChangePasswordGerant")
	public ModelAndView showForm(HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			return new ModelAndView("ModifierMotDePasseGerant", "Mdp", new Mdp());
		} else {
			return new ModelAndView("redirect:/index");
		}

	}


	// Method to save a new gerant password
	@PostMapping(value = "/SaveNewGerantPassword")
	public String ChangeMdp(@ModelAttribute("Mdp") Mdp Mdp, HttpSession session) {
		String mdpActuel = Mdp.getMdpActuel();
		String mdp1 = Mdp.getMdp1();
		String mdp2 = Mdp.getMdp2();
		Gerant G1 = gerantRepository.findById((int) session.getAttribute("id_gerant"));
		if (G1.getPassword().equals(mdpActuel)) {
			if (mdp1.equals(mdp2)) {
				G1.setPassword(mdp1);
				gerantRepository.save(G1);
				session.setAttribute("PasswordGerantChanged", "le mot de passe a été modifié avec succès");
				return "EspaceGerant";
			} else {
				session.setAttribute("NewGerantMdpWrong",
						"la confirmation ne correspond pas avec le nouveau mot de passe");
				return "ModifierMotDePasseGerant";
			}

		} else {
			session.setAttribute("PasswordGerantWrong", "le mot de passe est faux");
			return "ModifierMotDePasseGerant";
		}

	}

	
	// Method to display the planning for the gerant
	@GetMapping("AffichePlanningGerant")
	public String AfficherPlanning(@ModelAttribute("coach") Coach coach,
			@ModelAttribute("discipline") Discipline discipline, HttpSession session, Model model) throws SQLException {
		if ((String) session.getAttribute("GerantConnected") != null) {

			List<Coach> coachs = coachRepository.findAll();
			model.addAttribute("coachs", coachs);

			List<Discipline> disciplines = disciplineRepository.findAll();
			model.addAttribute("disciplines", disciplines);

			List<Seance> ListSeances = visiteurService.getSeances();
			model.addAttribute("ListSeances", ListSeances);
			session.setAttribute("ListSeances", ListSeances);

			List<Integer> WeekSeances = op.SeanceAbonné();
			List<Seance> WeekSeancesReservations = new ArrayList<Seance>();

			for (int seance : WeekSeances) {
				Seance c = seanceRepository.findById(seance);
				WeekSeancesReservations.add(c);

			}
			model.addAttribute("WeekSeancesReservations", WeekSeancesReservations);

			return "PlanningGerant";
		} else {
			return "redirect:/index";
		}
	}
	// Method to add a new seance by gerant
	@GetMapping(value = "AddSeance/{date}/{time}")
	public String AddSeanceAbonneByGerant(@PathVariable(name = "date") LocalDate date,
			@PathVariable(name = "time") LocalTime time, Model model, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			session.setAttribute("date", date);
			session.setAttribute("time", time);

			session.setAttribute("a", 0);

			List<Coach> coachs = coachRepository.findAll();
			model.addAttribute("coachs", coachs);

			List<Discipline> disciplines = disciplineRepository.findAll();
			model.addAttribute("disciplines", disciplines);

			return "redirect:/AffichePlanningGerant";
		} else {
			return "redirect:/index";
		}
	}

	boolean c = false;
	// Method to save a new seance added by gerant
	@PostMapping(value = "SaveNewSeanceByGerant")
	public String AddSeanceAbonneByGerant2(@ModelAttribute("coach") Coach coach,
			@ModelAttribute("discipline") Discipline discipline, Model model, HttpSession session) throws SQLException {
		if ((String) session.getAttribute("GerantConnected") != null) {

			c = false;

			LocalDate date = (LocalDate) session.getAttribute("date");
			LocalTime time = (LocalTime) session.getAttribute("time");

			int id_discipline = discipline.getId_discipline();
			Discipline d = disciplineRepository.findById(id_discipline);
			int id_coach = coach.getId_coach();
			b = false;

			Coach coach1 = coachRepository.findById(id_coach);
			coach1.getCoachDisciplines().forEach(a -> {
				if (a.getDiscipline().getId_discipline() == id_discipline) {
					b = true;
				}

			});
			if (b) {
				int lastPlanning = op.LastPlanning();
				Planning planning = planningRepository.findById(lastPlanning);

				LocalDate today = LocalDate.now();
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

				Seance s = new Seance();
				Planning p = planningRepository.findById(id_planning);
				s.setDiscipline(d);
				s.setPlanning(p);
				s.setDate(date.plusDays(1));
				s.setHeureDebut(time);
				s.setHeureFin(time.plusHours(2));
				s.setCoach(coach1);
				seanceRepository.save(s);
				p.setDateDebut(p.getDateDebut().plusDays(1));
				p.setDateFin(p.getDateFin().plusDays(1));
				p.setNbrSeance(p.getNbrSeance() + 1);
				planningRepository.save(p);
				session.setAttribute("seanecAjouté", "La séance a été ajoutée");
				c = true;
			} else {
				session.setAttribute("seanecNotAdded", "Cette discipline n'est pas assurée par ce coach");

			}
			if (c) {
				return "redirect:/AffichePlanningGerant";
			} else {

				return "redirect:/AddSeance/" + date + "/" + time;
			}

		} else {
			return "redirect:/index";
		}

	}
	// Method to delete a seance by gerant
	@GetMapping(value = "SupprimerReservationCoach/{id_seance}")
	public String DeletSeance(@PathVariable(name = "id_seance") int id_seance, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			Seance seance = seanceRepository.findById(id_seance);
			Coach coach = seance.getCoach();
			String Email = coach.getEmail();

			int id_planning = seance.getPlanning().getId_planning();
			Planning p = planningRepository.findById(id_planning);
			seance.getInscriptionSéance().forEach(a -> {
				String subject = "SPORTS WORLD";
				String message = "Cher abonne " + a.getAbonne().getFirstname()
						+ " votre réseravtion de la séance programmé le  " + seance.getDate() + " de "
						+ seance.getHeureDebut() + " a " + seance.getHeureFin() + " a été annuler";
				String to = a.getAbonne().getEmail();
				emailService.sendEmail(subject, message, to);
			});
			seanceRepository.delete(seance);

			p.setDateDebut(p.getDateDebut().plusDays(1));
			p.setDateFin(p.getDateFin().plusDays(1));
			p.setNbrSeance(p.getNbrSeance() - 1);
			planningRepository.save(p);

			String subject = "SPORTS WORLD";
			String message = "Cher coach " + coach.getFirstname() + " votre séance programmé le  " + seance.getDate()
					+ " de " + seance.getHeureDebut() + " a " + seance.getHeureFin() + " a été annuler";
			String to = Email;

			emailService.sendEmail(subject, message, to);
			session.setAttribute("SeanceDeleted", "La séance a été supprimée");
			return "redirect:/AffichePlanningGerant";
		} else {
			return "redirect:/index";
		}
	}
	// Method to manage reservations by gerant
	@GetMapping(value = "GérerReservations")
	public String GérerRservation(@ModelAttribute("Recherche") Recherche Recherche, HttpSession session, Model model)
			throws SQLException {
		if ((String) session.getAttribute("GerantConnected") != null) {
			LocalDate date = Recherche.getDate();
			if (date != null) {

				List<InscriptionSéance> listSeance = inscriptionSeanceRepository.getSeances(date.plusDays(1));
				model.addAttribute("ListSeancesReserver", listSeance);

			} else {

				List<Integer> WeekSeances = op.SeanceAbonné();
				List<Seance> ListSeancesReserver = new ArrayList<Seance>();

				List<InscriptionSéance> ListSeances = new ArrayList<InscriptionSéance>();
				List<InscriptionSéance> List = inscriptionSeanceRepository.findAll();

				List.forEach(a -> {
					for (int seance : WeekSeances) {
						if (seance == a.getSeance().getId_seance()) {
							;
							try {
								List<Integer> se = op.SeancesAbonnés(seance);
								for (int se1 : se) {
									InscriptionSéance is = inscriptionSeanceRepository.findById(se1);
									ListSeances.add(is);
									System.out.println(a.getSeance().getDiscipline().getNomDiscipline());
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}

				});

				model.addAttribute("ListSeancesReserver", ListSeances);

			}

			return "GérerReseravtions";

		} else {
			return "redirect:/index";
		}
	}
	// Method to delete a reservation by gerant
	@GetMapping(value = "supprimerReservation/{id_seance}/delete/{id_InscriptionSeance}")
	public String DeletSeanceAbonne(@PathVariable(name = "id_seance") int id_seance,
			@PathVariable(name = "id_InscriptionSeance") int id_InscriptionSeance, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			InscriptionSéance inscriptionSeance = inscriptionSeanceRepository.findById(id_InscriptionSeance);
			Seance seance = seanceRepository.findById(id_seance);
			Abonne abonne = inscriptionSeance.getAbonne();
			String Email = abonne.getEmail();

			inscriptionSeanceRepository.delete(inscriptionSeance);

			String subject = "SPORTS WORLD";
			String message = "Cher abonne " + abonne.getFirstname() + "votre séance de"
					+ inscriptionSeance.getSeance().getDiscipline().getNomDiscipline() + " programmé le  "
					+ seance.getDate() + " a " + seance.getHeureDebut() + "jusqu'a" + seance.getHeureFin()
					+ " à été annuler";
			String to = Email;

			emailService.sendEmail(subject, message, to);
			session.setAttribute("ReservationDeleted", "La réservation a été supprimée");

			return "redirect:/AffichePlanningGerant";
		} else {
			return "redirect:/index";
		}
	}
	// Method to display past plannings for the gerant
	@GetMapping(value = "AncienPlannings")
	public String AncienPlannings(@ModelAttribute("Recherche") Recherche Recherche, HttpSession session, Model model) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			LocalDate date = Recherche.getDate();
			if (date != null) {
				Planning p1 = planningRepository.getPlanning(date.plusDays(1));
				if (p1 != null) {
					List<Planning> listPlannings2 = new ArrayList();

					listPlannings2.add(p1);
					model.addAttribute("listPlannings", listPlannings2);

				} else {
					List<Seance> list = seanceRepository.getSeance2(date);

					if (!list.isEmpty()) {
						Seance a = list.get(0);
						List<Planning> listPlannings2 = new ArrayList();
						Planning b = planningRepository.findById(a.getPlanning().getId_planning());
						listPlannings2.add(b);
						model.addAttribute("listPlannings", listPlannings2);
					} else {
						session.setAttribute("NoSeances", "aucune séance n'est prévue ce jour la");
					}
				}
			} else {
				List<Planning> listPlannings = planningRepository.findAll();

				model.addAttribute("listPlannings", listPlannings);
			}
			return "Plannings";
		} else {
			return "redirect:/index";
		}
	}
	// Method to show details of a specific past planning
	@GetMapping(value = "showPlanning/{id_planning}")
	public String showAncienPlannings(@PathVariable(name = "id_planning") int id_planning, HttpSession session,
			Model model) {
		if ((String) session.getAttribute("GerantConnected") != null) {
			Planning p = planningRepository.findById(id_planning);
			session.setAttribute("DateDebut", p.getDateDebut());

			System.out.println(p.getDateDebut());
			List<Seance> listSeances = p.getSeance();
			listSeances.forEach(a -> {
				System.out.println(a.getDate());

			});

			session.setAttribute("listSeances2", listSeances);

			return "AncienPlanning";
		} else {
			return "redirect:/index";
		}
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "editCoach/espacegerant")
	public String ShowespaceGerant2() {
		return "redirect:/espacegerant";
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "ModifierInfoGerant/espacegerant")
	public String ShowespaceGerant3() {
		return "redirect:/espacegerant";
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "edit/espacegerant")
	public String ShowespaceGerant4() {
		return "redirect:/espacegerant";
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "editDiscipline/espacegerant")
	public String ShowespaceGerant5() {
		return "redirect:/espacegerant";
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "editAbonnement/espacegerant")
	public String ShowespaceGerant6() {
		return "redirect:/espacegerant";
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "ModifierInfoGerant/ChangePasswordGerant/espacegerant")
	public String ShowespaceGerant7() {
		return "redirect:/espacegerant";
	}

	// Redirect to the Gerant dashboard
	@GetMapping(value = "showPlanning/espacegerant")
	public String ShowespaceGerant8() {
		return "redirect:/espacegerant";
	}

}
