💇‍♀️ Beauty Salon Web Application
A full-stack Java web application built with Spring Boot and Spring MVC, designed to manage the daily operations of a beauty salon. This platform allows clients to explore services and book appointments, while providing administrators with tools to manage staff, services, and schedules.

🚀 Key Features
User Authentication & Authorization:

Secure registration and login.

Role-based access control (e.g., Client, Employee/Stylist, Admin).

Service Management:

Categorized salon services (e.g., Hair styling, Manicure, Makeup, Massages).

Dynamic pricing and duration details for each service.

Appointment Booking System:

Clients can book available time slots with their preferred stylists.

Validation to prevent double-booking or booking in the past.

Admin/Stylist Dashboard:

Staff can view their daily schedule and manage upcoming appointments.

Admins can add, edit, or remove services and monitor overall business activity.

🛠️ Technical Stack
Backend: Java 11/17+, Spring Boot

Data Access: Spring Data JPA, Hibernate

Database: MySQL / PostgreSQL (Configurable)

Build Tool: Maven (pom.xml)

Frontend Template Engine: Thymeleaf, HTML5, CSS3, JavaScript

Security: Spring Security / Custom Session Interceptors

🏗️ Architecture
The project is structured using the standard Layered Architecture to ensure clean separation of concerns:

Controllers (Web Layer): Handles HTTP requests, manages form submissions, and serves Thymeleaf templates.

Services (Business Logic Layer): Contains the core logic, such as checking appointment availability and processing bookings.

Repositories (Data Layer): Interfaces extending JpaRepository for seamless communication with the database.

Models (Domain Layer):

Entities: Map directly to database tables (e.g., User, Appointment, Service, Role).

DTOs / Binding Models: Used for secure data transfer and input validation.

🔧 Setup & Local Development
Clone the repository:

Bash
git clone https://github.com/Aleks29920200/BeautySalon.git
cd BeautySalon
Database Configuration:

Create a database (e.g., beauty_salon_db) in your SQL server.

Open src/main/resources/application.properties (or .yml).

Update the spring.datasource.url, username, and password with your local database credentials.

Build and Run the Application:

You can use the included Maven wrapper to build and run the project without needing Maven installed globally:

Bash
# On Windows
mvnw.cmd spring-boot:run

# On Mac/Linux
./mvnw spring-boot:run
Access the application:

Once the server starts, open your web browser and navigate to http://localhost:8080.

📋 Data Validation constraints
Passwords: Must meet security criteria (hashed before saving to the DB).

Appointments: Date and time must be in the future and fall within salon working hours.

Services: Names must be valid strings; prices and durations must be positive values.
