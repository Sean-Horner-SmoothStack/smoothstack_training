package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneDAO extends JpaRepository<Airplane, Integer> {

}
