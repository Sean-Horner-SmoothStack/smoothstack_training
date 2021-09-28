package com.utopia_air.classes;

import com.utopia_air.dao.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Flight {

    private Integer flight_id;
    private Integer route_id;
    private String origin_id;
    private String destination_id;
    private Integer airplane_id;
    private LocalDateTime departure_time;
    private LocalDateTime arrival_time;
    private Integer airplane_type_id;
    private Integer max_capacity;
    private Integer reserved_seats;
    private List<Integer> seats = null;
    private float seat_price;

    public Flight() {};

    public Flight(Integer flight_id, Integer route_id, Integer airplane_id) {

        Flight_Table flight = Flight_TableDAO.getFlight_TableById(flight_id);
        Route route = RouteDAO.getRoute(route_id);
        Airplane airplane = AirplaneDAO.getAirplane(airplane_id);

        this.flight_id = flight.getId();
        this.departure_time = flight.getDeparture_time();
        this.arrival_time = this.departure_time.plusHours(3);
        this.reserved_seats = flight.getReserved_seats();
        this.seat_price = flight.getSeat_price();

        this.route_id = route.getId();
        this.origin_id = route.getOrigin_id();
        this.destination_id = route.getDestination_id();

        this.airplane_id = airplane.getId();
        this.airplane_type_id = airplane.getType_id();
        this.max_capacity = AirplaneTypeDAO.getAirplaneCapacity(this.airplane_type_id);

        Integer business = (int)((this.max_capacity - 20) * 0.3);
        Integer economy = (int)((this.max_capacity - 20) * 0.7);
        Integer first = this.max_capacity - business - economy;

        this.seats.add(first);
        this.seats.add(business - this.reserved_seats);
        this.seats.add(economy);
    }

    public Flight(Flight_Table flight, Route route, Airplane airplane) {

        this.flight_id = flight.getId();
        this.departure_time = flight.getDeparture_time();
        this.arrival_time = this.departure_time.plusHours(3);
        this.reserved_seats = flight.getReserved_seats();
        this.seat_price = flight.getSeat_price();

        this.route_id = route.getId();
        this.origin_id = route.getOrigin_id();
        this.destination_id = route.getDestination_id();

        this.airplane_id = airplane.getId();
        this.airplane_type_id = airplane.getType_id();
        this.max_capacity = AirplaneTypeDAO.getAirplaneCapacity(this.airplane_type_id);

        Integer business = (int)((this.max_capacity - 20) * 0.3);
        Integer economy = (int)((this.max_capacity - 20) * 0.7);
        Integer first = this.max_capacity - business - economy;

        this.seats.add(first);
        this.seats.add(business - this.reserved_seats);
        this.seats.add(economy);
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
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

    public LocalDateTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalDateTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Integer getAirplane_type_id() {
        return airplane_type_id;
    }

    public void setAirplane_type_id(Integer airplane_type_id) {
        this.airplane_type_id = airplane_type_id;
    }

    public Integer getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(Integer max_capacity) {
        this.max_capacity = max_capacity;
    }

    public Integer getReserved_seats() {
        return reserved_seats;
    }

    public void setReserved_seats(Integer reserved_seats) {
        this.reserved_seats = reserved_seats;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }

    public void setSeats(Integer first, Integer business, Integer economy) {
        this.seats = List.of(first, business, economy);
    }

    public float getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(float seat_price) {
        this.seat_price = seat_price;
    }

    public String toString() {
        DateTimeFormatter day_time = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return String.format("ID: %d  %s --> %s  Leaving: %s",
                this.flight_id, this.origin_id, this.destination_id, this.departure_time.format(day_time));
    }

    public Flight_Table getFlightTable() {
        return new Flight_Table(this.flight_id, this.route_id, this.airplane_id,
                this.departure_time, this.reserved_seats, this.seat_price);
    }

    public Route getRoute() {
        return new Route(this.route_id, this.origin_id, this.destination_id);
    }

    public Airplane getAirplane() {
        return new Airplane(this.airplane_id, this.airplane_type_id);
    }
}
