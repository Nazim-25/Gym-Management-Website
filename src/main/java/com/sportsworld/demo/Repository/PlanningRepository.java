package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Planning;
import com.example.demo.DAO.Seance;

@Repository
//@Transactional
public interface PlanningRepository extends JpaRepository<Planning, Integer> {

	public Planning findById(int id_planning);

	@Query("select u from Planning u where u.dateDebut=:date")
	public Planning getPlanning(@Param("date") LocalDate date);
}
