//package com.smoothstack.utopia_spring.model;
//
//import com.smoothstack.utopia_spring.dao.BookingDAO;
//import com.smoothstack.utopia_spring.dao.PassengerDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.sql.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQuery;
//
//@Entity
//@NamedQuery(name = "Ticket.findByPassengerId",
//            query = "SELECT t FROM Ticket t WHERE t.passenger_id = ?1")
//@NamedQuery(name = "Ticket.deleteByPassengerId",
//            query = "DELETE FROM Ticket t WHERE t.passenger_id = ?1")
//public class Ticket {
//
////    @Column(name = "passenger_id")
//    private Integer passenger_id;
//
//    @Id
////    @Column(name = "booking_id")
//    private Integer booking_id;
//
////    @Column(name = "booking_is_active")
//    private Boolean booking_is_active;
//
////    @Column(name = "booking_confirmation_code")
//    private String booking_confirmation_code;
//
////    @Column(name = "passenger_id")
//    private String given_name;
//
////    @Column(name = "passenger_id")
//    private String family_name;
//
////    @Column(name = "passenger_id")
//    private Date dob;
//
////    @Column(name = "passenger_id")
//    private String gender;
//
////    @Column(name = "passenger_id")
//    private String address;
//
//
//    @Autowired
//    private BookingDAO bookingDao;
//
//    @Autowired
//    private PassengerDAO passengerDao;
//
//
//
//    public Ticket() {};
//
//    public Ticket(Booking booking, Passenger passenger) {
//        this.passenger_id = passenger.getId();
//        this.booking_id = booking.getId();
//        this.booking_is_active = booking.getIs_active();
//        this.booking_confirmation_code = booking.getConfirmation_code();
//        this.given_name = passenger.getGiven_name();
//        this.family_name = passenger.getFamily_name();
//        this.dob = passenger.getDob();
//        this.gender = passenger.getGender();
//        this.address = passenger.getAddress();
//    }
//
//    public Ticket(Integer booking_id, Integer passenger_id) {
//        Booking booking = bookingDao.getById(booking_id);
//        Passenger passenger = passengerDao.getById(passenger_id);
//        new Ticket(booking, passenger);
//    }
//
//    public Integer getPassenger_id() {
//        return passenger_id;
//    }
//
//    public void setPassenger_id(Integer passenger_id) {
//        this.passenger_id = passenger_id;
//    }
//
//    public Integer getBooking_id() {
//        return booking_id;
//    }
//
//    public void setBooking_id(Integer booking_id) {
//        this.booking_id = booking_id;
//    }
//
//    public Boolean getBooking_is_active() {
//        return booking_is_active;
//    }
//
//    public void setBooking_is_active(Boolean booking_is_active) {
//        this.booking_is_active = booking_is_active;
//    }
//
//    public String getBooking_confirmation_code() {
//        return booking_confirmation_code;
//    }
//
//    public void setBooking_confirmation_code(String booking_confirmation_code) {
//        this.booking_confirmation_code = booking_confirmation_code;
//    }
//
//    public String getGiven_name() {
//        return given_name;
//    }
//
//    public void setGiven_name(String given_name) {
//        this.given_name = given_name;
//    }
//
//    public String getFamily_name() {
//        return family_name;
//    }
//
//    public void setFamily_name(String family_name) {
//        this.family_name = family_name;
//    }
//
//    public Date getDob() {
//        return dob;
//    }
//
//    public void setDob(Date dob) {
//        this.dob = dob;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//}
