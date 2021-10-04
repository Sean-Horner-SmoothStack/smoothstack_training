//package com.smoothstack.utopia_spring.model;
//
//import com.smoothstack.utopia_spring.dao.AirplaneDAO;
//import com.smoothstack.utopia_spring.dao.AirplaneTypeDAO;
//import com.smoothstack.utopia_spring.dao.FlightDAO;
//import com.smoothstack.utopia_spring.dao.RouteDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Entity
//public class FlightInfo {
//
//    @Autowired
//    private AirplaneDAO airplaneDao;
//
//    @Autowired
//    private RouteDAO routeDao;
//
//    @Autowired
//    private FlightDAO flightDao;
//
//    @Autowired
//    private AirplaneTypeDAO airplaneTypeDao;
//
//    @Id
//    private Integer flight_id;
//    private Integer route_id;
//    private String origin_id;
//    private String destination_id;
//    private Integer airplane_id;
//    private LocalDateTime departure_time;
//    private LocalDateTime arrival_time;
//    private Integer airplane_type_id;
//    private Integer max_capacity;
//    private Integer reserved_seats;
//    private List<Integer> seats = null;
//    private float seat_price;
//
//    public FlightInfo() {};
//
//    public FlightInfo(Integer flight_id, Integer route_id, Integer airplane_id) {
//
//        Flight flight = flightDao.getById(flight_id);
//        Route route = routeDao.getById(route_id);
//        Airplane airplane = airplaneDao.getById(airplane_id);
//
//        this.flight_id = flight.getId();
//        this.departure_time = flight.getDeparture_time();
//        this.arrival_time = this.departure_time.plusHours(3);
//        this.reserved_seats = flight.getReserved_seats();
//        this.seat_price = flight.getSeat_price();
//
//        this.route_id = route.getId();
//        this.origin_id = route.getOrigin_id();
//        this.destination_id = route.getDestination_id();
//
//        this.airplane_id = airplane.getId();
//        this.airplane_type_id = airplane.getType_id();
//        this.max_capacity = airplaneTypeDao.getById(this.airplane_type_id).getMax_capacity();
//
//        Integer business = (int)((this.max_capacity - 20) * 0.3);
//        Integer economy = (int)((this.max_capacity - 20) * 0.7);
//        Integer first = this.max_capacity - business - economy;
//
//        this.seats.add(first);
//        this.seats.add(business - this.reserved_seats);
//        this.seats.add(economy);
//    }
//
//    public FlightInfo(Flight flight, Route route, Airplane airplane) {
//
//        this.flight_id = flight.getId();
//        this.departure_time = flight.getDeparture_time();
//        this.arrival_time = this.departure_time.plusHours(3);
//        this.reserved_seats = flight.getReserved_seats();
//        this.seat_price = flight.getSeat_price();
//
//        this.route_id = route.getId();
//        this.origin_id = route.getOrigin_id();
//        this.destination_id = route.getDestination_id();
//
//        this.airplane_id = airplane.getId();
//        this.airplane_type_id = airplane.getType_id();
//        this.max_capacity = airplaneTypeDao.getById(this.airplane_type_id).getMax_capacity();
//
//        Integer business = (int)((this.max_capacity - 20) * 0.3);
//        Integer economy = (int)((this.max_capacity - 20) * 0.7);
//        Integer first = this.max_capacity - business - economy;
//
//        this.seats.add(first);
//        this.seats.add(business - this.reserved_seats);
//        this.seats.add(economy);
//    }
//
//    public String toString() {
//        DateTimeFormatter day_time = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
//
//        return String.format("ID: %d  %s --> %s  Leaving: %s",
//                this.flight_id, this.origin_id, this.destination_id, this.departure_time.format(day_time));
//    }
//
//    public Flight getFlightTable() {
//        return new Flight(this.flight_id, this.route_id, this.airplane_id,
//                this.departure_time, this.reserved_seats, this.seat_price);
//    }
//
//    public Route getRoute() {
//        return new Route(this.route_id, this.origin_id, this.destination_id);
//    }
//
//    public Airplane getAirplane() {
//        return new Airplane(this.airplane_id, this.airplane_type_id);
//    }
//
//    public Integer flight_id() {
//        return flight_id;
//    }
//
//    public void flight_id(Integer flight_id) {
//        this.flight_id = flight_id;
//    }
//
//    public Integer route_id() {
//        return route_id;
//    }
//
//    public void route_id(Integer route_id) {
//        this.route_id = route_id;
//    }
//
//    public String origin_id() {
//        return origin_id;
//    }
//
//    public void origin_id(String origin_id) {
//        this.origin_id = origin_id;
//    }
//
//    public String destination_id() {
//        return destination_id;
//    }
//
//    public void destination_id(String destination_id) {
//        this.destination_id = destination_id;
//    }
//
//    public Integer airplane_id() {
//        return airplane_id;
//    }
//
//    public void airplane_id(Integer airplane_id) {
//        this.airplane_id = airplane_id;
//    }
//
//    public LocalDateTime departure_time() {
//        return departure_time;
//    }
//
//    public void departure_time(LocalDateTime departure_time) {
//        this.departure_time = departure_time;
//    }
//
//    public LocalDateTime arrival_time() {
//        return arrival_time;
//    }
//
//    public void arrival_time(LocalDateTime arrival_time) {
//        this.arrival_time = arrival_time;
//    }
//
//    public Integer airplane_type_id() {
//        return airplane_type_id;
//    }
//
//    public void airplane_type_id(Integer airplane_type_id) {
//        this.airplane_type_id = airplane_type_id;
//    }
//
//    public Integer max_capacity() {
//        return max_capacity;
//    }
//
//    public void max_capacity(Integer max_capacity) {
//        this.max_capacity = max_capacity;
//    }
//
//    public Integer reserved_seats() {
//        return reserved_seats;
//    }
//
//    public void reserved_seats(Integer reserved_seats) {
//        this.reserved_seats = reserved_seats;
//    }
//
//    public List<Integer> seats() {
//        return seats;
//    }
//
//    public void seats(List<Integer> seats) {
//        this.seats = seats;
//    }
//
//    public float seat_price() {
//        return seat_price;
//    }
//
//    public void seat_price(float seat_price) {
//        this.seat_price = seat_price;
//    }
//}
