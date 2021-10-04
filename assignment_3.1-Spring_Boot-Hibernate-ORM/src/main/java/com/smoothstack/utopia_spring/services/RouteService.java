package com.smoothstack.utopia_spring.services;

import com.smoothstack.utopia_spring.dao.RouteDAO;
import com.smoothstack.utopia_spring.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    RouteDAO routeDao;

    public Route getById(int route_id) {
        try {
            return routeDao.findById(route_id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Route();
    }

    public List<Route> getAllRoutes() {
        return routeDao.findAll();
    }

    public boolean insertRoute(Route route) {
        try {
            routeDao.save(route);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoute(Route route) {
        try {
            routeDao.delete(route);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoute(int route_id) {
        try {
            routeDao.deleteById(route_id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
