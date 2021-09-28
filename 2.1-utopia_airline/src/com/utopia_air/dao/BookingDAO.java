package com.utopia_air.dao;

import com.utopia_air.classes.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public static boolean bookingExists(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking
                    WHERE id=%d
                    """, booking_id));
            return rs.next();

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static Booking getBooking(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking
                    WHERE id=%d
                    """, id));

            if(rs.next()) {
                Booking booking = new Booking();
                booking.setId( rs.getInt("id") );
                booking.setIs_active( rs.getBoolean("is_active") );
                booking.setConfirmation_code( rs.getString("confirmation_code"));

                return booking;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static Booking getBooking(String conf_code) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking
                    WHERE confirmation_code=%s
                    """, conf_code));

            if(rs.next()) {
                Booking booking = new Booking();
                booking.setId( rs.getInt("id") );
                booking.setIs_active( rs.getBoolean("is_active") );
                booking.setConfirmation_code( rs.getString("confirmation_code"));

                return booking;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Booking> getActiveBookings() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking
                    WHERE is_active=1
                    """);

            List<Booking> bookings = new ArrayList<>();
            while(rs.next()) {
                Booking booking = new Booking();
                booking.setId( rs.getInt("id") );
                booking.setIs_active( rs.getBoolean("is_active") );
                booking.setConfirmation_code( rs.getString("confirmation_code"));

                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Booking> getInactiveBookings() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking
                    WHERE is_active=0
                    """);

            List<Booking> bookings = new ArrayList<>();

            while(rs.next()) {
                Booking booking = new Booking();
                booking.setId( rs.getInt("id") );
                booking.setIs_active( rs.getBoolean("is_active") );
                booking.setConfirmation_code( rs.getString("confirmation_code"));

                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Booking> getAllBookings() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking
                    ORDER BY id
                    """);

            List<Booking> bookings = new ArrayList<>();

            while(rs.next()) {
                Booking booking = new Booking();
                booking.setId( rs.getInt("id") );
                booking.setIs_active( rs.getBoolean("is_active") );
                booking.setConfirmation_code( rs.getString("confirmation_code"));

                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean bookingInsert(Booking booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO booking
                    VALUES(%d, %d, %s)""",
                    booking.getId(),
                    booking.getIs_active() ? 1 : 0,
                    booking.getConfirmation_code() ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingUpdate(Booking booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE booking
                    SET is_active=%d, confirmation_code=%s
                    WHERE booking_id=%d""",
                    booking.getIs_active() ? 1 : 0,
                    booking.getConfirmation_code(),
                    booking.getId() ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean setBookingInactive(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE booking
                    SET is_active = 0
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean setBookingInactive(Booking booking) {
        return setBookingInactive(booking.getId());
    }

    public static boolean setBookingActive(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE booking
                    SET is_active = 1
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean setBookingActive(Booking booking) {
        return setBookingActive(booking.getId());
    }

    public static boolean bookingDelete(Integer booking_id) {
        boolean proceed = true;
        if(BookingAgentDAO.bookingAgentExists(booking_id))
            proceed = BookingAgentDAO.bookingAgentDelete(booking_id);
        if(BookingUserDAO.bookingUserExistsByUserId(booking_id))
            proceed = BookingUserDAO.bookingUserDelete(booking_id);
        if(BookingGuestDAO.bookingGuestExists(booking_id))
            proceed = BookingGuestDAO.bookingGuestDelete(booking_id);

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM booking
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingDelete(Booking booking) {
        return bookingDelete(booking.getId());
    }
}
