package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDAO extends JpaRepository<Airport, String> {

}
