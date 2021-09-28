package com.utopia_air.dao;

import com.utopia_air.classes.Flight;
import com.utopia_air.classes.Flight_Table;
import com.utopia_air.classes.Route;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Flight_TableDAO {

    public static boolean existsInFlightTable(Integer flight_id) {
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

    public static boolean existsInFlightTable(Flight_Table flight) {
        return existsInFlightTable(flight.getId());
    }

    public static boolean existsInFlightTable(Flight flight) {
        return existsInFlightTable(flight.getFlight_id());
    }

    public static Flight_Table getFlight_TableById(Integer flight_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, flight_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                return flight;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static Flight_Table getFlight_TableById(Flight_Table flight) {
        return getFlight_TableById(flight.getId());
    }

    public static Flight_Table getFlight_TableById(Flight flight) {
        return getFlight_TableById(flight.getFlight_id());
    }

    public static List<Flight_Table> getAllFlight_Table() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM flight""");

            List<Flight_Table> flightsOnTable = new ArrayList<>();
            while(rs.next()) {
                Flight_Table flight = new Flight_Table();
                flight.setId( rs.getInt("id") );
                flight.setRoute_id( rs.getInt("route_id") );
                flight.setAirplane_id( rs.getInt("airplane_id") );
                flight.setDeparture_time( rs.getTimestamp("departure_time") );
                flight.setReserved_seats( rs.getInt( "reserved_seats") );
                flight.setSeat_price( rs.getFloat( "seat_price") );

                flightsOnTable.add(flight);
            }
            return flightsOnTable;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Flight_Table> getFlight_TableOnRoute(Integer route_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE route_id = ?
                    """);
            preparedStatement.setInt(1, route_id);
            ResultSet rs = preparedStatement.executeQuery();

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

    public static List<Flight_Table> getFlight_TableOnRoute(Route route) {
        return getFlight_TableOnRoute(route.getId());
    }

    public static List<Flight_Table> getFlight_TableOnPlane(Integer airplane_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE airplane_id = ?
                    """);
            preparedStatement.setInt(1, airplane_id);
            ResultSet rs = preparedStatement.executeQuery();

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

    public static List<Flight_Table> getFlight_TableForTimeSpan(Date date1, Date date2) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE departure_time>= ?
                        AND departure_time <= ?
                    """);
            preparedStatement.setDate(1, date1);
            preparedStatement.setDate(2, date2);
            ResultSet rs = preparedStatement.executeQuery();

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

    public static List<Flight_Table> getFlight_TableForTimeSpan(Timestamp ts1,Timestamp ts2) {
        Date date1 = new Date(ts1.getTime());
        Date date2 = new Date(ts2.getTime());
        return getFlight_TableForTimeSpan(date1, date2);
    }

    public static List<Flight_Table> getFlight_TableForPriceSpan(Float low_price, Float high_price) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight
                    WHERE seat_price >= ?
                        AND seat_price <= ?
                    """);
            preparedStatement.setFloat(1, low_price);
            preparedStatement.setFloat(2, high_price);
            ResultSet rs = preparedStatement.executeQuery();

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
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO flight
                    VALUES(?, ?, ?, ?, ?, ?)
                    """);
            preparedStatement.setInt(1, flight.getId());
            preparedStatement.setInt(2, flight.getRoute_id());
            preparedStatement.setInt(3, flight.getAirplane_id());
            preparedStatement.setDate(4, Date.valueOf(flight.getDeparture_time().toLocalDate()));
            preparedStatement.setInt(5, flight.getReserved_seats());
            preparedStatement.setFloat(6, flight.getSeat_price());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flight_TableInsert(Flight flight) {
        return flight_TableInsert(new Flight_Table(flight));
    }

    public static boolean flight_TableUpdate(Flight_Table flight) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE flight
                    SET  route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ?
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, flight.getRoute_id());
            preparedStatement.setInt(2, flight.getAirplane_id());
            preparedStatement.setDate(3, Date.valueOf(flight.getDeparture_time().toLocalDate()));
            preparedStatement.setInt(4, flight.getReserved_seats());
            preparedStatement.setFloat(5, flight.getSeat_price());
            preparedStatement.setInt(6, flight.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flight_TableUpdate(Flight flight) {
        return flight_TableUpdate(new Flight_Table(flight));
    }

    public static boolean flight_TableDelete(Integer flight_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM flight
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, flight_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flight_TableDelete(Flight_Table flight) {
        return flight_TableDelete(flight.getId());
    }

    public static boolean flight_TableDelete(Flight flight) {
        return flight_TableDelete(flight.getFlight_id());
    }
}
