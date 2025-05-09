package com.example.demo.Control;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ForgotController {
	// Repositories injected for database operations
	@Autowired
	private EmailService emailService;
	@Autowired
	private GerantService gerantService;
	@Autowired
	private AbonneRepository abonneRepository;
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private GerantRepository gerantRepository;

	Operations obj = new Operations();

	@GetMapping(value = "/mdpOublier")
	public String openEmailForm(@ModelAttribute("Abonne") Abonne abonne) {
		// Renders the forgot password email form
		return "forgot_email_form";
	}

	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam("email") String email, @ModelAttribute("Abonne") Abonne Abonne,
			HttpSession session) throws SQLException {
		System.out.println("Email : " + email);
		boolean flag = false;
		// Checks the provided email to send an OTP for password reset
		int y = gerantService.UserExistForMdp(email);

		Random random = new Random();

		if (y != 0) { // If the email exists, generates an OTP and sends it via email
			// Generate random OTP
			int otp = random.nextInt(99999);
			System.out.println("OTP : " + otp);

			String subject = "OTP From SPORTS WORLD";
			String message = " Le code OTP=" + otp;
			String to = email;
			// Send OTP via email
			flag = this.emailService.sendEmail(subject, message, to);

			if (flag) {
				// Save OTP and email in session
				session.setAttribute("myOtp", otp);
				session.setAttribute("email", email);
				// Determine user type and save in session
				if (y == 1) {
					session.setAttribute("type", "abonne");
				} else if (y == 2) {
					session.setAttribute("type", "coach");
				} else if (y == 3) {
					session.setAttribute("type", "gerant");
				}
				// Redirect to verify OTP page
				return "verify_otp";
			} else {
				session.setAttribute("message", "check your email id !!");
				return "forgot_email_form";
			}
		} else {
			session.setAttribute("message", "Verify votre adresse mail !! ");
			return "forgot_email_form";

		}

	}
	// Check if the OTP code entered matches the one that was sent
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") int otp, @ModelAttribute("Abonne") Abonne abonne,
			HttpSession session) {
		// Get OTP from session
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");
		
		// Verify OTP
		if (myOtp == otp) {
			return "Mdp_change_form";

		} else {
			session.setAttribute("messageOTP", "Vous avez entré le mauvais OTP !!");
			return "verify_otp";
		}

	}
	
	@PostMapping("/changer-mdp")
	public ModelAndView changerMdp(@RequestParam("newpassword") String newpassword, HttpSession session,
			@ModelAttribute("Abonne") Abonne abonne) throws SQLException {
		// Get user type and email from session
		String type = (String) session.getAttribute("type");
		String email = (String) session.getAttribute("email");
		// Change password based on user type
		if (type.equals("abonne")) {
			// Find and update password for Abonne
			Abonne ab = this.abonneRepository.findByEmailContains(email);
			ab.setPassword(newpassword);
			this.abonneRepository.save(ab);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("coach")) {
			// Find and update password for Coach
			Coach coach = this.coachRepository.findByEmailContains(email);
			coach.setPassword(newpassword);
			this.coachRepository.save(coach);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("gerant")) {
			// Find and update password for Gerant
			Gerant gerant = this.gerantRepository.findByEmailContains(email);
			gerant.setPassword(newpassword);
			this.gerantRepository.save(gerant);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		}
		// Redirect to login page
		return new ModelAndView("login");

	}
	// <---------------------------------------------------------------->

	@GetMapping(value = "ModifierInfo/mdpOublierAbonne")
	public String openForgetEmailForm(@ModelAttribute("Abonne") Abonne abonne, HttpSession session) {
		// Check if Abonne is connected
		if ((String) session.getAttribute("AbonneConnected") != null) {
			// Render the forgot password email form for Abonne
			return "forgot_email_formAbonne";
		} else {
			// Redirect to index if Abonne is not connected
			return "redirect:/index";
		}
	}

	@PostMapping("/send-otp-Abonne")
	public String sendOtpAbonne(@RequestParam("email") String email, @ModelAttribute("Abonne") Abonne Abonne,
			HttpSession session) throws SQLException {
		System.out.println("Email : " + email);
		boolean flag = false;
		// Check if user exists for password reset
		int y = gerantService.UserExistForMdp(email);

		Random random = new Random();

		if (y != 0) {
			// Generate and print OTP to console
			int otp = random.nextInt(99999);
			System.out.println("OTP : " + otp);

			String subject = "OTP From SPORTS WORLD";
			String message = " Le code OTP=" + otp;
			String to = email;
			// Send OTP via email
			flag = this.emailService.sendEmail(subject, message, to);

			if (flag) {
				// Save OTP, email, and user type in session
				session.setAttribute("myOtp", otp);
				session.setAttribute("email", email);
				if (y == 1) {
					session.setAttribute("type", "abonne");
				} else if (y == 2) {
					session.setAttribute("type", "coach");
				} else if (y == 3) {
					session.setAttribute("type", "gerant");
				}
				// Redirect to verify OTP page for Abonne
				return "verify_otp-Abonne";
			} else {
				// Show error message and render the forgot password email form for Abonne
				session.setAttribute("message", "check your email id !!");
				return "forgot_email_formAbonne";
			}
		} else {
			// Show error message and render the forgot password email form for Abonne
			session.setAttribute("message", "Verify votre adresse mail !! ");
			return "forgot_email_formAbonne";

		}

	}
	// Handles the verification of OTP for Abonne
	@PostMapping("/verify-otp-Abonne")
	public String verifyOtpAbonne(@RequestParam("otp") int otp, @ModelAttribute("Abonne") Abonne abonne,
			HttpSession session) {
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");

		if (myOtp == otp) {

			return "Mdp_change_form_Abonne";

		} else {
			session.setAttribute("messageOTP", "Vous avez entré le mauvais OTP !!");
			return "verify_otp-Abonne";
		}
	}
	// Handles the password change for Abonne
	@PostMapping("/changer-mdp-Abonne")
	public String changerMdpAbonne(@RequestParam("newpassword") String newpassword, HttpSession session,
			@ModelAttribute("Abonne") Abonne abonne) throws SQLException {
		String type = (String) session.getAttribute("type");
		String email = (String) session.getAttribute("email");

		if (type.equals("abonne")) {
			Abonne ab = this.abonneRepository.findByEmailContains(email);
			ab.setPassword(newpassword);
			this.abonneRepository.save(ab);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("coach")) {
			Coach coach = this.coachRepository.findByEmailContains(email);
			coach.setPassword(newpassword);
			this.coachRepository.save(coach);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("gerant")) {
			Gerant gerant = this.gerantRepository.findByEmailContains(email);
			gerant.setPassword(newpassword);
			this.gerantRepository.save(gerant);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		}

		return "redirect:/EspaceAbonne";

	}
	// Renders the forgot email form for Coach
	@GetMapping(value = "ModifierInfoCoach/mdpOublierCoach")
	public String openForgetEmailFormCoacj(@ModelAttribute("Abonne") Abonne abonne, HttpSession session) {
		if ((String) session.getAttribute("CoachConnected") != null) {

			return "forgot_email_formCoach";
		} else {
			return "redirect:/index";
		}
	}
	// Sends OTP for Coach
	@PostMapping("/send-otp-Coach")
	public String sendOtpCoach(@RequestParam("email") String email, @ModelAttribute("Abonne") Abonne Abonne,
			HttpSession session) throws SQLException {
		System.out.println("Email : " + email);
		boolean flag = false;

		int y = gerantService.UserExistForMdp(email);

		Random random = new Random();

		if (y != 0) {
			int otp = random.nextInt(99999);
			System.out.println("OTP : " + otp);

			String subject = "OTP From SPORTS WORLD";
			String message = " Le code OTP=" + otp;
			String to = email;

			flag = this.emailService.sendEmail(subject, message, to);

			if (flag) {
				session.setAttribute("myOtp", otp);
				session.setAttribute("email", email);
				if (y == 1) {
					session.setAttribute("type", "abonne");
				} else if (y == 2) {
					session.setAttribute("type", "coach");
				} else if (y == 3) {
					session.setAttribute("type", "gerant");
				}
				return "verify_otp-Coach";
			} else {
				session.setAttribute("message", "check your email id !!");
				return "forgot_email_formCoach";
			}
		} else {
			session.setAttribute("message", "Verify votre adresse mail !! ");

			return "forgot_email_formCoach";

		}

	}
	// Verify OTP for Coach
	@PostMapping("/verify-otp-Coach")
	public String verifyOtpCoach(@RequestParam("otp") int otp, @ModelAttribute("Abonne") Abonne abonne,
			HttpSession session) {
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");

		if (myOtp == otp) {

			return "Mdp_change_form_Coach";

		} else {
			session.setAttribute("messageOTP", "Vous avez entré le mauvais OTP !!");
			return "verify_otp-Coach";
		}
	}
	// Change password for Coach
	@PostMapping("/changer-mdp-Coach")
	public String changerMdpCoach(@RequestParam("newpassword") String newpassword, HttpSession session,
			@ModelAttribute("Abonne") Abonne abonne) throws SQLException {
		String type = (String) session.getAttribute("type");
		String email = (String) session.getAttribute("email");

		if (type.equals("abonne")) {
			Abonne ab = this.abonneRepository.findByEmailContains(email);
			ab.setPassword(newpassword);
			this.abonneRepository.save(ab);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("coach")) {
			Coach coach = this.coachRepository.findByEmailContains(email);
			coach.setPassword(newpassword);
			this.coachRepository.save(coach);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("gerant")) {
			Gerant gerant = this.gerantRepository.findByEmailContains(email);
			gerant.setPassword(newpassword);
			this.gerantRepository.save(gerant);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		}

		return "redirect:/espacecoach";

	}
	// Open forget email form for Gerant
	@GetMapping(value = "ModifierInfoGerant/mdpOublierGerant")
	public String openForgetEmailFormGerant(@ModelAttribute("Abonne") Abonne abonne, HttpSession session) {
		if ((String) session.getAttribute("GerantConnected") != null) {

			return "forgot_email_formGerant";
		} else {
			return "redirect:/index";
		}
	}	
	// Send OTP for Gerant
	@PostMapping("/send-otp-Gerant")
	public String sendOtpGerant(@RequestParam("email") String email, @ModelAttribute("Abonne") Abonne Abonne,
			HttpSession session) throws SQLException {
		System.out.println("Email : " + email);
		boolean flag = false;

		int y = gerantService.UserExistForMdp(email);

		Random random = new Random();

		if (y != 0) {
			int otp = random.nextInt(99999);
			System.out.println("OTP : " + otp);

			String subject = "OTP From SPORTS WORLD";
			String message = " Le code OTP=" + otp;
			String to = email;

			flag = this.emailService.sendEmail(subject, message, to);

			if (flag) {
				session.setAttribute("myOtp", otp);
				session.setAttribute("email", email);
				if (y == 1) {
					session.setAttribute("type", "abonne");
				} else if (y == 2) {
					session.setAttribute("type", "coach");
				} else if (y == 3) {
					session.setAttribute("type", "gerant");
				}

				return "verify_otp-Gerant";
			} else {
				session.setAttribute("message", "check your email id !!");
				return "forgot_email_formGerant";
			}
		} else {
			session.setAttribute("message", "Verify votre adresse mail !! ");

			return "forgot_email_formGerant";

		}

	}
	// Verify OTP for Gerant
	@PostMapping("/verify-otp-Gerant")
	public String verifyOtpGerant(@RequestParam("otp") int otp, @ModelAttribute("Abonne") Abonne abonne,
			HttpSession session) {
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");

		if (myOtp == otp) {

			return "Mdp_change_form_Gerant";

		} else {
			session.setAttribute("messageOTP", "Vous avez entré le mauvais OTP !!");
			return "verify_otp-Gerant";
		}
	}

	// Change password for Gerant
	@PostMapping("/changer-mdp-Gerant")
	public String changerMdpGerant(@RequestParam("newpassword") String newpassword, HttpSession session,
			@ModelAttribute("Abonne") Abonne abonne) throws SQLException {
		// Retrieve user type and email from the session
		String type = (String) session.getAttribute("type");
		String email = (String) session.getAttribute("email");
		
		// Check the user type and update the password accordingly
		if (type.equals("abonne")) {
			Abonne ab = this.abonneRepository.findByEmailContains(email);
			ab.setPassword(newpassword);
			this.abonneRepository.save(ab);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("coach")) {
			Coach coach = this.coachRepository.findByEmailContains(email);
			coach.setPassword(newpassword);
			this.coachRepository.save(coach);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		} else if (type.equals("gerant")) {
			Gerant gerant = this.gerantRepository.findByEmailContains(email);
			gerant.setPassword(newpassword);
			this.gerantRepository.save(gerant);
			session.setAttribute("success", "le mot de passe a été modifié avec succés");
		}
		// Redirect to the Gerant dashboard
		return "redirect:/espacegerant";

	}
}
