package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.AirplaneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneTypeDAO extends JpaRepository<AirplaneType, Integer> {

}