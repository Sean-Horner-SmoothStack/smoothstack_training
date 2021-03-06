package com.utopia_air.classes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Flight_Table {

    private Integer id;
    private Integer route_id;
    private Integer airplane_id;
    private LocalDateTime departure_time;
    private Integer reserved_seats;
    private Float seat_price;

    public Flight_Table() {};

    public Flight_Table(Integer route_id, Integer airplane_id, LocalDateTime departure_time,
                        Integer reserved_seats, Float seat_price) {
        this.route_id = route_id;
        this.airplane_id = airplane_id;
        this.departure_time = departure_time;
        this.reserved_seats = reserved_seats;
        this.seat_price = seat_price;
    }

    public Flight_Table(Integer ID, Integer route_id, Integer airplane_id, LocalDateTime departure_time,
                        Integer reserved_seats, Float seat_price) {
        this.id = ID;
        this.route_id = route_id;
        this.airplane_id = airplane_id;
        this.departure_time = departure_time;
        this.reserved_seats = reserved_seats;
        this.seat_price = seat_price;
    }

    public Flight_Table(Flight flight) {
        this.id = flight.getFlight_id();
        this.route_id = flight.getRoute_id();
        this.airplane_id = flight.getAirplane_id();
        this.departure_time = flight.getDeparture_time();
        this.reserved_seats = flight.getReserved_seats();
        this.seat_price = flight.getSeat_price();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public Integer getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(Integer airplane_id) {
        this.airplane_id = airplane_id;
    }

    public LocalDateTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalDateTime departure_time) {
        this.departure_time = departure_time;
    }

    public void setDeparture_time(Timestamp departure_time) {
        this.departure_time = departure_time.toLocalDateTime();
    }

    public Integer getReserved_seats() {
        return reserved_seats;
    }

    public void setReserved_seats(Integer reserved_seats) {
        this.reserved_seats = reserved_seats;
    }

    public Float getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(Float seat_price) {
        this.seat_price = seat_price;
    }
}
