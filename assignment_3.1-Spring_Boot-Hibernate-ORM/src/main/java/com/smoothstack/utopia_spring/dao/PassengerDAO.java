package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDAO extends JpaRepository<Passenger, Integer> {

}