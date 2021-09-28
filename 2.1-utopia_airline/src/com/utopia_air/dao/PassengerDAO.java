package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingPayment;
import com.utopia_air.classes.FlightBooking;
import com.utopia_air.classes.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {

    public static boolean passengerExists(Integer passenger_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM passenger
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, passenger_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerExists(Passenger passenger) {
        return passengerExists(passenger.getId());
    }

    public static Passenger getPassenger(Integer passenger_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM passenger
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, passenger_id);
            ResultSet rs = preparedStatement.executeQuery();

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

    public static Passenger getPassengerByBookingId(Integer booking_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM passenger
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();

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

    public static Passenger getPassengerByBookingId(Booking booking) {
        return getPassengerByBookingId(booking.getId());
    }

    public static Passenger getPassengerByBookingId(FlightBooking flight_booking) {
        return getPassengerByBookingId(flight_booking.getBooking_id());
    }

    public static Passenger getPassengerByBookingId(BookingPayment payment) {
        return getPassengerByBookingId(payment.getBooking_id());
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
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO passenger
                    VALUES(?, ?, ?, ?, ?, ?, ?)
                    """);
            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setInt(2, passenger.getBooking_id());
            preparedStatement.setString(3, passenger.getGiven_name());
            preparedStatement.setString(4, passenger.getFamily_name());
            preparedStatement.setDate(5, passenger.getDob());
            preparedStatement.setString(6, passenger.getGender());
            preparedStatement.setString(7, passenger.getAddress());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerUpdate(Passenger passenger) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE passenger
                    SET booking_id = ?, given_name = ?, family_name = ?,
                        dob = ?, gender = ?, address = ?
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, passenger.getBooking_id());
            preparedStatement.setString(2, passenger.getGiven_name());
            preparedStatement.setString(3, passenger.getFamily_name());
            preparedStatement.setDate(4, passenger.getDob());
            preparedStatement.setString(5, passenger.getGender());
            preparedStatement.setString(6, passenger.getAddress());
            preparedStatement.setInt(7, passenger.getId());
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerDelete(Integer passenger_id) {
        Integer booking_id = PassengerDAO.getPassenger(passenger_id).getBooking_id();
        BookingDAO.setBookingInactive(booking_id);
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM passenger
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, passenger_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean passengerDelete(Passenger passenger) {
        return passengerDelete(passenger.getId());
    }
}
