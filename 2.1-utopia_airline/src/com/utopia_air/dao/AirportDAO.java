package com.utopia_air.dao;

import com.utopia_air.classes.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO {

    public static boolean airportExists(String iata_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airport
                    WHERE iata_id = ?
                    """);
            preparedStatement.setString(1, iata_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Airport getAirport(String iata_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airport
                    WHERE iata_id = ?
                    """);
            preparedStatement.setString(1, iata_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                Airport airport = new Airport();
                airport.setIata_id( rs.getString("iata_id") );
                airport.setCity( rs.getString("city") );

                return airport;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static String getAirportLocation(String iata_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airport
                    WHERE iata_id = ?
                    """);
            preparedStatement.setString(1, iata_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
                return rs.getString("city");

        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static String getAirportId(String city) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airport
                    WHERE city = ?
                    """);
            preparedStatement.setString(1, city);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
                return rs.getString("iata_id");

        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Airport> getAllAirports() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM airport
                    ORDER BY iata_id""");

            List<Airport> airports = new ArrayList<>();
            while(rs.next()) {
                Airport airport = new Airport();
                airport.setIata_id( rs.getString("iata_id") );
                airport.setCity( rs.getString("city") );

                airports.add(airport);
            }
            return airports;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean airportInsert(Airport airport) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO airplane
                    VALUES(?, ?)
                    """);
            preparedStatement.setString(1, airport.getIata_id());
            preparedStatement.setString(2, airport.getCity());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airportUpdate(Airport airport) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE airplane
                    SET city = ?
                    WHERE iata_id = ?
                    """);
            preparedStatement.setString(1, airport.getCity());
            preparedStatement.setString(2, airport.getIata_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airportDelete(Airport airport) {
        if(RouteDAO.routeExists( airport.getIata_id() ))
            RouteDAO.routeDeleteByIata( airport.getIata_id() );

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM airport
                    WHERE iata_id = ?
                    """);
            preparedStatement.setString(1, airport.getIata_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airportDelete(String airport_id) {
        boolean proceed = true;
        if(RouteDAO.routeExists( airport_id ))
            proceed = RouteDAO.routeDeleteByIata( airport_id );

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM airport
                    WHERE iata_id = ?
                    """);
            preparedStatement.setString(1, airport_id);
            preparedStatement.executeUpdate();
            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
