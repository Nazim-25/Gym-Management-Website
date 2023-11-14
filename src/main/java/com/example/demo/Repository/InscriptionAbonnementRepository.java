package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.InscriptionAbonnement;

@Repository
//@Transactional
public interface InscriptionAbonnementRepository extends JpaRepository<InscriptionAbonnement, Integer> {

	@Query("select u from InscriptionAbonnement u where u.dateFin BETWEEN :date1 AND :date2")
	public List<InscriptionAbonnement> AbonnementWarnning(@Param("date1") LocalDate date1,
			@Param("date2") LocalDate date2);

	@Query("select u from InscriptionAbonnement u where u.dateFin=:date1 ")
	public List<InscriptionAbonnement> AbonnementWarnning2(@Param("date1") LocalDate date1);

	@Query("select u from InscriptionAbonnement u where u.abonne.id_abonne=:id_abonne AND u.dateFin>=:date1 ")
	public List<InscriptionAbonnement> GetLiveAbonnement(@Param("date1") LocalDate date1,
			@Param("id_abonne") int id_abonne);
}
