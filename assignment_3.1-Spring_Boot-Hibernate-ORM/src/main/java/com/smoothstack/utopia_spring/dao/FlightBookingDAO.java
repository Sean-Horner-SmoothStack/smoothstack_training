package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingDAO extends JpaRepository<FlightBooking, Integer> {

}
