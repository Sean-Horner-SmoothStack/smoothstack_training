//package com.smoothstack.utopia_spring.controller;
//
//import com.smoothstack.utopia_spring.model.FlightInfo;
//import com.smoothstack.utopia_spring.services.FlightInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/utopia/flight_info")
//public class FlightInfoController {
//
//    @Autowired
//    private FlightInfoService flightInfoService;
//
//    @GetMapping(path = "/{flight_id}")
//    public FlightInfo getById(@PathVariable int flight_id) {
//        return flightInfoService.getById(flight_id);
//    }
//
//    @GetMapping(path = "/all")
//    public List<FlightInfo> getAllTypes() {
//        return flightInfoService.getAllFlightInfo();
//    }
//
//    @PostMapping(path = "/upload")
//    public boolean insertType(@RequestBody FlightInfo flight_info) {
//        return flightInfoService.insertFlightInfo(flight_info);
//    }
//
//    @PostMapping(path = "/delete")
//    public boolean delete(@RequestBody FlightInfo flight_info) {
//        return flightInfoService.deleteFlightInfo(flight_info);
//    }
//
//    @PostMapping(path = "/delete/{flight_id}")
//    public boolean delete(@PathVariable int flight_id) {
//        return flightInfoService.deleteFlightInfo(flight_id);
//    }
//}
