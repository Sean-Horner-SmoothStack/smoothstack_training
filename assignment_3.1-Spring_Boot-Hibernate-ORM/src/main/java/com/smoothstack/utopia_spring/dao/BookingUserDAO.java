package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.BookingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingUserDAO extends JpaRepository<BookingUser, Integer> {

}