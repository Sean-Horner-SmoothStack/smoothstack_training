package com.smoothstack.utopia_spring.controller;

import com.smoothstack.utopia_spring.model.Passenger;
import com.smoothstack.utopia_spring.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/utopia/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping(path = "/{passenger_id}")
    public Passenger getById(@PathVariable int passenger_id) {
        return passengerService.getById(passenger_id);
    }

    @GetMapping(path = "/all")
    public List<Passenger> getAllTypes() {
        return passengerService.getAllPassengers();
    }

    @PostMapping(path = "/upload")
    public boolean insertType(@RequestBody Passenger passenger) {
        return passengerService.insertPassenger(passenger);
    }

    @PostMapping(path = "/delete")
    public boolean delete(@RequestBody Passenger passenger) {
        return passengerService.deletePassenger(passenger);
    }

    @PostMapping(path = "/delete/{passenger_id}")
    public boolean delete(@PathVariable int passenger_id) {
        return passengerService.deletePassenger(passenger_id);
    }
}
