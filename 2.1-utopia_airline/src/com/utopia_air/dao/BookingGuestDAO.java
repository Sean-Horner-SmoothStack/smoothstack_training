package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingGuest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingGuestDAO {

    public static boolean bookingGuestExistsByUserId(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_guest
                    WHERE user_id=%d
                    """, user_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingGuestExistsByBooking(Booking booking) {
        Integer booking_id = booking.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_guest
                    WHERE booking_id=%d""",
                    booking_id ));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingGuestExists(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_guest
                    WHERE booking_id=%d""",
                    booking_id ));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static BookingGuest getBookingGuestByBooking(Booking booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_guest
                    WHERE booking_id=%d""",
                    booking.getId() ));

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

    public static BookingGuest getBookingGuestByBookingId(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_guest
                    WHERE booking_id=%d
                    """, booking_id));

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

    public static BookingGuest getBookingGuestByUserId(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_guest
                    WHERE user_id=%d
                    """, user_id));

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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO booking_guest
                    VALUES(%d, %s, %s)""",
                    bookingGuest.getBooking_id(),
                    bookingGuest.getContact_email(),
                    bookingGuest.getContact_phone() ));
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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE booking_guest
                    SET email=%s, phone=%s
                    WHERE booking_id=%d""",
                    bookingGuest.getContact_email(),
                    bookingGuest.getContact_phone(),
                    bookingGuest.getBooking_id()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingGuestDelete(BookingGuest bookingGuest) {
        Integer booking_id = bookingGuest.getBooking_id();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_guest
                    WHERE booking_id=%d""",
                    booking_id ));

            if(BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingGuestDelete(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_guest
                    WHERE booking_id=%d""",
                    booking_id ));

            if(BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }
}
