package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Abonnement;
import com.example.demo.DAO.AbonnementsInforamtions;

@Repository
//@Transactional
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

	// Custom query method to retrieve specific information by joining 'Abonnement' and 'Discipline' entities
	@Query("select new com.example.demo.DAO.AbonnementsInforamtions( A.id_abonnement, A.tarif, A.duree,D.nomDiscipline,D.id_discipline) from Abonnement A Join A.discipline D")
	public List<AbonnementsInforamtions> getJoinInformation();

	public List<Abonnement> findByTarif(int tarif);

	public Abonnement findById(int id);

	@Query("select u from Abonnement u where u.id_abonnement=:id")
	public Abonnement getById(int id);

}
