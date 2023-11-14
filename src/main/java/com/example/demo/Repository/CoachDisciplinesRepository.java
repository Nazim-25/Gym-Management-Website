package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Coach;
import com.example.demo.DAO.CoachDisciplines;
import com.example.demo.DAO.Discipline;

@Repository
public interface CoachDisciplinesRepository extends JpaRepository<CoachDisciplines, Integer> {

	public void deleteById(int id_coachDiscipline);

}
