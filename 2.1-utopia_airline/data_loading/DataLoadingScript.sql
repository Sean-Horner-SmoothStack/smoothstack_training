LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/airplane_types.csv'
    INTO TABLE airplane_type
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/airplanes.csv'
    INTO TABLE airplane
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/airports.csv'
    INTO TABLE airport
    FIELDS TERMINATED BY '\t'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/bookings.csv'
    INTO TABLE booking
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/booking_agents.csv'
    INTO TABLE booking_agent
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/booking_guests.csv'
    INTO TABLE booking_guest
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/booking_payments.csv'
    INTO TABLE booking_payment
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/booking_users.csv'
    INTO TABLE booking_user
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/flights.csv'
    INTO TABLE flight
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/flight_bookings.csv'
    INTO TABLE flight_bookings
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/passengers.csv'
    INTO TABLE passenger
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/routes.csv'
    INTO TABLE route
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/users.csv'
    INTO TABLE user
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

LOAD DATA
	LOCAL INFILE 'D:/programming_projects/smoothstack_training/utopia_airline/data_loading/user_roles.csv'
    INTO TABLE user_role
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;