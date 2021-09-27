package com.utopia_air.dao;

import com.utopia_air.classes.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO {

    public static boolean airportExists(String iata_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airport
                    WHERE iata_id = '%s'""",
                    iata_id));

            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Airport getAirport(String iata_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airport
                    WHERE iata_id = '%s'""",
                    iata_id));

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
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airport
                    WHERE iata_id = '%s'""",
                    iata_id));

            if(rs.next())
                return rs.getString("city");

        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static String getAirportId(String city) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airport
                    WHERE city = '%s'""",
                    city));

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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO airplane
                    VALUES('%c%c%c', '%s')""",
                    airport.getIata_id().charAt(0),
                    airport.getIata_id().charAt(1),
                    airport.getIata_id().charAt(2),
                    airport.getCity() ));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airportUpdate(Airport airport) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE airplane
                    SET city = '%s'
                    WHERE iata_id = '%s'""",
                    airport.getCity(),
                    airport.getIata_id() ));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airportDelete(Airport airport) {
        if(RouteDAO.routeExists( airport.getIata_id() ))
            RouteDAO.routeDeleteByIata( airport.getIata_id() );

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM airport
                    WHERE iata_id = '%s'""",
                    airport.getIata_id()));
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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM airplane_type
                    WHERE iata_id='%s'""",
                    airport_id ));
            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
