package com.utopia_air.dao;

import com.utopia_air.classes.Airplane;
import com.utopia_air.classes.AirplaneType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneTypeDAO {

    /*
     * EXISTENCE METHODS
     */

    public static boolean airplaneExists(Airplane airplane) {
        Integer airplane_id = airplane.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airplane
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplane_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airplaneExists(Integer airplane_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airplane
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplane_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    /*
     * SELECTION METHODS
     */

    public static AirplaneType getAirplaneType(Integer type) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airplane_type
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                AirplaneType plane_type = new AirplaneType();
                plane_type.setId( rs.getInt("id") );
                plane_type.setMax_capacity( rs.getInt("max_capacity") );

                return plane_type;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static Integer getAirplaneCapacity(Integer airplane_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airplane_type
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplane_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                return rs.getInt("max_capacity");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<AirplaneType> getAirplanesByCap(Integer min_cap) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM airplane_type
                    WHERE max_capacity >= ?
                    """);
            preparedStatement.setInt(1, min_cap);
            ResultSet rs = preparedStatement.executeQuery();

            List<AirplaneType> airplanes = new ArrayList<>();
            while(rs.next()) {
                AirplaneType airplane = new AirplaneType();
                airplane.setId( rs.getInt("id") );
                airplane.setMax_capacity( rs.getInt("max_capacity") );

                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<AirplaneType> getAllAirplanes() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM airplane_type
                    ORDER BY id
                    """);

            List<AirplaneType> airplanes = new ArrayList<>();
            while(rs.next()) {
                AirplaneType airplane = new AirplaneType();
                airplane.setId( rs.getInt("id") );
                airplane.setMax_capacity( rs.getInt("max_capacity") );

                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    /*
     * CREATION METHODS
     */

    public static boolean airplaneTypeInsert(AirplaneType airplane) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO airplane
                    VALUES(%d, %d)
                    """);
            preparedStatement.setInt(1, airplane.getId());
            preparedStatement.setInt(2, airplane.getMax_capacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    /*
     * UPDATE METHODS
     */

    public static boolean airplaneTypeUpdate(AirplaneType airplane) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE airplane
                    SET max_capacity = ?
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplane.getMax_capacity());
            preparedStatement.setInt(2, airplane.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    /*
     * DELETION METHODS
     */

    public static boolean airplaneTypeDelete(AirplaneType airplaneType) {
        Integer airplaneType_id = airplaneType.getId();

        AirplaneDAO.airplaneDeleteByType(airplaneType_id);

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM airplane_type
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplaneType_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airplaneTypeDelete(Airplane airplane) {
        Integer airplaneType_id = airplane.getType_id();

        AirplaneDAO.airplaneDeleteByType(airplaneType_id);

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM airplane_type
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplaneType_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean airplaneTypeDeleteById(Integer airplaneType_id) {
        AirplaneDAO.airplaneDeleteByType(airplaneType_id);

        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM airplane_type
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, airplaneType_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
