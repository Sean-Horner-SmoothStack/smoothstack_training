package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.BookingAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingAgentDAO extends JpaRepository<BookingAgent, Integer> {

}
