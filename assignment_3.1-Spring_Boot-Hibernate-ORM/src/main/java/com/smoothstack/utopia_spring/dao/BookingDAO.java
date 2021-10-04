package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer> {

}