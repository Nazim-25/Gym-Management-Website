package com.sportsworld.demo.Control;

import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sportsworld.demo.DAO.Abonne;
import com.sportsworld.demo.DAO.Abonnement;
import com.sportsworld.demo.DAO.AbonnementsInforamtions;
import com.sportsworld.demo.DAO.CompteBancaire;
import com.sportsworld.demo.DAO.Discipline;
import com.sportsworld.demo.DAO.InscriptionAbonnement;
import com.sportsworld.demo.DAO.InscriptionSéance;
import com.sportsworld.demo.DAO.Mdp;
import com.sportsworld.demo.DAO.Payements;
import com.sportsworld.demo.DAO.Seance;
import com.sportsworld.demo.Metier.Operations;
import com.sportsworld.demo.Repository.AbonneRepository;
import com.sportsworld.demo.Repository.AbonnementDisciplinesRepository;
import com.sportsworld.demo.Repository.AbonnementRepository;
import com.sportsworld.demo.Repository.CompteBancaireRepository;
import com.sportsworld.demo.Repository.InscriptionAbonnementRepository;
import com.sportsworld.demo.Repository.InscriptionSeanceRepository;
import com.sportsworld.demo.Repository.PayementsRepository;
import com.sportsworld.demo.Repository.SeanceRepository;
import com.sportsworld.demo.Services.EmailService;
import com.sportsworld.demo.Services.GerantService;
import com.sportsworld.demo.Services.VisiteurService;

@Controller
public class AbonneController {
	// Repositories injected for database operations
	@Autowired
	private AbonneRepository abonneRepository;
	@Autowired
	private AbonnementRepository abonnementRepository;
	@Autowired
	private InscriptionAbonnementRepository inscriptionAbonnementRepository;

	@Autowired
	CompteBancaireRepository compteBancaireRepository;

	@Autowired
	PayementsRepository payementsRepository;

	@Autowired
	private AbonnementDisciplinesRepository abonnementDisciplinesRepository;

	@Autowired
	private SeanceRepository seanceRepository;

	@Autowired
	private InscriptionSeanceRepository inscriptionSeanceRepository;

	@Autowired
	private EmailService emailService;
	@Autowired
	private GerantService gerantService;

	@Autowired
	private VisiteurService visiteurService;
	Operations op = new Operations();

	@GetMapping(value = "/test")
	public String test(HttpSession session) {

		return "test";

	}

	// Method to show the subscriber's dashbord
	@GetMapping(value = "/EspaceAbonne")
	public String EspaceAbonne(HttpSession session) {

		if ((String) session.getAttribute("AbonneConnected") != null) {
			return "EspaceAbonne";
		} else {
			return "redirect:/index";
		}

	}

	// Method to cancel the subscription
	@GetMapping("/AnnulerInscription")
	public String AnnulerInscription(HttpSession session) {

		if ((String) session.getAttribute("AbonneConnected") != null) {
			session.removeAttribute("UserAdded");
			session.removeAttribute("AbonneModifier");
			Abonne abonne = abonneRepository.findById((int) session.getAttribute("id_abonne"));
			abonneRepository.delete(abonne);
			session.setAttribute("abonneDeleted", "votre inscription au site a été supprimé avec succès");
//		     String subject = "SPORTS WORLD" ;
//		 	 String message ="Suit à votre deinscription nous vous informons que votre compte a été fermé ";
//			 String to=abonne.getEmail();
//			 emailService.sendEmail(subject,message,to);
			return "index";
		} else {
			return "redirect:/index";
		}
	}

	// Method to display available subscriptions
	@GetMapping("/AfficheAbonnements")
	public String AfficheAbonnements(Model model, HttpSession session) {

		if ((String) session.getAttribute("AbonneConnected") != null) {
			session.removeAttribute("UserAdded");
			session.removeAttribute("AbonneModifier");

			List<Abonnement> listAbonnements = abonnementRepository.findAll();
			model.addAttribute("listAbonnements2", listAbonnements);

			return "ChoisitAbonnement";
		} else {
			return "redirect:/index";
		}
	}

	boolean b = false;
	boolean k = false;

	// Method to subscribe to a new subscription
	@GetMapping("/abonner/{id}")
	public String ajouterAbonnement(@ModelAttribute("CompteBancaire") CompteBancaire CompteBancaire,
			@PathVariable(name = "id") int id, HttpSession session, Model model) {

		if ((String) session.getAttribute("AbonneConnected") != null) {
			b = false;
			int id_abonne = (int) session.getAttribute("id_abonne");
			Abonne abonne = abonneRepository.findById(id_abonne);
			List<InscriptionAbonnement> list = abonne.getInscriptionAbonnement();
			for (InscriptionAbonnement IA : list) {
				if (IA.getAbonnement().getId_abonnement() == id) {
					if (!IA.isExpiration()) {
						b = true;
						break;
					}
				}
			}
			;

			if (b) {
				session.setAttribute("abonnementexiste", "L'abonnement existe déja");
				return "redirect:/AfficheAbonnements";

			} else {
				session.setAttribute("id", id);
				return "PageDePayement";

			}

		} else {
			return "redirect:/index";
		}
	}

	boolean K = false;

	// Method to handle payment for the subscription
	@PostMapping("/Payer/{id}")
	public String Payement(@PathVariable(name = "id") int id,
			@ModelAttribute("CompteBancaire") CompteBancaire CompteBancaire, HttpSession session, Model model) {

		if ((String) session.getAttribute("AbonneConnected") != null) {

			String email = (String) session.getAttribute("email");
			System.out.println(email);
			Abonne ab = abonneRepository.findByEmailContains(email);
			Abonne abonne = abonneRepository.findById((int) session.getAttribute("id_abonne"));
			System.out.println(abonne.getId_abonne());
			System.out.println(id);
			Abonnement abonnement2 = abonnementRepository.findById(id);
			System.out.println(abonnement2.getTarif());

			compteBancaireRepository.findAll().forEach(ab1 -> {
				if (ab1.getNo_Carte().equals(CompteBancaire.getNo_Carte())
						&& ab1.getPin().equals(CompteBancaire.getPin())) {
					if (ab1.getSolde() >= abonnement2.getTarif()) {

						double tarif = ab1.getSolde() - abonnement2.getTarif();
						ab1.setSolde(tarif);
						compteBancaireRepository.save(ab1);
						op.saveAbonnement(abonne, abonnement2);
						Payements P = new Payements();
						P.setAbonnement(abonnement2);
						P.setAbonne(ab);
						P.set_it_paid(true);
						P.setCompteBancaire(ab1);
						payementsRepository.save(P);
						session.setAttribute("good", "Votre Abonnement est activé");
						K = true;
					} else {

						session.setAttribute("SoldeInsuffisant", "Solde Insuffisant");
					}
				} else {

					session.setAttribute("InformationsInvalides", "Informations invalides");
				}

			});

			if (K) {
				return "redirect:/AfficheAbonnements";
			} else {
				return "PageDePayement";
			}

		} else {
			return "redirect:/index";
		}

	}

	// Method to show the subscriber's information for editing
	@GetMapping("ModifierInfo/{id_abonne}")
	public ModelAndView showEditAbonnePage(@PathVariable(name = "id_abonne") int id_abonne, HttpSession session) {

		if ((String) session.getAttribute("AbonneConnected") != null) {

			session.removeAttribute("UserAdded");
			ModelAndView ModifierInfoAbonne = new ModelAndView("ModifierInfoAbonne");
			Abonne ab = abonneRepository.findById(id_abonne);
			session.setAttribute("id_abonne", ab.getId_abonne());
			ModifierInfoAbonne.addObject("abonne", ab);
			return ModifierInfoAbonne;

		} else {
			ModelAndView index = new ModelAndView("redirect:/index");
			return index;
		}

	}

	// Method to save subscriber information after editing
	@PostMapping(value = "/SaveAbonne")
	public String saveAbonne(@ModelAttribute("abonne") Abonne abonne, HttpSession session) {
		if ((String) session.getAttribute("AbonneConnected") != null) {

			int id_abonne = (int) session.getAttribute("id_abonne");
			Abonne ab1 = abonneRepository.findById(id_abonne);
			String email = ab1.getEmail();

			boolean Y = gerantService.UserExistAbonne(abonne, email);
			System.out.println(Y);
			if (Y) {
				session.setAttribute("EmailAbonneExiste", " Email Existe Déja  ");
				return "redirect:/ModifierInfo/" + id_abonne + "";
			} else {
				abonne.setId_abonne(ab1.getId_abonne());
				abonne.setPassword(ab1.getPassword());
				abonne.setDate_inscription(ab1.getDate_inscription());
				abonne.setDate_nais(new java.sql.Date(abonne.getDate_nais().getTime() + (1000 * 60 * 60 * 24)));
				abonne.setGenre(ab1.getGenre());
				abonneRepository.save(abonne);
				session.setAttribute("AbonneModifier", " les informations ont été Modifiées  ");
				if (!email.equals(abonne.getEmail())) {
//				     String subject = "SPORTS WORLD" ;
//					 String message ="cher client "+abonne.getFirstname()+" suit au modification operer par vos soins votre nouvelle adresse mail est la suivant : "+abonne.getEmail();
//					 String to=abonne.getEmail();							 
//					 emailService.sendEmail(subject,message,to);
				}

				return "EspaceAbonne";
			}

		} else {
			return "redirect:/index";
		}
	}

	// Method to show the form for changing the subscriber's password
	@GetMapping(value = "/ModifierInfo/ChangePassword")
	public ModelAndView showForm(HttpSession session) {
		if ((String) session.getAttribute("AbonneConnected") != null) {
			return new ModelAndView("ModifierMotDePasse", "Mdp", new Mdp());
		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	// Method to save the new subscriber password
	@PostMapping(value = "/SaveNewPassword")
	public String ChangeMdp(@ModelAttribute("Mdp") Mdp Mdp, HttpSession session) {

		if ((String) session.getAttribute("AbonneConnected") != null) {
			String mdpActuel = Mdp.getMdpActuel();
			String mdp1 = Mdp.getMdp1();
			String mdp2 = Mdp.getMdp2();
			Abonne ab1 = abonneRepository.findById((int) session.getAttribute("id_abonne"));
			if (ab1.getPassword().equals(mdpActuel)) {
				if (mdp1.equals(mdp2)) {
					ab1.setPassword(mdp1);
					abonneRepository.save(ab1);
					session.setAttribute("PasswordChanged", "le mot de passe a été modifié avec succès");
					return "EspaceAbonne";
				} else {
					session.setAttribute("NewMdpWrong",
							"la confirmation ne correspond pas avec le nouveau mot de passe");
					return "ModifierMotDePasse";
				}

			} else {
				session.setAttribute("PasswordWrong", "le mot de passe est faux");
				return "ModifierMotDePasse";
			}

		} else {
			return "redirect:/index";
		}

	}

	// Method to display the subscriber's active subscriptions
	@GetMapping("AfficheMesAbonnements")
	public String AfficherMesAbonnements(HttpSession session, Model model) {
		if ((String) session.getAttribute("AbonneConnected") != null) {
			int id_abonne = (int) session.getAttribute("id_abonne");
			// Abonne abonne=abonneRepository.findById(id_abonne);
			// List<InscriptionAbonnement>ListIAB=ab.getInscriptionAbonnement();
			LocalDate today = LocalDate.now();
			List<InscriptionAbonnement> ListIAB = inscriptionAbonnementRepository.GetLiveAbonnement(today, id_abonne);

			// List<InscriptionAbonnement>ListIAB=abonne.getInscriptionAbonnement();
			session.setAttribute("today", today);
			model.addAttribute("ListIAB", ListIAB);
			return "MesAbonnements";
		} else {
			return "redirect:/index";
		}
	}

	// Method to display the subscriber's planned sessions
	@GetMapping("AffichePlanning")
	public String AfficherPlanning(HttpSession session, Model model) throws SQLException {
		if ((String) session.getAttribute("AbonneConnected") != null) {

			List<Seance> ListSeances = visiteurService.getSeances();
			model.addAttribute("ListSeances", ListSeances);
			session.setAttribute("ListSeances", ListSeances);

			List<Integer> seances = op.SeanceAbonné();

			Abonne abonne = abonneRepository.findById((int) session.getAttribute("id_abonne"));

			List<Seance> ListSeancesReserver = new ArrayList<Seance>();
			List<Integer> se = new ArrayList<Integer>();

			for (int seance : seances) {

				abonne.getInscriptionSéance().forEach(a -> {
					if (seance == (a.getSeance().getId_seance())) {
						se.add(seance);
					}
				});

			}

			for (int seance : se) {
				Seance c = seanceRepository.findById(seance);
				ListSeancesReserver.add(c);
			}

			model.addAttribute("ListSeancesReserver", ListSeancesReserver);

			return "PlanningAbonne";
		} else {
			return "redirect:/index";
		}
	}

	boolean c = false;
	boolean d = false;
	boolean f = false;

	// Method to handle the subscription of a session by a subscriber
	@GetMapping(value = "seanceAbonne/{id_seance}/{date1}")
	public String AddSeanceAbonne(@PathVariable(name = "id_seance") int id_seance,
			@PathVariable(name = "date1") LocalDate date1, HttpSession session) {
		if ((String) session.getAttribute("AbonneConnected") != null) {
			c = false;
			d = false;
			int id_abonne = (int) session.getAttribute("id_abonne");
			Abonne abonne = abonneRepository.findById(id_abonne);
			Seance s = seanceRepository.findById(id_seance);
			abonne.getInscriptionSéance().forEach(a -> {
				if (a.getSeance().getId_seance() == id_seance) {
					c = true;
				}
			});

			if (!c) {
				c = false;
				List<InscriptionAbonnement> inscription = abonne.getInscriptionAbonnement();
				inscription.forEach(a -> {
					a.getAbonnement().getAbonnementDisciplines().forEach(b -> {
						if (b.getDiscipline().getId_discipline() == s.getDiscipline().getId_discipline()) {
							c = true;
							if (!a.isExpiration()) {
								d = true;
								LocalDate dateFin = a.getDateFin();

								long day2 = ChronoUnit.DAYS.between(date1, dateFin);
								if (day2 >= 0) {
									f = true;
									return;
								}
							}
						}

					});

				});

				if (c == true) {
					if (d) {
						if (f) {
							InscriptionSéance is = new InscriptionSéance();

							is.setAbonne(abonne);
							is.setSeance(s);
							inscriptionSeanceRepository.save(is);

							session.setAttribute("ReservationAdded", "Votre réservation a été faite");
						} else {
							session.setAttribute("AbEX", " votre abonnement n'est plus valable ce jour la");
						}
					} else {
						session.setAttribute("AbonnementDied", "Votre abonnement a déja expiré");
					}

				} else {
					session.setAttribute("ReservationEchec", "Cette discipline n'existe pas dans vos abonnements");
				}

			} else {
				session.setAttribute("ReservationAlreadyAdded", "Cette séance a été déjà réservée");
			}

			return "redirect:/AffichePlanning";
		} else {
			return "redirect:/index";
		}
	}

	// Method to handle the cancellation of a session reservation by a subscriber
	@GetMapping(value = "deleteReservation/{id_seance}")
	public String DeletSeanceAbonne(@PathVariable(name = "id_seance") int id_seance, HttpSession session) {
		if ((String) session.getAttribute("AbonneConnected") != null) {
			// InscriptionSéance s=
			int id_abonne = (int) session.getAttribute("id_abonne");
			inscriptionSeanceRepository.findAll().forEach(a -> {
				if ((a.getAbonne().getId_abonne() == id_abonne) && (a.getSeance().getId_seance() == id_seance)) {
					inscriptionSeanceRepository.delete(a);
				}
			});

			session.setAttribute("ReservationDeleted", "La réservation a été suppriméé");
			return "redirect:/AffichePlanning";
		} else {
			return "redirect:/index";
		}
	}

	// Method to redirect to the subscriber's dashbord
	@GetMapping(value = "/abonner/EspaceAbonne")
	public String EspaceAbonne2() {

		return "redirect:/EspaceAbonne";
	}

	// Method to redirect to the subscriber's dashbord
	@GetMapping(value = "/Payer/EspaceAbonne")
	public String EspaceAbonne4() {

		return "redirect:/EspaceAbonne";
	}

	// Method to redirect to the subscriber's dashbord
	@GetMapping(value = "ModifierInfo/EspaceAbonne")
	public String EspaceAbonne3() {

		return "redirect:/EspaceAbonne";
	}
}
