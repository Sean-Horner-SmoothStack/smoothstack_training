package com.utopia_air.dao;

import com.utopia_air.classes.BookingUser;
import com.utopia_air.classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingUserDAO {

    public static boolean bookingUserExistsByUserId(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_user
                    WHERE user_id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingUserExistsByUserId(User user) {
        return bookingUserExistsByUserId(user.getId());
    }

    public static BookingUser getBookingUserByBooking(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_user
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingUser booking_user = new BookingUser();
                booking_user.setBooking_id( rs.getInt("booking_id") );
                booking_user.setUser_id( rs.getInt("user_id") );

                return booking_user;
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return null;
    }

    public static BookingUser getBookingUserByUserId(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_user
                    WHERE user_id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingUser booking_user = new BookingUser();
                booking_user.setBooking_id( rs.getInt("booking_id") );
                booking_user.setUser_id( rs.getInt("user_id") );

                return booking_user;
            }
        } catch (SQLException e) { e.printStackTrace(); }
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
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO booking_user
                    VALUES(?, ?)
                    """);
            preparedStatement.setInt(1, bookingUser.getBooking_id());
            preparedStatement.setInt(2, bookingUser.getUser_id());
            preparedStatement.executeUpdate();
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
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE booking_user
                    SET user_id = ?
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, bookingUser.getUser_id());
            preparedStatement.setInt(2, bookingUser.getBooking_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingUserDelete(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            boolean proceed = true;
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM booking_user
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();

            if(BookingDAO.bookingExists( booking_id ))
                proceed = BookingDAO.setBookingInactive(booking_id);

            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingUserDelete(BookingUser bookingUser) {
        return bookingUserDelete(bookingUser.getBooking_id());
    }
}
