# ParkingLot API

## Overview

ParkingLot API is a RESTful API designed to manage parking lots, including tracking vehicle entry and exit, calculating parking fees, handling different payment methods, and providing information about available parking spots through a display board.

## Problem Definition

A parking lot is a designated area for parking vehicles, commonly found in shopping malls, sports stadiums, offices, and other popular venues. Each parking lot has a fixed number of parking spots available for different types of vehicles. Parking spots are charged based on the duration a vehicle is parked and the type of vehicle.

The parking process involves issuing a parking ticket to the vehicle upon entry. The parking time is tracked using this ticket. Upon exit, the vehicle can pay the parking fee using an automated exit panel or a parking agent, with available payment methods including card or cash.

## Technologies Used

- Spring Boot
- Spring Data JPA
- Hibernate

## Endpoints

### Parking Ticket Controller

- **PUT** `/api/ticket/finalize-parking`: Finalize parking for a ticket.
- **PUT** `/api/ticket/exit-parking`: Exit parking for a ticket.
- **POST** `/api/ticket/`: Create a new parking ticket.
- **GET** `/api/ticket`: Get all parking tickets.
- **GET** `/api/ticket/{ticketNumber}`: Get a specific parking ticket by ticket number.

### Account Controller

- **PUT** `/api/account/{id}`: Update an account by ID.
- **DELETE** `/api/account/{id}`: Delete an account by ID.
- **POST** `/api/account/`: Create a new account.
- **GET** `/api/account`: Get all accounts.

### Vehicle Controller

- **POST** `/vehicle/`: Create a new vehicle.
- **GET** `/vehicle`: Get all vehicles.

### Payment Controller

- **POST** `/payment/`: Process a payment.

### Parking Spot Controller

- **POST** `/parking-spot/park`: Park a vehicle in a parking spot.
- **POST** `/parking-spot/`: Create a new parking spot.
- **GET** `/parking-spot`: Get all parking spots.

### Parking Rate Controller

- **POST** `/parking-rate/`: Create a new parking rate.
- **GET** `/parking-rate/rates`: Get all parking rates.

### Display Board Controller

- **GET** `/display-board/`: Get information for display board.

## Swagger Documentation
You can access the Swagger API documentation by visiting http://localhost:8080/swagger-ui/index.html#/ in your browser after running the application.

## Usage
Before using the API, make sure to set up the necessary configurations and dependencies. Refer to the provided endpoint documentation for details on each endpoint's functionality and required parameters.

## How To Run
Running the application Locally:

`mvn clean install` 

`mvn spring-boot:run`

## Prerequisites
JDK 8 or higher installed
Maven 3.x installed

## Limitations and Future Improvements
Currently, the application does not include authentication and authorization mechanisms. To enhance security, it is recommended to integrate Spring Security for access control and user authentication.

