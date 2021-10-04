package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDAO extends JpaRepository<Route, Integer> {

}
