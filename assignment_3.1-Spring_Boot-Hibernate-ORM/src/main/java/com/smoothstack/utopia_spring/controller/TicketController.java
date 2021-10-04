//package com.smoothstack.utopia_spring.controller;
//
//import com.smoothstack.utopia_spring.model.Ticket;
//import com.smoothstack.utopia_spring.services.TicketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/utopia/ticket")
//public class TicketController {
//
//    @Autowired
//    private TicketService ticketService;
//
//    @GetMapping(path = "/booking={booking_id}")
//    public Ticket getByBookingId(@PathVariable int booking_id) {
//        return ticketService.getByBookingId(booking_id);
//    }
//
//    @GetMapping(path = "/passenger={passenger_id}")
//    public List<Ticket> getByPassengerId(@PathVariable int passenger_id) {
//        return ticketService.getByPassengerId(passenger_id);
//    }
//
//    @GetMapping(path = "/all")
//    public List<Ticket> getAllTypes() {
//        return ticketService.getAllTickets();
//    }
//
//    @PostMapping(path = "/upload")
//    public boolean insertType(@RequestBody Ticket ticket) {
//        return ticketService.insertTicket(ticket);
//    }
//
//    @PostMapping(path = "/delete")
//    public boolean delete(@RequestBody Ticket ticket) {
//        return ticketService.deleteTicket(ticket);
//    }
//
//    @PostMapping(path = "/delete/booking={booking_id}")
//    public boolean deleteByBooking(@PathVariable int booking_id) {
//        return ticketService.deleteTicketByBooking(booking_id);
//    }
//
//    @PostMapping(path = "/delete/passenger={passenger_id}")
//    public boolean deleteByPassenger(@PathVariable int passenger_id) {
//        return ticketService.deleteTicketByPassenger(passenger_id);
//    }
//}
