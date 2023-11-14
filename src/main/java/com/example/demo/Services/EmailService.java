package com.example.demo.Services;

import java.util.Properties;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

@Service
public class EmailService {
	
	// Method to send an email
	public boolean sendEmail(String subject, String message, String to) {
		boolean f = false; // Flag to indicate if the email was sent successfully

		String from = "sportsworld@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties); // Displaying properties (for debugging)
		// Configuring mail properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sportsworld@gmail.com", ""); // Authenticating with the email credentials
			}
		});

		session.setDebug(true); // Enabling debug mode
		MimeMessage m = new MimeMessage(session);// Creating a MimeMessage

		try {
			// Setting email details
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			// Sending the email
			Transport.send(m);
			System.out.println("sent success ..............");// Confirmation message
			f = true; // Email sent successfully

		} catch (Exception e) {
			e.printStackTrace();

		}
		return f;// Returning the status of the email sending process

	}
}
