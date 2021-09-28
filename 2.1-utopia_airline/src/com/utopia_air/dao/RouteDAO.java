package com.utopia_air.dao;

import com.utopia_air.classes.Airport;
import com.utopia_air.classes.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public static boolean routeExists(Route route) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM route
                    WHERE origin_id=%s || destination_id=%s""",
                    route.getOrigin_id(), route.getDestination_id()));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeExists(Integer route_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM route
                    WHERE id=%d""",
                    route_id
            ));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeExists(Airport airport) {
        String iata_id = airport.getIata_id();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM route
                    WHERE origin_id=%s || destination_id=%s""",
                    iata_id, iata_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeExists(String iata_id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM route
                    WHERE origin_id=%s || destination_id=%s""",
                    iata_id, iata_id));
            return rs.next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Route getRoute(Integer id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM route
                    WHERE id=%d
                    """, id));

            if(rs.next()) {
                Route route = new Route();
                route.setId(id);
                route.setOrigin_id( rs.getString("origin_id") );
                route.setDestination_id( rs.getString("destination_id") );

                return route;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static List<Route> getOriginRoutes(String origin) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("""
                    SELECT *
                    FROM route
                    WHERE origin_id=%s
                    """, origin));

            List<Route> routes = new ArrayList<>();

            while(rs.next()) {
                Route route = new Route();
                route.setId( rs.getInt("id") );
                route.setOrigin_id( rs.getString("origin_id") );
                route.setDestination_id( rs.getString("destination_id") );

                routes.add(route);
            }

            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Route> getDestRoutes(String destination) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    SELECT *
                    FROM route
                    WHERE destination_id = ?
                    """);
            preparedStatement.setString(1, destination);
            ResultSet rs = preparedStatement.executeQuery();

            List<Route> routes = new ArrayList<>();
            while(rs.next()) {
                Route route = new Route();
                route.setId( rs.getInt("id") );
                route.setOrigin_id( rs.getString("origin_id") );
                route.setDestination_id( rs.getString("destination_id") );

                routes.add(route);
            }
            return routes;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<Route> getAllRoutes() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *
                    FROM route
                    ORDER BY id
                    """);

            List<Route> routes = new ArrayList<>();
            while(rs.next()) {
                Route route = new Route();
                route.setId( rs.getInt("id") );
                route.setOrigin_id( rs.getString("origin_id") );
                route.setDestination_id( rs.getString("destination_id") );

                routes.add(route);
            }
            return routes;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean routeInsert(Route route) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    INSERT INTO route
                    VALUES(?, ?, ?)
                    """);
            preparedStatement.setInt(1, route.getId());
            preparedStatement.setString(2, route.getOrigin_id());
            preparedStatement.setString(3, route.getDestination_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeUpdate(Route route) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    UPDATE route
                    SET origin_id = ?, destination_id = ?
                    WHERE id = ?
                    """);
            preparedStatement.setString(1, route.getOrigin_id());
            preparedStatement.setString(2, route.getDestination_id());
            preparedStatement.setInt(3, route.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeDelete(Route route) {
        Integer route_id = route.getId();
        Connection conn = ConnectionFactory.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("""
                    DELETE
                    FROM route
                    WHERE id=%d""",
                    route_id
            ));
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeDelete(Integer route_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM route
                    WHERE id = ?
                    """);
            preparedStatement.setInt(1, route_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean routeDeleteByIata(String iata_id) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("""
                    DELETE
                    FROM route
                    WHERE origin_id = ?
                        OR destination_id = ?
                    """);
            preparedStatement.setString(1, iata_id);
            preparedStatement.setString(2, iata_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
