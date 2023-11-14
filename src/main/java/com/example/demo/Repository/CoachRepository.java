package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Coach;

@Repository
//@Transactional
public interface CoachRepository extends JpaRepository<Coach, Integer> {

	public Coach findById(int id_coach);

	public void deleteById(int id_coach);

	public Coach findByEmail(String email);

	@Query("select u from Coach u where u.email=:email and u.password=:password")
	public Coach getCoach(@Param("email") String email, @Param("password") String password);

	public Coach findByEmailContains(String email);

	@Query("select u from Coach u where u.firstname like %:keyword% or u.lastname like %:keyword%")
	public List<Coach> findByKeyword(@Param("keyword") String keyword);
}
