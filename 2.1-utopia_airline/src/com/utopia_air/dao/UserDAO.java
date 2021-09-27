package com.utopia_air.dao;

import com.utopia_air.classes.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static boolean userExists(User user) {
        Integer user_id = user.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user
                    WHERE id=%d""",
                    user_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean userExists(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user
                    WHERE id=%d""",
                    user_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean userExistsByRoleId(Integer role_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user
                    WHERE role_id=%d""",
                    role_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static User getUser(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user
                    WHERE id=%d""",
                    user_id));

            if(rs.next()) {
                User user = new User();
                user.setId( rs.getInt("id") );
                user.setRole_id( rs.getInt("role_id") );
                user.setGiven_name( rs.getString("given_name") );
                user.setFamily_name( rs.getString("family_name"));
                user.setUsername( rs.getString("username") );
                user.setEmail( rs.getString("email") );
                user.setPassword( rs.getString("password") );
                user.setPhone( rs.getString("phone") );

                return user;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<User> getUsersByRoleId(Integer role_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM user
                    WHERE role_id=%d""",
                    role_id));

            List<User> users = new ArrayList<>();

            while(rs.next()) {
                User user = new User();
                user.setId( rs.getInt("id") );
                user.setRole_id( rs.getInt("role_id") );
                user.setGiven_name( rs.getString("given_name") );
                user.setFamily_name( rs.getString("family_name"));
                user.setUsername( rs.getString("username") );
                user.setEmail( rs.getString("email") );
                user.setPassword( rs.getString("password") );
                user.setPhone( rs.getString("phone") );

                users.add(user);
            }
            return users;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean insertUser(User user) {
        if(!UserRoleDAO.roleExists( user.getRole_id() )) {
            System.out.printf(
                    "There is no ID in the user_role table that matches %d%n",
                    user.getRole_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    INSERT INTO user
                    VALUES(%d, %d, %s, %s, %s, "%s", "%s", %s)""",
                    user.getId(),
                    user.getRole_id(),
                    user.getGiven_name(),
                    user.getFamily_name(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone()
            ));

            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean updateUser(User user) {
        if(!UserRoleDAO.roleExists( user.getRole_id() )) {
            System.out.printf(
                    "There is no ID in the user_role table that matches %d%n",
                    user.getRole_id());
            System.out.println("This is a coding error that should have been avoided.");
            return false;
        }

        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    UPDATE user
                    SET role_id=%d, given_name=%s, family_name=%s, username=%s, email=%s, password=%s, phone=%s
                    WHERE id=%d""",
                    user.getRole_id(),
                    user.getGiven_name(),
                    user.getFamily_name(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getId()
            ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUser(Integer user_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM user
                    WHERE id=%d""",
                    user_id));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUser(User user) {
        Integer user_id = user.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM user
                    WHERE id=%d""",
                    user_id));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUserByRoleId(Integer role_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM user
                    WHERE role_id=%d""",
                    role_id));
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
