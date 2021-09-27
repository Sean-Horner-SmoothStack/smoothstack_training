package com.utopia_air.dao;

import com.utopia_air.classes.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO {

    public static boolean roleExists(Integer role_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user_role
                    WHERE id=%d
                    """, role_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean roleExists(UserRole userRole) {
        Integer role_id = userRole.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user_role
                    WHERE id=%d
                    """, role_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static UserRole getUserRole(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user_role
                    WHERE id=%d
                    """, id));

            if(rs.next()) {
                UserRole user_role = new UserRole();
                user_role.setId( rs.getInt("id") );
                user_role.setName( rs.getString("name") );

                return user_role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static UserRole getUserRole(String name) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user_role
                    WHERE name=%s
                    """, name));

            if(rs.next()) {
                UserRole user_role = new UserRole();
                user_role.setId( rs.getInt("id") );
                user_role.setName( rs.getString("name") );

                return user_role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean deleteUserRole(Integer role_id) {
        boolean proceed = true;
        if(UserDAO.userExistsByRoleId(role_id))
            proceed = UserDAO.deleteUserByRoleId(role_id);

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM user_role
                    WHERE id=%d""",
                    role_id));
            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUserRole(UserRole role) {
        Integer role_id = role.getId();
        boolean proceed = true;
        if(UserDAO.userExistsByRoleId(role_id))
            proceed = UserDAO.deleteUserByRoleId(role_id);

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM user_role
                    WHERE id=%d""",
                    role_id));
            return proceed;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
