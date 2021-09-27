package com.utopia_air.classes;

import com.utopia_air.dao.BookingDAO;
import com.utopia_air.dao.PassengerDAO;

import java.sql.Date;

public class Ticket {

    private Integer passenger_id;
    private Integer booking_id;
    private Boolean booking_is_active;
    private String booking_confirmation_code;
    private String given_name;
    private String family_name;
    private Date dob;
    private String gender;
    private String address;

    public Ticket() {};

    public Ticket(Booking booking, Passenger passenger) {
        this.passenger_id = passenger.getId();
        this.booking_id = booking.getId();
        this.booking_is_active = booking.getIs_active();
        this.booking_confirmation_code = booking.getConfirmation_code();
        this.given_name = passenger.getGiven_name();
        this.family_name = passenger.getFamily_name();
        this.dob = passenger.getDob();
        this.gender = passenger.getGender();
        this.address = passenger.getAddress();
    }

    public Ticket(Integer booking_id, Integer passenger_id) {
        Booking booking = BookingDAO.getBooking(booking_id);
        Passenger passenger = PassengerDAO.getPassenger(passenger_id);
        new Ticket(booking, passenger);
    }

    public Integer getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Integer passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public Boolean getBooking_is_active() {
        return booking_is_active;
    }

    public void setBooking_is_active(Boolean booking_is_active) {
        this.booking_is_active = booking_is_active;
    }

    public String getBooking_confirmation_code() {
        return booking_confirmation_code;
    }

    public void setBooking_confirmation_code(String booking_confirmation_code) {
        this.booking_confirmation_code = booking_confirmation_code;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
