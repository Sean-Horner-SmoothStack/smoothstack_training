package com.utopia_air.dao;

import com.utopia_air.classes.Flight_Table;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Flight_TableDAO {

    public static boolean existsInFlightTable(Integer flight_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( String.format("""
                    SELECT *
                    FROM flight
                    WHERE id=%d""",
                    flight_id) );
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Flight_Table getFlight_TableById(Integer flight_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight
                    WHERE id=%d""",
                    flight_id) );
            if(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Flight_Table> getAllFlight_Table() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM flight""");

            List<Flight_Table> flightsOnRoute = new ArrayList<>();

            while(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                flightsOnRoute.add(flight);
            }

            return flightsOnRoute;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Flight_Table> getFlight_TableOnRoute(Integer route_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight
                    WHERE route_id=%d""",
                    route_id) );

            List<Flight_Table> flightsOnRoute = new ArrayList<>();

            while(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                flightsOnRoute.add(flight);
            }

            return flightsOnRoute;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Flight_Table> getFlight_TableInPlane(Integer airplane_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight
                    WHERE airplane_id=%d""",
                    airplane_id) );

            List<Flight_Table> flightsInPlane = new ArrayList<>();

            while(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                flightsInPlane.add(flight);
            }

            return flightsInPlane;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Flight_Table> getFlight_TableForTimeSpan(Timestamp ts1,Timestamp ts2) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight
                    WHERE departure_time>=%s && departure_time <= %s""",
                    ts1, ts2) );

            List<Flight_Table> flightsInTimeSpan = new ArrayList<>();

            while(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                flightsInTimeSpan.add(flight);
            }

            return flightsInTimeSpan;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Flight_Table> getFlight_TableForPriceSpan(Float low_price, Float high_price) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight
                    WHERE seat_price>=%f && seat_price<=%f""",
                    low_price, high_price) );

            List<Flight_Table> flightsInPriceSpan = new ArrayList<>();

            while(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                flightsInPriceSpan.add(flight);
            }

            return flightsInPriceSpan;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean flight_TableInsert(Flight_Table flight) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO flight
                    VALUES(%d, %d, %d, %s, %d, %f)""",
                    flight.getId(),
                    flight.getRoute_id(),
                    flight.getAirplane_id(),
                    flight.getDeparture_time(),
                    flight.getReserved_seats(),
                    flight.getSeat_price()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flight_TableUpdate(Flight_Table flight) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE flight
                    SET  route_id=%d, airplane_id=%d, departure_time=%s, reserved_seats=%d, seat_price=%f
                    WHERE id=%d""",
                    flight.getRoute_id(),
                    flight.getAirplane_id(),
                    flight.getDeparture_time(),
                    flight.getReserved_seats(),
                    flight.getSeat_price(),
                    flight.getId()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
