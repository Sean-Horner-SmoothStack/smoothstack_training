package com.utopia_air.dao;

import com.utopia_air.classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingDAO {

    public static boolean flightBookingExistsByBookingId(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight_booking
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightBookingExistsByBookingId(Booking booking) {
        return flightBookingExistsByBookingId(booking.getId());
    }

    public static FlightBooking getFlightBookingByBookingId(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight_booking
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                FlightBooking flight_booking = new FlightBooking();
                flight_booking.setBooking_id( rs.getInt("booking_id") );
                flight_booking.setFlight_id( rs.getInt("flight_id") );

                return flight_booking;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static FlightBooking getFlightBookingByBookingId(Booking booking) {
        return getFlightBookingByBookingId(booking.getId());
    }

    public static FlightBooking getFlightBookingByFlightId(Integer flight_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM flight_booking
                    WHERE flight_id = ?
                    """);
            preparedStatement.setInt(1, flight_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                FlightBooking flight_booking = new FlightBooking();
                flight_booking.setBooking_id( rs.getInt("booking_id") );
                flight_booking.setFlight_id( rs.getInt("flight_id") );

                return flight_booking;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static FlightBooking getFlightBookingByFlightId(Flight flight) {
        return getFlightBookingByFlightId(flight.getFlight_id());
    }

    public static FlightBooking getFlightBookingByFlightId(Flight_Table flight) {
        return getFlightBookingByFlightId(flight.getId());
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
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO flight_booking
                    VALUES(?, ?)
                    """);
            preparedStatement.setInt(1, flight_booking.getFlight_id());
            preparedStatement.setInt(2, flight_booking.getBooking_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightBookingUpdate(FlightBooking flight_booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE flight_booking
                    SET flight_id = ?
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, flight_booking.getFlight_id());
            preparedStatement.setInt(2, flight_booking.getBooking_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightBookingDeleteByFlightId(Integer flight_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM flight_booking
                    WHERE flight_id = ?
                    """);
            preparedStatement.setInt(1, flight_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightBookingDeleteByFlightId(FlightBooking flight_booking) {
        return flightBookingDeleteByFlightId(flight_booking.getFlight_id());
    }

    public static boolean flightBookingDeleteByFlightId(Flight flight) {
        return flightBookingDeleteByFlightId(flight.getFlight_id());
    }

    public static boolean flightBookingDeleteByFlightId(Flight_Table flight) {
        return flightBookingDeleteByFlightId(flight.getId());
    }

    public static boolean flightBookingDeleteByBookingId(Integer booking_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM flight_booking
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean flightBookingDeleteByBookingId(Booking booking) {
        return flightBookingDeleteByBookingId(booking.getId());
    }

    public static boolean flightBookingDeleteByBookingId(BookingGuest booking_guest) {
        return flightBookingDeleteByBookingId(booking_guest.getBooking_id());
    }

    public static boolean flightBookingDeleteByBookingId(BookingUser booking_user) {
        return flightBookingDeleteByBookingId(booking_user.getBooking_id());
    }

    public static boolean flightBookingDeleteByBookingId(BookingAgent booking_agent) {
        return flightBookingDeleteByBookingId(booking_agent.getBooking_id());
    }

    public static boolean flightBookingDeleteByBookingId(BookingPayment payment) {
        return flightBookingDeleteByBookingId(payment.getBooking_id());
    }

    public static boolean flightBookingDeleteByBookingId(Passenger passenger) {
        return flightBookingDeleteByBookingId(passenger.getBooking_id());
    }
}
