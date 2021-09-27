package com.utopia_air.menus;

import com.utopia_air.classes.*;
import com.utopia_air.dao.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class MenuObject {

    protected static Scanner input = new Scanner(System.in);

    public static void MainMenu() {
        System.out.print("""
            Welcome to the Utopia Airlines Management System
          
            Which category of user are you:
              1) Employee/Agent
              2) Administrator
              3) Traveler
              
              0) Exit the program
    
            Please enter your selection:\s\s"""
        );
        Integer selection = input.nextInt();

        switch(selection) {
            case 1 -> EmpMenu1();
            case 2 -> AdminBase();
            case 3 -> TravAuth();
        }
    }

    public static void EmpMenu1() {
        System.out.print("""
              Enter the IDs of flights you manage (comma- or space-separated list)
              
              [Exit] Return to the previous menu
            
            Please enter your selection:\s\s"""
        );

        List<String> inputs = Arrays.stream(input.nextLine()
                                                .split(","))
                                                .map(String::trim)
                                                .collect(Collectors.toList());
    }

    public static void EmpMenu2() {
        List<Route> routes = RouteDAO.getAllRoutes();
        assert(routes != null);

        StringBuilder sb = new StringBuilder();
        sb.append("The following routes are available:\n");
        for (int i = 0; i < routes.size() && i <= 10; i++)
            sb.append(String.format("\t%d) %s --> %s\n",
                    i+1,
                    routes.get(i).getOrigin_id(),
                    routes.get(i).getDestination_id()
            ));
        sb.append("""
                     0) Return to previous menu
                
                Please enter your selection:\s\s""");

        System.out.print(sb);
    }

    public static void EmpMenu3() {
        System.out.print("""
              1) View more details about the flight
              2) Update the details of the flight
              3) Add seats to a flight
              
              0) Return to previous menu
            
            Please enter your selection:\s\s""");
    }

    public static void EmpMenu3_1(Flight flight) {
        // Date and time formatter for converting timestamps into human-readable strings
        DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter times = DateTimeFormatter.ofPattern("HH:mm");
        // Pre-creating the departure and arrival time strings for readability
        String departure_date = flight.getDeparture_time().format(dates);
        String departure_time = flight.getDeparture_time().format(times);
        String arrival_date = flight.getArrival_time().format(dates);
        String arrival_time = flight.getArrival_time().format(times);

        // building and printing the formatted string
        System.out.printf("""
            You have chosen to view the following flight:
              Flight ID: %s
              Route ID:  %s
                
              Departure Airport: %s\t|  Arrival Airport:  %s
              Departure Date:    %s\t|  Departure Time:   %s
              Arrival Date:      %s\t|  Arrival Time:     %s
            
            Available seats categorized by class:
              1) First class    -> %d
              2) Business class -> %d
              3) Economy class  -> %d
              
              0) Return to the previous menu
                
            Please enter your selection:\s\s""",
                flight.getFlight_id(),
                flight.getRoute_id(),
                flight.getOrigin_id(), flight.getDestination_id(),
                departure_date, departure_time,
                arrival_date, arrival_time,
                flight.getSeats().get(0),
                flight.getSeats().get(1),
                flight.getSeats().get(2)
        );
    }

    public static void EmpMenu3_2(Flight flight) {
        System.out.printf("""
            You have chosen to update the following flight:
              Flight ID:   %s
              Origin:      %s
              Destination: %s
            
            Please enter your choice:\s\s""",
                flight.getFlight_id(),
                flight.getOrigin_id(),
                flight.getDestination_id()
        );
    }

    public static void EmpMenu3_3(Flight flight) {
        System.out.print("""
            Pick the class of seat you want to add to the flight:
              1) First
              2) Business
              3) Economy
              
              0) Cancel and return to previous menu
            
            Please enter your selection:\s\s""");

        int selection = input.nextInt()-1;

        Integer seats = flight.getSeats().get(selection);

        System.out.printf("""
            Existing number of seats of that class: %d
            
            Please enter a new number of seats:\s\s""", seats);

        int new_number = input.nextInt();

        flight.getSeats().set(selection, new_number);

        System.out.printf(
                "The new number of seats in the chosen section is: %d",
                flight.getSeats().get(selection)
        );
    }

    public static void TravAuth() {
        System.out.print("Please enter your membership number:  ");
        Long mem_num = input.nextLong();
        System.out.printf("You entered %d", mem_num);
        TravMenu1();
    }

    public static void TravMenu1() {
        System.out.print("""
            Which of the following actions would you like to do:
              1) Book a ticket
              2) Cancel an upcoming trip
              0) Return to previous menu
    
            Please enter your selection:\s\s""");
    }

    public static void TravMenu1_1_a(List<String> origins, List<String> destinations) {
        assert(origins.size() == destinations.size());
        StringBuilder sb = new StringBuilder();

        sb.append("Which of the following routes would you like to book:\n");
        // printing loop for listing all available options
        for (int i = 0; i < origins.size() && i <= 10; i++)
            sb.append(String.format("\t%d) %s --> %s\n", i+1, origins.get(i), destinations.get(i)));
        sb.append("""
                    0) Return to previous menu
                    
                    Please enter your selection:\s\s""");

        System.out.print(sb);
    }

    public static void TravMenu1_1_b() {
        System.out.print("""
            Which of the following actions would you like to do:
              1) View flight details
              2) First class
              3) Business class
              4) Economy class
              0) Cancel and return to previous menu
    
            Please enter your selection:\s\s""");
    }

    public static void AdminBase() {
        System.out.print("""
            Which action would you like to perform:
              1) Add/Update/Delete/Read Flights
              2) Add/Update/Delete/Read Seats
              3) Add/Update/Delete/Read Tickets and Passengers
              4) Add/Update/Delete/Read Airports
              5) Add/Update/Delete/Read Travelers
              6) Add/Update/Delete/Read Employees
              7) Over-ride Trip Cancellation for a ticket
              0) Return to previous menu
            
            Please enter your selection:\s\s""");

        int selection = input.nextInt();

        switch (selection) {
            case 1 -> AdminMenu_Flights();
            case 2 -> AdminMenu_Seats();
            case 3 -> AdminMenu_Tickets();
            case 4 -> AdminMenu_Airports();
            case 5 -> AdminMenu_Travelers();
            case 6 -> AdminMenu_Employees();
            case 0 -> MainMenu();
            default -> System.out.println("uh oh");
        }
    }

    public static void AdminMenu_Flights() {

        // Creating a timestamp formatter to give departure dates in a human-readable
        // date and time format.
        DateTimeFormatter day_time = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        List<Flight> flights = FlightDAO.getAllFlights();
        assert(flights != null);

        StringBuilder sb = new StringBuilder();
        sb.append("You've chosen the flights editing menu.");
        sb.append("Which of the following flights would you like to update:\n");
        // for loop to print out all the available flights in the database
        for (int i = 0; i < flights.size() && i <= 10; i++) {
            sb.append(String.format(
                "\t%2d) %s\n",
                    i + 1,
                    flights.get(i).toString()
            ));
        }
        sb.append("""
            11) Add a flight to the list
            12) Next page
            
            0) Return to the previous menu
            
            Please enter your selection:\s\s""");

        System.out.print(sb);

        int selection = input.nextInt();
        int pagination = 0;

        switch(selection) {
            case 0 -> AdminBase();
            case 1 -> Admin_EditFlight(1+pagination);
            case 2 -> Admin_EditFlight(2+pagination);
            case 3 -> Admin_EditFlight(3+pagination);
            case 4 -> Admin_EditFlight(4+pagination);
            case 5 -> Admin_EditFlight(5+pagination);
            case 6 -> Admin_EditFlight(6+pagination);
            case 7 -> Admin_EditFlight(7+pagination);
            case 8 -> Admin_EditFlight(8+pagination);
            case 9 -> Admin_EditFlight(9+pagination);
            case 10 -> Admin_EditFlight(10+pagination);
            case 11 -> Admin_AddFlight();

        }
    }

    private static void Admin_EditFlight(int i) {
        Flight flight = FlightDAO.getAllFlights().get(i);
        Flight_Table sub_flight = flight.getFlightTable();
        Route route = flight.getRoute();
        Airplane airplane = flight.getAirplane();
        System.out.print(String.format("""
                        You have chosen flight the following flight: 
                        %s
                                            
                        What would you like to change?
                            1) Flight ID
                            2) Route ID
                            3) Airplane ID
                            
                            0) Return to previous menu
                                            
                        Please enter your selection:\s\s""",
                flight.toString()));

        Integer selection = input.nextInt();

        System.out.print("What would you like to change the ID to: ");
        Integer new_id = input.nextInt();


        switch(selection) {
            case 1 -> sub_flight.setId(new_id);
            case 2 -> route.setId(new_id);
            case 3 -> airplane.setId(new_id);
            default -> AdminBase();
        }
    }

    private static void AdminMenu_Seats() {
        StringBuilder sb = new StringBuilder();
        sb.append("You've chosen the seats editing menu.");
        sb.append("Which of the following flights' seats would you like to update:\n");

        AdminBase();
    }

    private static void AdminMenu_Tickets() {
        StringBuilder sb = new StringBuilder();
        sb.append("You've chosen the ticket editing menu.");
        sb.append("Which of the following flights' seats would you like to update:\n");

        AdminBase();
    }

    public static void Admin_AddFlight() {
        System.out.print("""
                You've chosen to add a new flight.
                
                To begin, what type of airplane is this?
                The available options are:
                """);

        List<AirplaneType> typesOfPlanes = AirplaneTypeDAO.getAllAirplanes();
        typesOfPlanes.forEach(System.out::println);

        System.out.print("Please enter the Type ID number of the plane you'd like: ");
        Integer plane_type = input.nextInt();

        Integer airplane_id = AirplaneDAO.airplaneInsert(plane_type);

        List<Route> routes = RouteDAO.getAllRoutes();
        routes.forEach(System.out::println);

        System.out.print("Please enter the id of the route this flight will take:  ");
        Integer route_id = input.nextInt();

        System.out.print("Please enter the departure time you would like\n(Format MM-dd-yyyy HH:mm) : ");
        String dateString = input.nextLine();

        Flight flight = new Flight();
        flight.setDeparture_time(LocalDateTime.now());

        AdminBase();
    };

    public static void AdminMenu_Airports() {
        StringBuilder sb = new StringBuilder();
        sb.append("The following airports are available:");

        List<Airport> airports = AirportDAO.getAllAirports();
        airports.forEach(System.out::println);

        sb.append("""
                
                What action would you like to perform?
                    1) Add an airport to the system
                    2) Update an airport in the system
                    3) Delete an airport from the system
                    
                    0) Return to the previous menu
                
                Please enter your selection:\s\s""");

        System.out.print(sb);
        Integer selection = input.nextInt();

        if(selection == 0)
            AdminBase();

        input.nextLine();
        System.out.print("Please enter the IATA id of the airport: ");
        String iata_id = input.nextLine();

        System.out.print("Please enter the city of the airport: ");
        String city = input.nextLine();

        if (selection == 3) {
            AirportDAO.airportDelete(iata_id);
            System.out.println("Successfully deleted airport.");
            AdminBase();
        }

        if(AirportDAO.airportExists(iata_id))
            AirportDAO.airportUpdate(new Airport(iata_id, city));
        else
            AirportDAO.airportInsert(new Airport(iata_id, city));
        System.out.println("Airport successfully added!");

        AdminBase();
    }

    private static void AdminMenu_Travelers() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                You've chosen the traveler administration menu.
                The following people are passengers:
                """);
        List<Passenger> passengers = PassengerDAO.getAllPassengers();
        for (Passenger passenger : passengers)
            sb.append(passenger.toString());
        sb.append("""
                
                What action would you like to take?
                    1) Remove passenger
                    
                    0) Return to previous menu
                
                Please enter your selection:\s\s""");
        System.out.print(sb);

        Integer selection = input.nextInt();
        switch(selection) {
            case 1 -> AdminMenu_RemoveTraveler();
            default -> AdminBase();
        }
    }

    private static void AdminMenu_RemoveTraveler() {
        System.out.println("""
                You've chosen to remove a traveler.
                Please enter the traveler's ID number:\s\s""");
        Integer passenger_id = input.nextInt();
        PassengerDAO.passengerDelete(passenger_id);
    }

    private static void AdminMenu_Employees() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                You've chosen the employee administration menu.
                The following users have the employee role:
                """);
        List<User> employees = UserDAO.getUsersByRoleId(3);
        for (User employee : employees)
            sb.append(employee.toString());
        sb.append("""
                
                What action would you like to take?
                    1) Activate employee
                    2) Deactivate employee
                    3) Remove employee
                    
                    0) Return to previous menu
                
                Please enter your selection:\s\s""");

        System.out.print(sb);

        Integer selection = input.nextInt();
        switch(selection) {
            case 1 -> AdminMenu_EmployeeActivation();
            case 2 -> AdminMenu_EmployeeDeactivation();
            case 3 -> AdminMenu_UserRemoval();
            default -> AdminBase();
        }
    }

    private static void AdminMenu_EmployeeActivation() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                You've chosen to activate a new employee.
                The following users are not yet employees:
                """);
        List<User> employees = UserDAO.getUsersByRoleId(2);
        for (User employee : employees)
            sb.append(employee.toString());
        System.out.print(sb);
        System.out.print("Which employee would you like to activate: ");
        Integer user_id = input.nextInt();

        User update = UserDAO.getUser(user_id);
        update.setRole_id(3);
        update.setEmail("empty");  // getting a char error at @
        UserDAO.updateUser(update);

        System.out.print("Employee activated successfully.");
        AdminBase();
    }

    private static void AdminMenu_EmployeeDeactivation() {
        System.out.print("Which employee would you like to deactivate: ");
        Integer user_id = input.nextInt();

        User update = UserDAO.getUser(user_id);
        update.setRole_id(2);
        update.setEmail("empty");  // getting a char error at @
        UserDAO.updateUser(update);

        System.out.print("Employee deactivated successfully.");
        AdminBase();
    }

    private static void AdminMenu_UserRemoval() {
        System.out.print("Which employee would you like to remove: ");
        Integer user_id = input.nextInt();
        UserDAO.deleteUser(user_id);
        System.out.print("Employee deleted successfully.");
        AdminBase();
    }
}
