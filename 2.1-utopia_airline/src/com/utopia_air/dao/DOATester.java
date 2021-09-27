package com.utopia_air.dao;

import com.utopia_air.classes.*;

import java.util.List;

public class DOATester {

    public static void AllRoutesTest() {
        List<Route> routes = RouteDAO.getAllRoutes();

        if(routes != null)
            routes.forEach(System.out::println);
    }

    public static void AllPlanesTest() {
        List<Airplane> airplanes = AirplaneDAO.getAllAirplanes();

        if(airplanes != null)
            airplanes.forEach(System.out::println);
    }

    public static void AllPlaneTypeTest() {
        List<AirplaneType> planes = AirplaneTypeDAO.getAllAirplanes();

        if (planes != null) {
            planes.forEach(System.out::println);
        }
    }

    public static void AllAirportsTest() {
        List<Airport> airports = AirportDAO.getAllAirports();

        if(airports != null)
            airports.forEach(System.out::println);
    }

    public static void AllBookingsTest() {
        List<Booking> bookings = BookingDAO.getAllBookings();

        if(bookings != null)
            bookings.forEach(System.out::println);
    }

    public static void AllRolesTest() {
        List<UserRole> roles = UserRoleDAO.getAllRoles();

        if(roles != null)
            roles.forEach(System.out::println);
    }

    public static void AllAgentsTest() {
        List<BookingAgent> agents = BookingAgentDAO.getAllAgents();

        if (agents != null)
            agents.forEach(System.out::println);
    }

    public static void AllBookingUsersTest() {
        List<BookingUser> booking_users = BookingUserDAO.getAllBookingUsers();

        if (booking_users != null)
            booking_users.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        AllRoutesTest();
//        AllPlanesTest();
//        AllPlaneTypeTest();
//        AllAirportsTest();
//        AllBookingsTest();
//        AllRolesTest();
//        AllAgentsTest();
        AllBookingUsersTest();
    }
}
