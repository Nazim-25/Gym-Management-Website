package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Abonne;
import com.example.demo.DAO.Gerant;

@Repository
//@Transactional
public interface GerantRepository extends JpaRepository<Gerant, Integer> {

	public Gerant findById(int id);

	@Query("select u from Gerant u where u.email=:email and u.password=:password")
	public Gerant getGerant(@Param("email") String email, @Param("password") String password);

	public Gerant findByEmailContains(String email);
}
