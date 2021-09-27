package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingPayment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingPaymentDAO {

    public static boolean paymentExists(BookingPayment payment) {
        Integer booking_id = payment.getBooking_id();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id=%d""",
                    booking_id ));

            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentExists(Booking booking) {
        Integer booking_id = booking.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id=%d""",
                    booking_id ));

            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentExists(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id=%d""",
                    booking_id ));

            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static BookingPayment getBookingPayment(Booking booking) {
        Integer booking_id = booking.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id=%d
                    """, booking_id));

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

    public static BookingPayment getBookingPayment(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_payment
                    WHERE booking_id=%d
                    """, booking_id));

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<BookingPayment> refundedPayments() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_payment
                    WHERE refunded=1
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<BookingPayment> clearedPayments() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_payment
                    WHERE refunded=0
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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO booking_payment
                    VALUES (%d, %s, %d)""",
                    payment.getBooking_id(), payment.getStripe_id(), payment.isRefunded()? 1 : 0));
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE booking_payment
                    SET stripe_id=%s, is_refunded=%d
                    WHERE id=%d""",
                    payment.getStripe_id(), payment.isRefunded()? 1 : 0, payment.getBooking_id()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentDelete(BookingPayment payment) {
        Integer booking_id = payment.getBooking_id();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_payment
                    WHERE booking_id=%d""",
                    booking_id ));

            if (BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentDelete(Booking booking) {
        Integer booking_id = booking.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_payment
                    WHERE booking_id=%d""",
                    booking_id ));

            if (BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean paymentDelete(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_payment
                    WHERE booking_id=%d""",
                    booking_id ));

            if (BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);

            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}


