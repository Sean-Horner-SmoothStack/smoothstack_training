package com.utopia_air.classes;

public class BookingUser {

    private Integer booking_id;
    private Integer user_id;

    public BookingUser() {};

    public BookingUser(Integer booking_id, Integer user_id) {
        this.booking_id = booking_id;
        this.user_id = user_id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String toString() {
        return String.format("Booking ID: %d  User ID: %d", this.booking_id, this.user_id);
    }
}
