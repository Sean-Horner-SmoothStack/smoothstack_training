package com.utopia_air.classes;

public class FlightBooking {

    private Integer flight_id;
    private Integer booking_id;

    public FlightBooking() {};

    public FlightBooking(Integer flight_id, Integer booking_id) {
        this.flight_id = flight_id;
        this.booking_id = booking_id;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }
}
