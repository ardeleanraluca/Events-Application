<div align="justify">

# Events Application
The project consists of an application that virtually hosts the events that exist at the country level. Through the application, users are informed of the existence of these events in which they can participate.
The application can be used by anyone and all the events present in the application will be visible regardless of whether the user has an account or not, but having an account brings some benefits that will be detailed below.


Therefore, the application accepts 4 types of users:
- **Guest** - can only see the existing events in the application without being registered in the application.
- **Standard User** -  can see the existing events in the application and also reserve one or more places at a certain event by buying tickets.
- **Organizer** - can see the existing events in the application and also add his own event.
- **Admin** - has access to all the information about events and organizers and more than that, the admin is the one who generates accounts for the organizers following an agreement between the admin and the organizer.

## Features
The features are customized according to the user, but there are also more general features.
The feature that is available to all users is to view the events that exist at that time in the application.
The next most general functionality is aimed at users who are **not guests**, namely that of authentication in the application, and as an extension to the authentication functionality is password reset in case the user has forgotten his password.
#### _Guest_
- can register in application.

#### _Standard User_
- can purchase tickets to the event, and depending on the event there may be several types of tickets to choose from.
- at some events, the user must choose the place among the available ones or it will be randomly generated.
- depending on the previous events in which the user participated, certain events are suggested to him when he logs into the application.

#### _Organizer_
- add the event he organizes in the application with the possibility of having different types of tickets.
- has a section where he can see and edit the organized events, but also how many tickets were sold for each event.

#### _Admin_
- create accounts for the organizers as a result of an agreement between the two.
- delete organizer accounts and associated events if the agreement between the two is no longer valid.
- has access to details about organizers and events.
- can generate certain reports.

## Database diagram
![Database Diagram](http://www.plantuml.com/plantuml/svg/hPJ1Rjiy3CVlVWgs7_2X1bOK6z2XC0oZfrrstTsGRSOMOek6HAVnbhpxB3VIuaJ5ac2-cHH__9CYzMfOQ1wwDhghS2KLGgzb9_IOht5ysCQG5bbQKaiw-QzuUVdwUVhzVt9bwTDipRRfKv7vu1LfbBKw1Bj0CN-dWJw8HT6YpOMA9O-mXJI5Xn-VuAsHZ35aMHJTSOCTGjyAgIAc7fYW86Rge98QIvQa8hEMgXTQTD1EN1vx1DBSK1IzfoXeg1lEMPPhP1F3hmXWKQ-iuWGAMKl5asjHZQMzHrRvqR59whVlMNPwUZ2emyTi2IKcO5Fi3xfmW-fQFOresmk4aAw5aZy_L5CAnfhqcC16wB8H-k7BytDiFTNa2LZHb5hsjjtdpeO6VMvLeZi5xTsDj5GAoxgW-036wgT1yHYmov1LL9L6Om8QAJSesFY4xcfy_wwy7b7Eoa9TTsXDBTvMjoFSDCYumQMkluo1D7Cb2LGqJ4cdD5XA8qaTSfOOS7c9yo8Scd6bKDkVIUUyAbYZZe_65ZiXsuj6pUOJnhoHhfQUXSH_Txj-ziAJ6jwpd5Pv8JKtvtkoVBDW_dQxnyVT3fpj_4lWVlbU9RAw3wBsCmdc7W24KTRf67PwbYazm-zoY66VgmiOVzOjmF1E1mgSThs1nU2TW0vcXmdqqUa9IVKOw_IGG3u7iOFyONEjyvZX3zvUpokgQkZQtm00)

## Architecture
The Spring Boot follows a **layered architecture** in which each layer communicates to other layers(Above or below in hierarchical order).
### Spring Boot Layers

The spring boot consists of the following four layers:
- Presentation Layer – Authentication & Json Translation
- Business Layer – Business Logic, Validation & Authorization
- Persistence Layer – Storage Logic
- Database Layer – Actual Database

![img.png](img.png)

#### 1. Presentation Layer

The presentation layer is the top layer of the spring boot architecture. It consists of Views. i.e., the front-end part of the application. It handles the HTTP requests and performs authentication. It is responsible for converting the JSON field’s parameter to Java Objects and vice-versa. Once it performs the authentication of the request it passes it to the next layer. i.e., the business layer.

#### 2. Business Layer

The business layer contains all the business logic. It consists of services classes. It is responsible for validation and authorization.

#### 3. Persistence Layer

The persistence layer contains all the database storage logic. It is responsible for converting business objects to the database row and vice-versa.

#### 4. Database Layer

The database layer contains all the databases such as MySql, MongoDB, etc. This layer can contain multiple databases. It is responsible for performing the CRUD operations.

### Spring Boot Flow Architecture
![img_3.png](img_3.png)
- The Client makes an HTTP request(GET, PUT, POST, etc.)
- The HTTP request is forwarded to the Controller. The controller maps the request. It processes the handles and calls the server logic.
- The business logic is performed in the Service layer. The spring boot performs all the logic over the data of the database which is mapped to the spring boot model class through Java Persistence Library(JPA).
- The JSP page is returned as Response from the controller.

## Implementation
### **Security**
Before starting the actual implementation, security configurations were added, in order to have a secure application. To do that, I integrated the Spring Security in my Spring Boot application, adding the security started maven dependecy, which brings in application the default security configuration. At its core, Spring Security is really just a bunch of servlet filters that help us add authentication and authorization to our web application.

#### Filter Security Chains
The first configuartion of Spring Security is Filter Security Chains.
When we send a http request to our server, it will go through a set of security filter chains. 
It defines which URL paths should be secured and which should not, allowing to access some URL only by a type of users. 

#### The UserDetailsService
This is the main configuration for our application. To load the customer details, we need to implement UserDetailsService interface. It uses spring Security UserDetailsService interface in order to lookup the username, password and Granted Authorities for any user.

#### Autentification Manager and Password Encoder
The next step was to onfigure an authentication manager with the correct password-encoding schema that will be used for credentials verification.
In my application, I choose to use the bcrypt password-hashing algorithm.

#### JSON Web Token (JWT)
The next configuration was to define a new token generator and to do this I used JWT. The generator is used at login.
We verify the provided credentials using the authentication manager, and in case of success, we generate the JWT token and return it as a response header along with the user identity information in the response body.

### **Observer Design Pattern -  Send Mails**
I used the Application Events of the Spring Framework that is an implementation of the Observer pattern for the part of the application that handles sending emails. The Observer pattern serves as a means to exchange information between loosely coupled components.

#### Application Events
The event publisher (subject) publishes an event, while the event listener (observer) only receives the specific event if the event listener listens for that specific type of event.
The application event capability of the Spring framework is synchronous by default. This implies the publisher method blocks until all registered listeners have processed the event.

My application is able to send a "welcome" mail to all users that finish successfully their registration.

#### EmailEvent Class
The EmailEvent is an application event and extends the ApplicationEvent abstract class. The EmailEvent class contains three String properties called "toEmail", "subject", "body" that store the event data.

The ApplicationEvent class is abstract since it doesn’t make sense for generic events to be published directly.

#### EmailSenderService class
The EmailSenderService is a service class that deals with sending emails to users. In its constructor are configured the application properties that enable sending emails, and it also has a method that handles the actual sending of an email. 

In the class where I want to generate a sending of an email I have an ApplicationEventPublisher object that will notify the application with an EventEmail to send mail.

``` applicationEventPublisher.publishEvent(emailEvent);```

The application that is an observer is able to listen through the annotation @EventListener() at the method that call the sendMail method from EmailSenderService.

### **Endpoints**
Controller classes handle the HTTP requests, translates the JSON parameter to object, authenticates the request and transfer it to the service layer.

#### AuthController and AuthService
AuthController and AuthService handle all the requests related to authentication and registration in application.
- ```public ResponseEntity<String> register(@RequestBody StandardUserDto standardUserDto)``` - Registers a standard user and adds it to the database, while creating an account which is also saved to the database.
- ```public ResponseEntity<String> registerOrganizer(OrganizerDto organizerDto)``` - An organizer is registered in application by admin and added to the database, while creating an account which is also saved to the database.
- ```public ResponseEntity<AuthResponse> login(UserLoginDto userLoginDto)``` - This endpoint try to authenticate a user in application. If it succeed a token is generated.


#### UserController and UserService
UserController and UserService handle all the requests related to users, whatever if they are standard users or organizers.
- ```public ResponseEntity<String> update(StandardUserDto newUser, Long id)``` - Updates a user's data in database, if it exists. This method can update both the user account data and the personal data of a standard user.
- ```public ResponseEntity<String> updateOrganizer(OrganizerDto newOrganizer, Long id)``` - Updates an organizer's data in database, if it exists. This method can update both the user account data and the personal data of an organizer.
- ```public ResponseEntity<String> delete(Long id)``` - Deletes a standard user from the database, if it exists. This action also involves the deletion of the associated user account.
- ``` public ResponseEntity<String> deleteOrganizer(Long id)``` - Deletes an organizer from the database, if it exists. This action also involves the deletion of the associated user account and only an admin is able to do this.
- ```public ResponseEntity<List<StandardUserEntity>> getAllStandardUsers()``` - Finds all standard user in database and returns them.

#### EventController and EventService
EventController and EventService handle all the requests related to events.
- ```public ResponseEntity<String> createEvent(EventDto eventDto)``` - Create an event and adds it into database. This action also involves the creation of tickets associated with the event and can be made only by organizers.
- ```public ResponseEntity<String> updateEvent(EventDto eventDto, Long id)``` - Update an event's data in database, if it exists. This action can be made only by organizers.
- ``` public ResponseEntity<String> deleteEvent(Long id)``` - Deletes an event from the database, if it exists.  This action also involves the deletion of the associated tickets. 
- ``` public ResponseEntity<List<EventEntity>> getEventsByCity(String city)``` - Finds all events from a city in database and returns them.
  
#### LocationController and LocationService
LocationController and LocationService handle all the requests related to events'location.
- ```public ResponseEntity<String> createLocation(LocationDto locationDto)``` - Creates a location for an event and adds it into database, if that location does no already exist in that city.
- ``` public ResponseEntity<String> deleteLocation(Long id)``` - Deletes a location from the database, if it exists and if it is not assigned to any event.

A location should not be editable for security reasons. Once created, it cannot be modified to avoid any mistakes that may affect the event.
</div>