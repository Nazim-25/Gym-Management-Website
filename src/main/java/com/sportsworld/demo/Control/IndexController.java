package com.example.demo.Control;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.Gerant;
import com.example.demo.Metier.Operations;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.CoachRepository;
import com.example.demo.Repository.GerantRepository;
import com.example.demo.Services.EmailService;
import com.example.demo.Services.GerantService;

@Controller
public class IndexController {
	// Repositories injected for database operations
	@Autowired
	private GerantService gerantService;

	@Autowired
	private AbonneRepository abonneRepository;

	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private GerantRepository gerantRepository;

	@Autowired
	private EmailService emailService;

	 // Displays the main index page
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		return "index";
	}
	// Shows the login form
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView showLoginForm() {
		return new ModelAndView("authentification", "Abonne", new Abonne());
	}

	// Processes login attempts
	@RequestMapping(value = "/authentification", method = RequestMethod.POST)
	public String login2(@ModelAttribute("Abonne") Abonne abonne, HttpSession session) throws SQLException {
		String email = abonne.getEmail();

		String password = abonne.getPassword();
		System.out.println(email);
		System.out.println(password);

		Coach coach = new Coach();
		Gerant gerant = new Gerant();
		coach.setEmail(email);
		coach.setPassword(password);

		gerant.setEmail(email);
		gerant.setPassword(password);

		Operations obj = new Operations();
		// Login authentication process based on user role
		// Code that handles login attempts and session management
		if (session.getAttribute("logins") == null) {
			session.setAttribute("logins", 0);
		}
		int attempts = (int) session.getAttribute("logins");

		if (attempts <= 2 && obj.authentifyAbonne(abonne)) {

			session.setAttribute("email", abonne.getEmail());
			session.setAttribute("Username", abonne.getUsername());
			session.setAttribute("id_abonne", abonne.getId_abonne());
			session.setAttribute("AbonneConnected", "yes");
			return "EspaceAbonne";
		} else if (obj.authentifyCoach(coach) && attempts <= 2) {

			session.setAttribute("emailCoach", coach.getEmail());
			session.setAttribute("UsernameCoach", coach.getUsername());
			session.setAttribute("id_coach", coach.getId_coach());
			session.setAttribute("CoachConnected", "yes");
			return "EspaceCoach";
		} else if (obj.authentifyGerant(gerant) && attempts <= 2) {
			session.setAttribute("emailGerant", gerant.getEmail());
			session.setAttribute("UsernameGerant", gerant.getUsername());
			session.setAttribute("id_gerant", gerant.getId_gerant());
			session.setAttribute("GerantConnected", "yes");
			return "EspaceGerant";

		} else if (attempts > 3) {
			session.invalidate();
			return "index"; // Redirects back to the index page
		} else {
			session.setAttribute("messageAbonne", "email ou mot de pass incorrect");
			session.setAttribute("logins", ++attempts);
			return "authentification";
		}

	}


	@GetMapping(value = "/SignUp")
	public ModelAndView Signup() {
		// Renders the view for user sign-up 
		return new ModelAndView("AddUser", "Abonne", new Abonne());
	}

	@PostMapping(value = "/AddUser")
	public String AddClient(@ModelAttribute("Abonne") Abonne Abonne, Model model, HttpSession session)
			throws SQLException {
		Operations obj1 = new Operations();
		
		// Checks if the user already exists in the database
		boolean Y = gerantService.UserExist2(Abonne);
		System.out.println(Y);
		if (Y) {
			 // If the user exists, displays an error message and remains on the sign-up page
			session.setAttribute("messageErreur", "Email existe déja");
			return "AddUser";
		} else {
			// Adds the user and sets session attributes for the added user
			obj1.addUser(Abonne);
			Abonne a = abonneRepository.findByEmail(Abonne.getEmail());
			session.setAttribute("email", Abonne.getEmail());
			session.setAttribute("id_abonne", a.getId_abonne());
			//session.setAttribute("UserAdded", " l'abonné est ajouté avec succes");
			session.setAttribute("AbonneConnected", "yes");
			// Redirects to the subscriber's space after successful addition
			return "EspaceAbonne";
		}

	}
	
	// Handles user logout
	@GetMapping(value = "/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	 // Redirects to the main logout route
	@GetMapping(value = "/ModifierInfo/logout")
	public String LogoutAboone(HttpSession session) {
		
		return "redirect:/logout";
	}
	
	// Redirects to the main logout route
	@GetMapping(value = "/ModifierInfo/ChangePassword/logout")
	public String LogoutAboone2(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/ModifierInfoCoach/logout")
	public String LogoutCoach(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/ModifierInfoCoach/ChangePasswordCoach/logout")
	public String LogoutCoach2(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/edit/logout")
	public String LogoutGerant(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/editCoach/logout")
	public String LogoutGerant2(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/editCoach/ModifierDisciplines/logout")
	public String LogoutGerant3(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/editDiscipline/logout")
	public String LogoutGerant4(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/editAbonnement/logout")
	public String LogoutGerant5(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/ModifierInfoGerant/logout")
	public String LogoutGerant6(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/ModifierInfoGerant/ChangePasswordGerant/logout")
	public String LogoutGerant7(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/abonner/logout")
	public String LogoutAbonne(HttpSession session) {

		return "redirect:/logout";
	}
	// Redirects to the main logout route
	@GetMapping(value = "/showPlanning/logout")
	public String LogoutGerant10(HttpSession session) {

		return "redirect:/logout";
	}

}
