package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.Notification;

@Repository
//@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
