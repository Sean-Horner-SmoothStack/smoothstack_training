package com.utopia_air.dao;

import com.utopia_air.classes.Airplane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {

    public static boolean airplaneExists(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airplane
                    WHERE id=%d""", id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Airplane getAirplane(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airplane
                    WHERE id=%d
                    """, id));

            if(rs.next()) {
                Airplane airplane = new Airplane();
                airplane.setId( rs.getInt("id") );
                airplane.setType_id(rs.getInt("type_id") );

                return airplane;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Airplane> getAirplanesByType(Integer type_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM airplane
                    WHERE type_id=%s
                    """, type_id));

            List<Airplane> airplanes = new ArrayList<>();

            while(rs.next()) {
                Airplane airplane = new Airplane();
                airplane.setId( rs.getInt("id") );
                airplane.setType_id( rs.getInt("type_id") );

                airplanes.add(airplane);
            }

            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Airplane> getAllAirplanes() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM airplane
                    ORDER BY id
                    """);

            List<Airplane> airplanes = new ArrayList<>();

            while(rs.next()) {
                Airplane airplane = new Airplane();
                airplane.setId( rs.getInt("id") );
                airplane.setType_id( rs.getInt("type_id") );

                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean airplaneInsert(Airplane airplane) {
        if(!AirplaneTypeDAO.airplaneExists(airplane.getType_id())) {
            System.out.printf(
                    "There is no ID in the airplane_type table that matches %d%n",
                    airplane.getType_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO airplane
                    VALUES(%d, %d)""",
                    airplane.getId(),
                    airplane.getType_id() ));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Integer airplaneInsert(Integer airplane_type) {
        if(!AirplaneTypeDAO.airplaneExists(airplane_type)) {
            System.out.printf(
                    "There is no ID in the airplane_type table that matches %d%n",
                    airplane_type);
            System.out.println("This is a coding error that should have been avoided.");
            return null;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT a.id + 1 AS firstAvailableId
                    FROM airplane AS a
                    LEFT JOIN airplane AS a1
                    	ON a1.id = a.id + 1
                    WHERE a1.id IS NULL
                    ORDER BY a.id
                    LIMIT 0, 1""");
            int nextId = rs.getInt("firstAvailableId");

            PreparedStatement ps = conn.prepareStatement("""
                    INSERT INTO airplane
                    VALUES(?, ?)""");
            ps.setInt(1, nextId);
            ps.setInt(2, airplane_type);
            ps.executeUpdate();

            System.out.println("New airplane added successfully.");

            return nextId;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean airplaneUpdate(Airplane airplane) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("""
                    UPDATE airplane
                    SET type_id = ?
                    WHERE id = ?""");
            ps.setInt(1, airplane.getType_id());
            ps.setInt(2, airplane.getId());
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    /*
     * DELETION METHODS
     */

    public static boolean airplaneDelete(Airplane airplane) {
        Integer airplane_id = airplane.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    """
                    DELETE
                    FROM airplane
                    WHERE id = ?""");
            ps.setInt(1, airplane_id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airplaneDeleteById(Integer airplane_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    """
                    DELETE
                    FROM airplane
                    WHERE id = ?""");
            ps.setInt(1, airplane_id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airplaneDeleteByType(Integer airplaneType_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("""
                    DELETE
                    FROM airplane
                    WHERE type_id = ?
                    """);
            ps.setInt(1, airplaneType_id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
