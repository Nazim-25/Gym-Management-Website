package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.InscriptionSéance;
import com.example.demo.DAO.Seance;

//@Transactional
@Repository
public interface InscriptionSeanceRepository extends JpaRepository<InscriptionSéance, Integer> {

	public InscriptionSéance findById(int id);

	@Query("select u from InscriptionSéance u where u.seance.date=:date")
	public List<InscriptionSéance> getSeances(@Param("date") LocalDate date);

}
