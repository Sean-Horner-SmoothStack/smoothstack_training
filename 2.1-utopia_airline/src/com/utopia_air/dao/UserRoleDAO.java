package com.utopia_air.dao;

import com.utopia_air.classes.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO {

    public static boolean roleExists(Integer role_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user_role
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, role_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean roleExists(UserRole userRole) {
        return roleExists(userRole.getId());
    }

    public static UserRole getUserRole(Integer role_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user_role
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, role_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                UserRole user_role = new UserRole();
                user_role.setId( rs.getInt("id") );
                user_role.setName( rs.getString("name") );

                return user_role;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static UserRole getUserRoleByName(String name) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user_role
                    WHERE name = ?
                    """);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                UserRole user_role = new UserRole();
                user_role.setId( rs.getInt("id") );
                user_role.setName( rs.getString("name") );

                return user_role;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<UserRole> getAllRoles() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM user_role
                    ORDER BY id
                    """);

            List<UserRole> roles = new ArrayList<>();
            while(rs.next()) {
                UserRole user_role = new UserRole();
                user_role.setId( rs.getInt("id") );
                user_role.setName( rs.getString("name") );

                roles.add(user_role);
            }
            return roles;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean deleteUserRole(Integer role_id) {
        boolean proceed = true;
        if(UserDAO.userExistsByRoleId(role_id))
            proceed = UserDAO.deleteUserByRoleId(role_id);

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM user_role
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, role_id);
            preparedStatement.executeUpdate();
            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUserRole(UserRole role) {
        return deleteUserRole(role.getId());
    }

    public static boolean deleteUserRoleByName(String name) {

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM user_role
                    WHERE id = ?
                    """);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
