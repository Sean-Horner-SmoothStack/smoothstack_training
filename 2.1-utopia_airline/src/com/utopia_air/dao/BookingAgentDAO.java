package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingAgent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingAgentDAO {

    public static boolean bookingAgentExists(Integer agent_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_agent
                    WHERE agent_id = ?
                    """);
            preparedStatement.setInt(1, agent_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingAgentExists(Booking booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_agent
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking.getId());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static BookingAgent getBookingAgentByBooking(Integer book_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_agent
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, book_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingAgent agent = new BookingAgent();
                agent.setBooking_id( rs.getInt("booking_id") );
                agent.setAgent_id( rs.getInt("agent_id") );

                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static BookingAgent getBookingAgentByBooking(Booking booking) {
        return getBookingAgentByBooking(booking.getId());
    }

    public static BookingAgent getBookingAgentById(Integer agent_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM booking_agent
                    WHERE agent_id = ?
                    """);
            preparedStatement.setInt(1, agent_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                BookingAgent agent = new BookingAgent();
                agent.setBooking_id( rs.getInt("booking_id") );
                agent.setAgent_id( rs.getInt("agent_id") );

                return agent;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<BookingAgent> getAllAgents() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM booking_agent
                    ORDER BY agent_id
                    """);

            List<BookingAgent> agents = new ArrayList<>();
            while(rs.next()) {
                BookingAgent agent = new BookingAgent();
                agent.setBooking_id( rs.getInt("booking_id") );
                agent.setAgent_id( rs.getInt("agent_id") );

                agents.add(agent);
            }
            return agents;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean bookingAgentInsert(Integer agent_id, Integer booking_id) {
        if (!BookingDAO.bookingExists(booking_id)) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    booking_id);
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO booking_agent
                    VALUES(?, ?)
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.setInt(2, agent_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
    return false;
    }

    public static boolean bookingAgentInsert(BookingAgent agent) {
        return bookingAgentInsert(agent.getAgent_id(), agent.getBooking_id());
    }

    public static boolean bookingAgentUpdate(Integer agent_id, Integer booking_id) {
        if (!BookingDAO.bookingExists(booking_id)) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    booking_id);
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE booking_agent
                    SET agent_id = ?
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, agent_id);
            preparedStatement.setInt(2, booking_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingAgentUpdate(BookingAgent agent) {
        return bookingAgentUpdate(agent.getAgent_id(), agent.getBooking_id());
    }

    /*
     * DELETION METHODS
     */

    public static boolean bookingAgentDelete(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM booking_agent
                    WHERE booking_id = ?
                    """);
            preparedStatement.setInt(1, booking_id);
            preparedStatement.executeUpdate();

            if(BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingAgentDelete(BookingAgent agent) {
        return bookingAgentDelete(agent.getBooking_id());
    }
}
