package com.example.demo.Services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.CoachDisciplines;
import com.example.demo.DAO.Discipline;
import com.example.demo.DAO.Gerant;
import com.example.demo.DAO.InscriptionAbonnement;
import com.example.demo.DAO.Planning;
import com.example.demo.Metier.Operations;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.AbonnementRepository;
import com.example.demo.Repository.CoachDisciplinesRepository;
import com.example.demo.Repository.CoachRepository;
import com.example.demo.Repository.DisciplineRepository;
import com.example.demo.Repository.GerantRepository;
import com.example.demo.Repository.InscriptionAbonnementRepository;
import com.example.demo.Repository.PlanningRepository;

@Service
public class GerantService {
	// Repositories injected for database operations
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private AbonneRepository abonneRepository;

	@Autowired
	private AbonnementRepository abonnementRepository;

	@Autowired
	private GerantRepository gerantRepository;
	@Autowired
	private CoachDisciplinesRepository coachDisciplinesRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;

	@Autowired
	private PlanningRepository planningRepository;

	@Autowired
	private InscriptionAbonnementRepository inscriptionAbonnementRepository;

	@Autowired
	private EmailService emailService;

	boolean b = false;

	// Method to check if an 'Abonne' already exists by email in the system
	public boolean UserExist2(Abonne abonne) {

		b = false;// Flag to determine the existence of the user
		
		// Checking 'Coach', 'Abonne', and 'Gerant' repositories for the provided email
		coachRepository.findAll().forEach(a -> {
			if (a.getEmail().equals(abonne.getEmail())) {
				b = true;// Set flag if found
	            return; // Exiting the loop
			}
		});
		abonneRepository.findAll().forEach(d -> {
			if (d.getEmail().equals(abonne.getEmail())) {
				b = true;
				return;

			}
		});
		gerantRepository.findAll().forEach(c -> {
			if (c.getEmail().equals(abonne.getEmail())) {
				b = true;
				return;

			}
		});

		return b; // Returning whether the user exists
	}

	int y = 0;
	// Method to determine the type of user (Coach, Abonne, or Gerant) based on the email
	public int UserExistForMdp(String email) {
		y = 0;// Variable to represent user type
		
		// Checking 'Coach', 'Abonne', and 'Gerant' repositories for the provided email
		coachRepository.findAll().forEach(a -> {
			if (a.getEmail().equals(email)) {
				y = 2;// Set as Coach
				return;

			}
		});
		abonneRepository.findAll().forEach(d -> {
			if (d.getEmail().equals(email)) {
				y = 1;// Set as Abonne
				return;

			}
		});
		gerantRepository.findAll().forEach(c -> {
			if (c.getEmail().equals(email)) {
				y = 3; // Set as Gerant
				return;// Exiting the loop

			}
		});

		return y;// Returning the user type
	}

	// Method to check if a 'Coach' already exists by email in the system
	public boolean UserExist(Coach Coach) {

		b = false;
		coachRepository.findAll().forEach(a -> {
			if (a.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});
		abonneRepository.findAll().forEach(d -> {
			if (d.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});
		gerantRepository.findAll().forEach(c -> {
			if (c.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});

		return b;
	}

	// Method to check if a 'Coach' exists by email while excluding a specific email
	public boolean UserExist2(Coach Coach, String email) {

		b = false;
		coachRepository.findAll().forEach(a -> {
			if (a.getEmail().equals(Coach.getEmail())) {
				if (Coach.getEmail().equals(email)) {
					b = false;
				} else {
					b = true;
					return;
				}
			}
		});
		abonneRepository.findAll().forEach(d -> {
			if (d.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});
		gerantRepository.findAll().forEach(c -> {
			if (c.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});

		return b;
	}
	// Method to check if an 'Abonne' exists by email while excluding a specific email
	public boolean UserExistAbonne(Abonne abonne, String email) {

		b = false;
		abonneRepository.findAll().forEach(a -> {
			if (a.getEmail().equals(abonne.getEmail())) {
				if (abonne.getEmail().equals(email)) {
					b = false;
				} else {
					b = true;
					return;
				}
			}
		});
		coachRepository.findAll().forEach(d -> {
			if (d.getEmail().equals(abonne.getEmail())) {
				b = true;
				return;

			}
		});
		gerantRepository.findAll().forEach(c -> {
			if (c.getEmail().equals(abonne.getEmail())) {
				b = true;
				return;

			}
		});

		return b;
	}
	// Method to check if a 'Gerant' exists by email while excluding a specific email
	public boolean UserExist3(Gerant Coach, String email) {

		b = false;
		gerantRepository.findAll().forEach(a -> {
			if (a.getEmail().equals(Coach.getEmail())) {
				if (Coach.getEmail().equals(email)) {
					b = false;
				} else {
					b = true;
					return;
				}
			}
		});
		abonneRepository.findAll().forEach(d -> {
			if (d.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});
		coachRepository.findAll().forEach(c -> {
			if (c.getEmail().equals(Coach.getEmail())) {
				b = true;
				return;

			}
		});

		return b;
	}
	// Method to add a 'Coach' to the repository
	public void addCoach(Coach Coach) {
		java.sql.Date RcDate = new java.sql.Date(new java.util.Date().getTime());
		Coach Co = new Coach();
		Co.setFirstname(Coach.getFirstname());
		Co.setLastname(Coach.getLastname());
		Co.setUsername(Coach.getUsername());
		Co.setEmail(Coach.getEmail());
		Co.setPassword(Coach.getPassword());
		Co.setAge(Coach.getAge());
		Co.setExperience(Coach.getExperience());
		Co.setNum_tele(Coach.getNum_tele());
		Co.setDate_nais(new java.sql.Date(Coach.getDate_nais().getTime() + (1000 * 60 * 60 * 24)));
		Co.setDate_recrutement(RcDate);
		Co.setSalaire(Coach.getSalaire());
		Co.setGenre(Coach.getGenre());

		coachRepository.save(Co);

	}
	// Method to add a discipline to a coach's disciplines
	public boolean addDisciplineCoach(Coach coach, int id) {

		boolean Dc = false;
		Discipline s = disciplineRepository.findById(id);
		System.out.println("" + s.getNomDiscipline());

		List<CoachDisciplines> listCoachDisciplines = coach.getCoachDisciplines();

		boolean oui = listCoachDisciplines.isEmpty();

		if (oui) {

			CoachDisciplines cd = new CoachDisciplines(coach, s);
			coachDisciplinesRepository.save(cd);

		} else {

			for (CoachDisciplines cs : listCoachDisciplines) {

				Discipline d = cs.getDiscipline();
				Coach c = cs.getCoach();

				if (d.getId_discipline() == id && c.getId_coach() == coach.getId_coach()) {

					Dc = true;
					break;

				}

			}
			if (!Dc) {
				CoachDisciplines cd = new CoachDisciplines(coach, s);
				coachDisciplinesRepository.save(cd);
			}
		}

		return Dc;

	}
	// Method to check if a discipline exists while excluding a specific discipline name
	public boolean DisciplineExist(Discipline discipline, String NomDiscipline) {

		b = false;
		disciplineRepository.findAll().forEach(a -> {
			if (a.getNomDiscipline().equals(discipline.getNomDiscipline())) {
				if (discipline.getNomDiscipline().equals(NomDiscipline)) {
					b = false;
				} else {
					b = true;
					return;
				}
			}
		});

		return b;
	}
	// Method to check if a discipline exists by its name
	public boolean DisciplineExist2(String NomDiscipline) {

		b = false;
		disciplineRepository.findAll().forEach(a -> {
			if (a.getNomDiscipline().equals(NomDiscipline)) {
				b = true;
				return;
			}
		});

		return b;
	}
	// Method to check if an 'Abonnement' exists based on specific attributes
	public boolean AbonnementExist(Abonnement abonnement) {

		b = false;
		abonnementRepository.findAll().forEach(a -> {
			if (a.getTarif() == abonnement.getTarif() && a.getDuree() == abonnement.getDuree()
					&& a.getDiscipline().getId_discipline() == abonnement.getDiscipline().getId_discipline()) {
				b = true;
				return;
			}
		});

		return b;
	}
	// Method to find 'Abonne' entities based on a keyword
	public List<Abonne> findByKeyword(String Keyword) {
		return abonneRepository.findByKeyword(Keyword);

	}
	// Method to find 'Coach' entities based on a keyword
	public List<Coach> findByKeyword2(String Keyword) {
		return coachRepository.findByKeyword(Keyword);

	}
	// Method triggered by a scheduled task to perform actions daily
	Operations op = new Operations();

	@Scheduled(cron = "0 0 8 * * * ")
	public void EveryDay() throws SQLException {
		// Retrieving the last and before-last planning details
		int lastPlanning = op.LastPlanning();

		// Some date calculations and validations based on the retrieved planning details
		Planning planning = planningRepository.findById(lastPlanning);
		LocalDate today = LocalDate.now();
		int beforeLast = op.BeforeLastPlanning();
		Planning planning2 = planningRepository.findById(beforeLast);
		LocalDate beforeLastDin = planning2.getDateFin();
		// System.out.println(beforeLastDin);

		long day2 = ChronoUnit.DAYS.between(beforeLastDin, today);
		long month2 = ChronoUnit.MONTHS.between(beforeLastDin, today);
		long year2 = ChronoUnit.YEARS.between(beforeLastDin, today);

		System.out.println(day2);
		// Creating a new planning entry if a specific condition is met
		if (day2 == 1 && month2 == 0 && year2 == 0) {
			Planning newPlanning = new Planning();
			// Setting attributes for the new planning entry
			newPlanning.setId_planning(lastPlanning + 1);
			newPlanning.setNbrSeance(0);
			newPlanning.setDateDebut(today.plusDays(9));
			newPlanning.setDateFin(today.plusDays(15));
			planningRepository.save(newPlanning);
		}

		System.out.println(today.minusDays(3));
		 // Retrieving lists of subscriptions nearing expiration for warning and notification purposes
		List<InscriptionAbonnement> WarnningList = inscriptionAbonnementRepository
				.AbonnementWarnning2(today.minusDays(2));
		List<InscriptionAbonnement> WarnningList2 = inscriptionAbonnementRepository
				.AbonnementWarnning2(today.plusDays(1));
		
		// Iterating through subscriptions nearing expiration to send warnings and notifications
		WarnningList2.forEach(a -> {
			System.out.println(a.getDateFin());
			String email = a.getAbonne().getEmail();
			if (!a.isExpiration()) {
				a.setExpiration(true);
				// a.setDateFin(a.getDateFin().plusDays(1));
				inscriptionAbonnementRepository.save(a);

				List<String> list = new ArrayList<String>();
				a.getAbonnement().getAbonnementDisciplines().forEach(c -> {
					String s = c.getDiscipline().getNomDiscipline();
					list.add(s);
				});

				String subject = "SPORTS WORLD";
				// String message ="Cher abonne "+a.getAbonne().getFirstname()+" votre
				// abonnement de "+ list.toString() +" expirer aujourd'hui";
				String message = "Cher abonne " + a.getAbonne().getFirstname() + " votre abonnement de "
						+ list.toString() + " a expiré";
				String to = email;
				
				// Sending expiration notifications and updating subscription status
				emailService.sendEmail(subject, message, to);
			}

		});

		WarnningList.forEach(a -> {

			String subject = "SPORTS WORLD";
			String message = "Cher abonne " + a.getAbonne().getFirstname() + " votre abonnement Expire dans 3 jours ";
			String to = a.getAbonne().getEmail();
			// Sending warning emails for subscriptions nearing expiration
			emailService.sendEmail(subject, message, to);

		});

	}

}
