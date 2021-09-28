package com.utopia_air.dao;

import com.utopia_air.classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingGuestDAO {

    public static boolean bookingGuestExistsByUserId(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_guest
                    WHERE user_id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingGuestExists(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_guest
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static BookingGuest getBookingGuestByBookingId(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_guest
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingGuest booking_guest = new BookingGuest();
                booking_guest.setBooking_id( rs.getInt("booking_id") );
                booking_guest.setContact_email( rs.getString("contact_email") );
                booking_guest.setContact_phone( rs.getString("phone") );

                return booking_guest;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean bookingGuestExistsByBookingId(Booking booking) {
        return bookingGuestExists(booking.getId());
    }

    public static BookingGuest getBookingGuestByUserId(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_guest
                    WHERE user_id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingGuest booking_guest = new BookingGuest();
                booking_guest.setBooking_id( rs.getInt("booking_id") );
                booking_guest.setContact_email( rs.getString("contact_email") );
                booking_guest.setContact_phone( rs.getString("phone") );

                return booking_guest;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static BookingGuest getBookingGuestByUserId(User user) {
        return getBookingGuestByUserId(user.getId());
    }

    public static List<BookingGuest> getAllBookingGuests() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_guest
                    ORDER BY user_id
                    """);

            List<BookingGuest> booking_guests = new ArrayList<>();
            while(rs.next()) {
                BookingGuest booking_guest = new BookingGuest();
                booking_guest.setBooking_id( rs.getInt("booking_id") );
                booking_guest.setContact_email( rs.getString("contact_email") );
                booking_guest.setContact_phone( rs.getString("phone") );

                booking_guests.add(booking_guest);
            }
            return booking_guests;
        } catch (SQLException e) { e.printStackTrace(); }

        return null;
    }

    public static boolean bookingGuestInsert(BookingGuest bookingGuest) {
        if (!BookingDAO.bookingExists(bookingGuest.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    bookingGuest.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO booking_guest
                    VALUES(?, ?, ?)
                    """);
            preparedStatement.setInt(1, bookingGuest.getBooking_id() );
            preparedStatement.setString(2, bookingGuest.getContact_email() );
            preparedStatement.setString(3, bookingGuest.getContact_phone() );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingGuestUpdate(BookingGuest bookingGuest) {
        if (!BookingDAO.bookingExists(bookingGuest.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    bookingGuest.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE booking_guest
                    SET email = ?, phone = ?
                    WHERE booking_id = ?
                    """);
            preparedStatement.setString(1, bookingGuest.getContact_email() );
            preparedStatement.setString(2, bookingGuest.getContact_phone() );
            preparedStatement.setInt(3, bookingGuest.getBooking_id() );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingGuestDelete(Integer booking_id) {
        boolean proceed = true;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM booking_guest
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();

            if(BookingDAO.bookingExists(booking_id))
                proceed = BookingDAO.setBookingInactive(booking_id);

            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingGuestDelete(BookingGuest bookingGuest) {
        return bookingGuestDelete(bookingGuest.getBooking_id());
    }
}
