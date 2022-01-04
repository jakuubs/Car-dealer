# Car Dealer

Car dealer page made for Programming classes on Adam Mickiewicz University in Poznań. You can check available cars, customers and sales data.
You can add your own car models to database. Backend uses REST API for sending data.

## Project Preview

![Car dealer](/car-dealer.png?raw=true "Car-dealer")

## Installation and setup

You will need `node` and `npm` installed globally on your machine. Backend uses `MySQL`. To visit app go to `http://localhost:4200/`.
Backend is available under http://localhost:8080/. You can also check API documentation under http://localhost:8080/swagger-ui/.

- Backend:
  - clone down this repository
  - create MySQL schema with name `cars`
  - run main method of CarDealerApplication class

- Frontend:
  - clone down this repository
  - run `npm install`
  - run `npm start`

## Technologies and tools

- Backend:
    * Java 11
    * Spring Boot
	* Hibernate
	* JodaTime
	* Jackson
	* Swagger
- Frontend:
    * HTML5
    * CSS3
    * Angular
    * Bootstrap