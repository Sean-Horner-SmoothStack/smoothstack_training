package com.utopia_air.dao;

import com.utopia_air.classes.Flight;
import com.utopia_air.classes.Flight_Table;
import com.utopia_air.classes.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public static boolean flightExists(Integer flight_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, flight_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightExists(Flight flight) {
        return flightExists(flight.getFlight_id());
    }

    public static boolean flightExists(Flight_Table flight) {
        return flightExists(flight.getId());
    }

    public static Flight getFlightById(Integer flight_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, flight_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                Integer id = rs.getInt("id");
                Integer route_id = rs.getInt("route_id");
                Integer airplane_id = rs.getInt("airplane_id");
                return new Flight(id, route_id, airplane_id);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static Flight getFlightFromFlight_Table(Flight_Table flight) {
        return getFlightById(flight.getId());
    }

    public static List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM flight AS f
                    INNER JOIN
                    	(SELECT a.id AS a_id, a_t.max_capacity, a_t.id AS airplane_type_id
                         FROM airplane AS a
                         INNER JOIN airplane_type AS a_t
                    		ON a.type_id = a_t.id
                    	) AS airplane_capacity
                    		ON f.airplane_id = airplane_capacity.a_id
                    INNER JOIN
                    	(SELECT r.id AS r_id, r.origin_id, r.destination_id
                    	 FROM route as r) AS route_info
                    		ON f.route_id = route_info.r_id""");

            while(rs.next()) {
                Flight flight = new Flight();
                flight.setFlight_id( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id" ) );
                flight.setDeparture_time( rs.getTimestamp("departure_time").toLocalDateTime() );
                flight.setArrival_time( flight.getDeparture_time().plusHours(3) );
                flight.setReserved_seats( rs.getInt("reserved_seats") );
                flight.setSeat_price( rs.getFloat("seat_price") );
                flight.setMax_capacity( rs.getInt("max_capacity") );
                flight.setAirplane_type_id( rs.getInt("airplane_type_id") );
                flight.setOrigin_id( rs.getString("origin_id") );
                flight.setDestination_id( rs.getString("destination_id") );

                flights.add(flight);
            }
            return flights;

        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean insertFlight(Flight flight) {
        try{
            if(!AirportDAO.airportExists(flight.getOrigin_id()))
                System.out.printf("No airport in the table matches %s%nThis is a programming error.%n",
                            flight.getOrigin_id());

            if(!RouteDAO.routeExists(flight.getRoute_id()))
                RouteDAO.routeInsert(
                        new Route( flight.getRoute_id(), flight.getOrigin_id(), flight.getDestination_id() ));

            Flight_TableDAO.flight_TableInsert(new Flight_Table(flight));
            return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;

    }

    public static boolean updateFlight(Flight flight) {
        return false;
    }

    public static boolean deleteFlight(Flight flight) {
        return false;
    }
}
