package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDAO extends JpaRepository<Flight, Integer> {

}
