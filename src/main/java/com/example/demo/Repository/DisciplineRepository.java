package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Discipline;

@Repository
//@Transactional
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {

	public Discipline findById(int id_discipline);

}
