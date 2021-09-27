package com.utopia_air.dao;

import com.utopia_air.classes.FlightBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingDAO {

    public static boolean flightBookingExists(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight_booking
                    WHERE booking_id=%d""",
                    booking_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static FlightBooking getFlightBooking(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight_booking
                    WHERE id=%d
                    """, id));

            if(rs.next()) {
                FlightBooking flight_booking = new FlightBooking();
                flight_booking.setBooking_id( rs.getInt("booking_id") );
                flight_booking.setFlight_id( rs.getInt("flight_id") );

                return flight_booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static FlightBooking getFlightBooking(String name) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM flight_booking
                    WHERE name=%s
                    """, name));

            if(rs.next()) {
                FlightBooking flight_booking = new FlightBooking();
                flight_booking.setBooking_id( rs.getInt("booking_id") );
                flight_booking.setFlight_id( rs.getInt("flight_id") );

                return flight_booking;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<FlightBooking> getAllFlightBookings() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM flight_booking
                    ORDER BY id
                    """);

            List<FlightBooking> flight_bookings = new ArrayList<>();
            while(rs.next()) {
                FlightBooking flight_booking = new FlightBooking();
                flight_booking.setBooking_id( rs.getInt("booking_id") );
                flight_booking.setFlight_id( rs.getInt("flight_id") );

                flight_bookings.add(flight_booking);
            }
            return flight_bookings;
        } catch (SQLException e) { e.printStackTrace(); }

        return null;
    }

    public static boolean flightBookingInsert(FlightBooking flight_booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO flight_booking
                    VALUES(%d, %d)""",
                    flight_booking.getBooking_id(),
                    flight_booking.getFlight_id()
            ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightBookingUpdate(FlightBooking flight_booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE flight_booking
                    SET flight_id=%d
                    WHERE booking_id=%d""",
                    flight_booking.getFlight_id(),
                    flight_booking.getBooking_id()
            ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
