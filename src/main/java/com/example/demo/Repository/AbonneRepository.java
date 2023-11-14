package com.example.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.Abonne;

@Repository
//@Transactional
public interface AbonneRepository extends JpaRepository<Abonne, Integer> {

	@Query("select u from Abonne u where u.email=:email")
	public Abonne getUserByUserName(@Param("email") String email);

	// , email=:ab.email, lastname=:ab.lastname,
	// username=:ab.username,num_tele=:ab.num_tele,date_nais=:ab.date_nais
	@Modifying
	@Transactional
	@Query(value = "update abonne a set a.firstname=:ab.firstname where a.id_abonne=:ab.id_abonne", nativeQuery = true)
	public void Modifier(@Param("ab") Abonne ab);

	public Abonne findByEmailContains(String email);

	public Abonne findByEmail(String email);

	public List<Abonne> findByFirstnameContains(String nom);

	public Abonne findById(int id_abonne);
	// <---------------------------------------------------------------->

	@Query("select u from Abonne u where u.email=:email and u.password=:password")
	public Abonne getAbonne(@Param("email") String email, @Param("password") String password);

	@Query("select u from Abonne u where u.firstname like %:keyword% or u.lastname like %:keyword%")
	public List<Abonne> findByKeyword(@Param("keyword") String keyword);

}
