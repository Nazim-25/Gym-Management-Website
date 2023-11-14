package com.example.demo.Metier;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.AbonnementsInforamtions;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.CompteBancaire;
import com.example.demo.DAO.Gerant;
import com.example.demo.DAO.Seance;
import com.example.demo.Repository.AbonneRepository;
import com.example.demo.Repository.AbonnementRepository;
import com.example.demo.Repository.CompteBancaireRepository;
import com.example.demo.Repository.SeanceRepository;

@Service
public class Operations {
	private static ConnexionBase con; // Connection base instance
	private static Connection Connect; // Connection instance
	boolean c = false; // Flag for connection status

	// Repositories injected for database operations
	@Autowired
	CompteBancaireRepository compteBancaireRepository;
	@Autowired
	private AbonneRepository abonneRepository;

	@Autowired
	private AbonnementRepository abonnementRepository;

	@Autowired
	private SeanceRepository seanceRepository;

	// Constructor to establish a database connection
	public Operations() {
		con = new ConnexionBase();
		Connect = con.getConnection();// Getting the database connection
	}

	// Method to authenticate an 'Abonne' (Subscriber)
	public boolean authentifyAbonne(Abonne abonne) throws SQLException {
		String query = "select * from Abonne where email = ? and password = ?";
		PreparedStatement st = null;
		ResultSet r = null;
		boolean b = false;

		try {
			st = this.Connect.prepareStatement(query); // Creating a prepared statement
			st.setString(1, abonne.getEmail()); // Setting parameters
			st.setString(2, abonne.getPassword());
			r = st.executeQuery(); // Executing the query

			if (r.next()) {// If a result is found

				abonne.setId_abonne(r.getInt("id_abonne"));
				b = true; // Authentication successful
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Closing resources in a finally block
			if (st != null || r != null)
				st.close(); // Closing the statement
			r.close(); // Closing the result set
		}
		return b; // Returning the authentication status
	}
	// Method to authenticate a 'Coach'
	public boolean authentifyCoach(Coach coach) throws SQLException {
		String query = "select * from Coach where email = ? and password = ?";
		PreparedStatement st = null;
		ResultSet r = null;
		boolean b = false;

		try {
			st = this.Connect.prepareStatement(query);
			st.setString(1, coach.getEmail());
			st.setString(2, coach.getPassword());
			r = st.executeQuery();

			if (r.next()) {

				coach.setId_coach(r.getInt("id_coach"));
				b = true;
			}
			st.close();
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null || r != null)
				st.close();
			r.close();
		}
		return b;
	}
	// Method to authenticate a 'Gerant' (Manager)
	public boolean authentifyGerant(Gerant gerant) throws SQLException {
		String query = "select * from Gerant where email = ? and password = ?";
		PreparedStatement st = null;
		ResultSet r = null;
		boolean b = false;

		try {
			st = this.Connect.prepareStatement(query);
			st.setString(1, gerant.getEmail());
			st.setString(2, gerant.getPassword());
			r = st.executeQuery();

			if (r.next()) {

				gerant.setId_gerant(r.getInt("id_gerant"));
				b = true;
			}
			st.close();
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null || r != null)
				st.close();
			r.close();
		}
		return b;
	}
	// Method to convert java.util.Date to java.sql.Date
	private static java.sql.Date convertUtilToSql(java.util.Date date) {
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		return sDate;
	}
	// Method to add an 'Abonne' (Subscriber) to the database
	public void addUser(Abonne Abonne) throws SQLException {
		// SQL query to insert Abonne details into the database
		String query = "insert into Abonne(username,password,email,firstname,lastname,num_tele,date_nais,date_inscription,genre) values (?,?,?, ?,?, ?,?,?,?)";
		PreparedStatement st = null;
		int r = 0; // Result of SQL execution

		 // Processing and formatting dates for insertion
		java.sql.Date sDate = new java.sql.Date(Abonne.getDate_nais().getTime() + (1000 * 60 * 60 * 24)); // Adjusting the birth date

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Date1 = formatter.format(Abonne.getDate_nais()); // Formatting date

		java.sql.Date inDate = new java.sql.Date(new java.util.Date().getTime());

		String nume_tele = String.valueOf(Abonne.getNum_tele()); // Converting telephone number to string

		st = this.Connect.prepareStatement(query);// Creating prepared statement
		// Setting parameters for the query
		st.setString(1, Abonne.getUsername());
		st.setString(4, Abonne.getFirstname());
		st.setString(5, Abonne.getLastname());
		st.setString(3, Abonne.getEmail());
		st.setString(6, nume_tele);
		st.setDate(7, sDate);
		st.setString(2, Abonne.getPassword());

		st.setDate(8, inDate); // Setting registration date
		st.setString(9, Abonne.getGenre());

		r = st.executeUpdate();// Executing the query
        st.close(); // Closing the statement

	}
	
	// Method to check if essential 'Abonne' details are present
	public boolean Check(Abonne abonne) {
		boolean b = false;// Flag for validation

	    // Checking if any essential details are empty
		String x = abonne.getNum_tele();
		String s = String.valueOf(x);
		String a = String.valueOf(abonne.getDate_nais());

		if (abonne.getUsername() == "" || abonne.getFirstname() == "" || abonne.getLastname() == ""
				|| abonne.getEmail() == "" || s == "" || abonne.getPassword() == "" || a == "") {

			b = true;// Set flag if any essential detail is missing
		}

		return b;// Return the validation status
	}

	// Method to retrieve emails of all 'Abonne' (Subscribers) from the database
	public ArrayList<Abonne> GetMails() throws SQLException {
		ArrayList<Abonne> Liste = new ArrayList<Abonne>();
		String query = "select email from abonne"; // SQL query to retrieve emails
		try {
			java.sql.Statement st = Connect.createStatement();
			ResultSet r = st.executeQuery(query);
			// Iterating through the results and adding Abonne emails to the list
			while (r.next()) {
				Abonne abonne = new Abonne();
				abonne.setEmail(r.getString("email"));

				Liste.add(abonne);
			}
			for (Abonne liste : Liste) {
				System.out.println("" + liste.getEmail());
			}

			st.close();
			r.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return Liste;

	}
	// Method to modify details of an 'Abonne' in the database
	public void ModifierAbonne(Abonne abonne) {
		String query = "update abonne  set firstname=?,email=?, lastname=?, username=?,num_tele=?,date_nais=?,genre=? where id_abonne=?";
		PreparedStatement st = null;
		int r = 0;
		boolean b = false;
		// java.sql.Date sDate = convertUtilToSql(abonne.getDate_nais());
		// java.sql.Date sDate = new java.sql.Date(abonne.getDate_nais().getTime()+
		// (1000 * 60 * 60 * 24));
		// System.out.println( sDate);
		try {
			st = this.Connect.prepareStatement(query);
			st.setString(1, abonne.getFirstname());
			st.setString(2, abonne.getEmail());
			st.setString(3, abonne.getLastname());
			st.setString(4, abonne.getUsername());
			st.setString(5, abonne.getNum_tele());
			st.setDate(6, new java.sql.Date(abonne.getDate_nais().getTime() + (1000 * 60 * 60 * 24)));
			st.setString(7, abonne.getGenre());
			st.setInt(8, abonne.getId_abonne());

			r = st.executeUpdate();

			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return b;
	}
	
	// Method to save an 'Abonnement' (Subscription) for an 'Abonne' in the database
	public void saveAbonnement(Abonne abonne, Abonnement abonnement) {
		// SQL query to insert a new subscription for an Abonne
		String query = "insert into inscription_abonnement " 
				+ "(abonnement_id_abonnement,abonne_id_abonne,date_fin,expiration ) "
				+ "values (?,?,?,?) ";
		PreparedStatement st = null;
		int r = 0;
		int a = abonnement.getDuree();
		LocalDate today = LocalDate.now();
		LocalDate dateFin = today.plusMonths(a);

		java.sql.Date sqlDate = java.sql.Date.valueOf(dateFin);

		try {
			st = this.Connect.prepareStatement(query);
			st.setInt(1, abonnement.getId_abonnement());
			st.setInt(2, abonne.getId_abonne());
			st.setDate(3, sqlDate);
			st.setByte(4, (byte) 0);
			r = st.executeUpdate();

			st.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	// Method to get a list of 'Seance' (Session) entities
	public List<Seance> getSeances() {

		List<Seance> seancesList = seanceRepository.findAll();
		return seancesList;

	}
	// Method to get a list of session IDs attended by 'Abonne' within a specific date range
	public List<Integer> SeanceAbonné() throws SQLException {
		String query = "select * from seance where  date BETWEEN ? AND ? ";
		PreparedStatement st = null;
		ResultSet r = null;
		boolean b = false;
		List<Integer> seances = new ArrayList<Integer>();

		LocalDate today = LocalDate.now().plusDays(1);
		LocalDate todayplus6 = today.plusDays(7);

		java.sql.Date sqlDate = java.sql.Date.valueOf(today);
		java.sql.Date sqlDate1 = java.sql.Date.valueOf(todayplus6);
		try {
			st = this.Connect.prepareStatement(query);

			st.setDate(1, sqlDate);
			st.setDate(2, sqlDate1);
			r = st.executeQuery();

			while (r.next()) {

				seances.add(r.getInt("Id_seance"));
				b = true;
			}
			st.close();
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null || r != null)
				st.close();
			r.close();
		}
		return seances;
	}
	// Method to retrieve a list of session IDs attended by subscribers for a given session
	public List<Integer> SeancesAbonnés(int id_seance) throws SQLException {
		String query = "select * from inscription_séance  where seance_id_seance=? ";
		PreparedStatement st = null;
		ResultSet r = null;
		boolean b = false;
		List<Integer> seances = new ArrayList<Integer>();
		try {
			st = this.Connect.prepareStatement(query);
			st.setInt(1, id_seance);
			r = st.executeQuery();
			// Fetching and storing subscription IDs
			while (r.next()) {
				seances.add(r.getInt("id_inscription_séance"));
			}
			st.close();
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null || r != null)
				st.close();
			r.close();
		}
		return seances;
	}
	// Method to retrieve the ID of the last entry in the 'planning' table
	public int LastPlanning() throws SQLException {
		String query = "select max(id_planning) from planning";
		PreparedStatement st = null;
		ResultSet r = null;
		int a = 0;
		try {
			st = this.Connect.prepareStatement(query);
			r = st.executeQuery();
			// Getting the maximum ID if available
			if (r.next()) {
				a = r.getInt(1);
			}
			st.close();
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null || r != null)
				st.close();
			r.close();
		}
		return a;// Returning the maximum ID from the 'planning' table
	}
	// Method to retrieve the ID before the last entry in the 'planning' table
	public int BeforeLastPlanning() throws SQLException {
		// SQL query to find the second-to-last ID in the 'planning' table
		String query = " SELECT id_planning from planning ORDER BY id_planning DESC LIMIT 1, 2  ";
		PreparedStatement st = null;
		ResultSet r = null;
		int a = 0;
		try {
			st = this.Connect.prepareStatement(query);
			r = st.executeQuery();
			if (r.next()) {
				a = r.getInt(1);
			}
			st.close();
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null || r != null)
				st.close();
			r.close();
		}
		return a;// Returning the second-to-last ID from the 'planning' table
	}

}
