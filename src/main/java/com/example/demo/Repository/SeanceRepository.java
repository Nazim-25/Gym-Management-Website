package com.example.demo.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.Coach;
import com.example.demo.DAO.Seance;

@Repository
@Transactional
public interface SeanceRepository extends JpaRepository<Seance, Integer> {

	@Query("select u from Seance u where u.HeureDebut=:time")
	public List<Seance> getSeance(@Param("time") LocalTime time);

	@Query("select u from Seance u where u.date=:date")
	public List<Seance> getSeance2(@Param("date") LocalDate date);

	@Query("select u from Seance u where u.date=:date AND u.HeureDebut=:time")
	public List<Seance> getSeance3(@Param("date") LocalDate date, @Param("time") LocalTime time);

	@Query("select u from Seance u where u.id_seance=:id_seance")
	public Seance getbyId(int id_seance);

	public Seance findById(int id_seance);

	@Query("select u from Seance u where u.coach=:coach")
	public List<Seance> getSeancesCoach(Coach coach);
}
