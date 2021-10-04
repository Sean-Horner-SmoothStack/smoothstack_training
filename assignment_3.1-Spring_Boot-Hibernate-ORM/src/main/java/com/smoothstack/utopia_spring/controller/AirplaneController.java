package com.smoothstack.utopia_spring.controller;

import com.smoothstack.utopia_spring.model.Airplane;
import com.smoothstack.utopia_spring.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utopia/airplane")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping(path = "/{airplane_id}")
    public Airplane getAirplaneById(@PathVariable int airplane_id) {
        return airplaneService.getById(airplane_id);
    }

    @GetMapping(path = "/all")
    public List<Airplane> getAllPlanes() {
        return airplaneService.getAll();
    }

    @PostMapping(path = "/upload")
    public boolean upload(@RequestBody Airplane airplane) {
        return airplaneService.upload(airplane);
    }

    @PostMapping(path = "/delete")
    public boolean delete(@RequestBody Airplane airplane) {
        return airplaneService.delete(airplane);
    }
}
