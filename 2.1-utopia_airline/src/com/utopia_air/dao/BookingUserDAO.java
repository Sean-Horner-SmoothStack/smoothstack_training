package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingUserDAO {

    public static boolean bookingUserExists(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_user
                    WHERE user_id=%d
                    """, user_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingUserExists(Booking booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_user
                    WHERE booking_id=%d""",
                    booking.getId()));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static BookingUser getBookingUserByBooking(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_user
                    WHERE booking_id=%d
                    """, booking_id));

            if(rs.next()) {
                BookingUser booking_user = new BookingUser();
                booking_user.setBooking_id( rs.getInt("booking_id") );
                booking_user.setUser_id( rs.getInt("user_id") );

                return booking_user;
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return null;
    }

    public static BookingUser getBookingUserById(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_user
                    WHERE user_id=%d
                    """, user_id));

            if(rs.next()) {
                BookingUser booking_user = new BookingUser();
                booking_user.setBooking_id( rs.getInt("booking_id") );
                booking_user.setUser_id( rs.getInt("user_id") );

                return booking_user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<BookingUser> getAllBookingUsers() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_user
                    ORDER BY user_id
                    """);

            List<BookingUser> booking_users = new ArrayList<>();

            while(rs.next()) {
                BookingUser booking_user = new BookingUser();
                booking_user.setBooking_id( rs.getInt("booking_id") );
                booking_user.setUser_id( rs.getInt("user_id") );

                booking_users.add(booking_user);
            }

            return booking_users;
        } catch (SQLException e) { e.printStackTrace(); }

        return null;
    }

    public static boolean bookingUserInsert(BookingUser bookingUser) {
        if (!BookingDAO.bookingExists(bookingUser.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    bookingUser.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO booking_user
                    VALUES(%d, %d)
                    """, bookingUser.getBooking_id(), bookingUser.getUser_id()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingUserUpdate(BookingUser bookingUser) {
        if (!BookingDAO.bookingExists(bookingUser.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    bookingUser.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE booking_user
                    SET user_id=%d
                    WHERE booking_id=%d
                    """, bookingUser.getUser_id(), bookingUser.getBooking_id()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public static boolean bookingUserDelete(BookingUser bookingUser) {
        Integer booking_id = bookingUser.getBooking_id();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_user
                    WHERE booking_id=%d""",
                    booking_id ));

            if(BookingDAO.bookingExists( booking_id ))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingUserDelete(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_user
                    WHERE booking_id=%d""",
                    booking_id ));

            if(BookingDAO.bookingExists( booking_id ))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
