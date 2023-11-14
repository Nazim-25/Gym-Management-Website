package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.Seance;
import com.example.demo.Repository.SeanceRepository;

@Service
public class VisiteurService {

	@Autowired
	private SeanceRepository seanceRepository;
	
	// Method to retrieve a list of sessions
	public List<Seance> getSeances() {

		List<Seance> seancesList = seanceRepository.findAll();// Fetching all sessions from the repository
		return seancesList;// Returning the list of sessions

	}
}
