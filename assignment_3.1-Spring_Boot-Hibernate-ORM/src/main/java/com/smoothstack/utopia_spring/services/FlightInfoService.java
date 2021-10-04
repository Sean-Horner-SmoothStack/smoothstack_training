//package com.smoothstack.utopia_spring.services;
//
//import com.smoothstack.utopia_spring.dao.FlightInfoDAO;
//import com.smoothstack.utopia_spring.model.FlightInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FlightInfoService {
//
//    @Autowired
//    FlightInfoDAO flightInfoDao;
//
//    public FlightInfo getById(int flight_id) {
//        try {
//            return flightInfoDao.findById(flight_id).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public List<FlightInfo> getAllFlightInfo() {
//        return flightInfoDao.findAll();
//    }
//
//    public boolean insertFlightInfo(FlightInfo flight_info) {
//        try {
//            flightInfoDao.save(flight_info);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean deleteFlightInfo(FlightInfo flight_info) {
//      try {
//            flightInfoDao.delete(flight_info);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean deleteFlightInfo(int flight_id) {
//        try {
//            flightInfoDao.deleteById(flight_id);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
