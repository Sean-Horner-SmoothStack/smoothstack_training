//package com.smoothstack.utopia_spring.services;
//
//import com.smoothstack.utopia_spring.dao.TicketDAO;
//import com.smoothstack.utopia_spring.model.Ticket;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TicketService {
//
//    @Autowired
//    private TicketDAO ticketDao;
//
//    public Ticket getByBookingId(int booking_id) {
//        try {
//            return ticketDao.findById(booking_id).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public List<Ticket> getByPassengerId(int passenger_id) {
//        return ticketDao.findByPassengerId(passenger_id);
//    }
//
//    public List<Ticket> getAllTickets() {
//        return ticketDao.findAll();
//    }
//
//    public boolean insertTicket(Ticket ticket) {
//        try {
//            ticketDao.save(ticket);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean deleteTicket(Ticket ticket) {
//        try {
//            ticketDao.delete(ticket);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean deleteTicketByBooking(int booking_id) {
//        try {
//            ticketDao.deleteById(booking_id);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public boolean deleteTicketByPassenger(int passenger_id) {
//        try {
//            ticketDao.deleteByPassengerId(passenger_id);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
