package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.BookingGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingGuestDAO extends JpaRepository<BookingGuest, Integer> {

}
