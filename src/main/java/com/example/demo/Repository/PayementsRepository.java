package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Payements;

@Repository
//@Transactional
public interface PayementsRepository extends JpaRepository<Payements, Integer> {

}
