package com.utopia_air.dao;

import com.utopia_air.classes.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {

    public static boolean passengerExists(Integer passenger_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM passenger
                    WHERE id = %d""",
                    passenger_id));

            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Passenger getPassenger(Integer passenger_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM passenger
                    WHERE id = %d""",
                    passenger_id));

            if(rs.next()) {
                Passenger passenger = new Passenger();
                passenger.setId( rs.getInt("id") );
                passenger.setBooking_id( rs.getInt("booking_id") );
                passenger.setGiven_name( rs.getString("given_name") );
                passenger.setFamily_name( rs.getString("family_name") );
                passenger.setDob( rs.getDate("dob") );
                passenger.setGender( rs.getString("gender") );
                passenger.setAddress( rs.getString("address") );

                return passenger;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Passenger> getAllPassengers() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM passenger
                    ORDER BY id""");

            List<Passenger> passengers = new ArrayList<>();
            while(rs.next()) {
                Passenger passenger = new Passenger();
                passenger.setId( rs.getInt("id") );
                passenger.setBooking_id( rs.getInt("booking_id") );
                passenger.setGiven_name( rs.getString("given_name") );
                passenger.setFamily_name( rs.getString("family_name") );
                passenger.setDob( rs.getDate("dob") );
                passenger.setGender( rs.getString("gender") );
                passenger.setAddress( rs.getString("address") );

                passengers.add(passenger);
            }
            return passengers;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean passengerInsert(Passenger passenger) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO passenger
                    VALUES(%d, %d, %s, %s, %s, %s, %s)""",
                    passenger.getId(),
                    passenger.getBooking_id(),
                    passenger.getGiven_name(),
                    passenger.getFamily_name(),
                    passenger.getDob(),
                    passenger.getGender(),
                    passenger.getAddress()
            ));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerUpdate(Passenger passenger) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE passenger
                    SET booking_id = %d, given_name = %s, family_name = %s,
                        dob = %s, gender = %s, address = %s
                    WHERE id = %d""",
                    passenger.getBooking_id(),
                    passenger.getGiven_name(),
                    passenger.getFamily_name(),
                    passenger.getDob(),
                    passenger.getGender(),
                    passenger.getAddress(),
                    passenger.getId() ));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerDelete(Passenger passenger) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM passenger
                    WHERE id = %d""",
                    passenger.getId() ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerDelete(String passenger_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM passenger
                    WHERE id='%s'""",
                    passenger_id ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

}
