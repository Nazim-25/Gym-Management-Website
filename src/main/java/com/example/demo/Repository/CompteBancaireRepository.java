package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.DAO.CompteBancaire;

//@Transactional
@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, String> {

}
