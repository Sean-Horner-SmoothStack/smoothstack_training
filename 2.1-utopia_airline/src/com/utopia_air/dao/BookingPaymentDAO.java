package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingPayment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingPaymentDAO {

    public static boolean paymentExists(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentExists(BookingPayment payment) {
        return paymentExists(payment.getBooking_id());
    }

    public static boolean paymentExists(Booking booking) {
        return paymentExists(booking.getId());
    }

    public static BookingPayment getBookingPayment(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingPayment payment = new BookingPayment();
                payment.setBooking_id( rs.getInt("booking_id") );
                payment.setStripe_id( rs.getString("stripe_id") );
                payment.setRefunded( rs.getBoolean("refunded") );

                return payment;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static BookingPayment getBookingPayment(Booking booking) {
        return getBookingPayment(booking.getId());
    }

    public static BookingPayment getBookingPaymentByStripe(String stripe_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_payment
                    WHERE stripe_id=%s
                    """, stripe_id));

            if(rs.next()) {
                BookingPayment payment = new BookingPayment();
                payment.setBooking_id( rs.getInt("booking_id") );
                payment.setStripe_id( rs.getString("stripe_id") );
                payment.setRefunded( rs.getBoolean("refunded") );

                return payment;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<BookingPayment> refundedPayments() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_payment
                    WHERE refunded = 1
                    """);

            List<BookingPayment> payments = new ArrayList<>();
            while(rs.next()) {
                BookingPayment payment = new BookingPayment();
                payment.setBooking_id( rs.getInt("booking_id") );
                payment.setStripe_id( rs.getString("stripe_id") );
                payment.setRefunded( rs.getBoolean("refunded") );

                payments.add(payment);
            }
            return payments;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<BookingPayment> clearedPayments() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_payment
                    WHERE refunded = 0
                    """);

            List<BookingPayment> payments = new ArrayList<>();
            while(rs.next()) {
                BookingPayment payment = new BookingPayment();
                payment.setBooking_id( rs.getInt("booking_id") );
                payment.setStripe_id( rs.getString("stripe_id") );
                payment.setRefunded( rs.getBoolean("refunded") );

                payments.add(payment);
            }
            return payments;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean paymentInsert(BookingPayment payment) {
        if (!BookingDAO.bookingExists(payment.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    payment.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO booking_payment
                    VALUES (?, ?, ?)
                    """);
            preparedStatement.setInt(1, payment.getBooking_id());
            preparedStatement.setString(2, payment.getStripe_id());
            preparedStatement.setInt(3, payment.isRefunded()? 1 : 0);
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    };

    public static boolean paymentUpdate(BookingPayment payment) {
        if (!BookingDAO.bookingExists(payment.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    payment.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE booking_payment
                    SET stripe_id = ?, is_refunded = ?
                    WHERE id = ?
                    """);
            preparedStatement.setString(1, payment.getStripe_id());
            preparedStatement.setInt(2, payment.isRefunded()? 1 : 0);
            preparedStatement.setInt(3, payment.getBooking_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentDelete(Integer booking_id) {
        boolean proceed = true;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM booking_payment
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();

            if (BookingDAO.bookingExists(booking_id))
                proceed = BookingDAO.setBookingInactive(booking_id);

            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentDelete(Booking booking) {
        return paymentDelete(booking.getId());
    }

    public static boolean paymentDelete(BookingPayment payment) {
        return  paymentDelete(payment.getBooking_id());
    }
}


