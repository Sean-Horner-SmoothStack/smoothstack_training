package com.utopia_air.dao;

import com.utopia_air.classes.User;
import com.utopia_air.classes.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static boolean userExists(Integer user_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean userExists(User user) {
        return userExists(user.getId());
    }

    public static boolean userExistsByRoleId(Integer role_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user
                    WHERE role_id = ?
                    """);
            preparedStatement.setInt(1, role_id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean userExistsByRoleId(UserRole role) {
        return userExistsByRoleId(role.getId());
    }

    public static User getUser(Integer user_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();

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
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM user
                    WHERE role_id = ?
                    """);
            preparedStatement.setInt(1, role_id);
            ResultSet rs = preparedStatement.executeQuery();

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

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO user
                    VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                    """);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getRole_id());
            preparedStatement.setString(3, user.getGiven_name());
            preparedStatement.setString(4, user.getFamily_name());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getPhone());
            preparedStatement.executeUpdate();
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

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE user
                    SET role_id = ?, given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ?
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, user.getRole_id());
            preparedStatement.setString(2, user.getGiven_name());
            preparedStatement.setString(3, user.getFamily_name());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUser(Integer user_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM user
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUser(User user) {
        return deleteUser(user.getId());
    }

    public static boolean deleteUserByRoleId(Integer role_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM user
                    WHERE role_id = ?
                    """);
            preparedStatement.setInt(1, role_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean deleteUserByRoleId(UserRole role) {
        return deleteUserByRoleId(role.getId());
    }
}
