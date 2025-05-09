package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.AbonnementDisciplines;
import com.example.demo.DAO.Discipline;

@Repository
@Transactional
public interface AbonnementDisciplinesRepository extends JpaRepository<AbonnementDisciplines, Integer> {

	public AbonnementDisciplines findById(int id);

	@Modifying
	@Query(value = "delete from abonnement_disciplines ab where ab.abonnement_id_abonnement=:a AND ab.discipline_id_discipline=:d", nativeQuery = true)
	public void deleteDiscipline(int a, int d);
}
