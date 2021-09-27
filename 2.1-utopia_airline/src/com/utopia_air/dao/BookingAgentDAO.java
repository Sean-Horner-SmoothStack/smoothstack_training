package com.utopia_air.dao;

import com.utopia_air.classes.Booking;
import com.utopia_air.classes.BookingAgent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingAgentDAO {

    public static boolean bookingAgentExists(Integer agent_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_agent
                    WHERE agent_id=%d
                    """, agent_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingAgentExists(Booking booking) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_agent
                    WHERE booking_id=%d""",
                    booking.getId() ));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static BookingAgent getBookingAgentByBooking(Integer book_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_agent
                    WHERE booking_id=%d
                    """, book_id));

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

    public static BookingAgent getBookingAgentById(Integer agent_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM booking_agent
                    WHERE agent_id=%d
                    """, agent_id));

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

    public static boolean bookingAgentInsert(BookingAgent agent) {
        if (!BookingDAO.bookingExists(agent.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    agent.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO booking_agent
                    VALUES(%d, %d)""",
                    agent.getBooking_id(), agent.getAgent_id()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
    return false;
    }

    public static boolean bookingAgentUpdate(BookingAgent agent) {
        if (!BookingDAO.bookingExists(agent.getBooking_id())) {
            System.out.printf(
                    "There is no ID in the booking table that matches %d%n",
                    agent.getBooking_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE booking_agent
                    SET agent_id=%d
                    WHERE booking_id=%d""",
                    agent.getAgent_id(), agent.getBooking_id()));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    /*
     * DELETION METHODS
     */

    public static boolean bookingAgentDelete(BookingAgent agent) {
        Integer booking_id = agent.getBooking_id();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_agent
                    WHERE booking_id=%d""",
                    booking_id ));
            if(BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean bookingAgentDelete(Integer booking_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM booking_agent
                    WHERE booking_id=%d""",
                    booking_id ));
            if(BookingDAO.bookingExists(booking_id))
                BookingDAO.setBookingInactive(booking_id);
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
