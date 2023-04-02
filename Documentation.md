<div align="justify">

# Events Application
The project consists of an application that virtually hosts the events that exist at the country level. By means of the application, users are informed of the existence of these events in which they can participate.
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
- can add events he is interested in to a Favorites section.
- can purchase tickets to the event, and depending on the event there may be several types of tickets to choose from.
- at some events, the user must choose the place among the available ones or it will be randomly generated.
- depending on the previous events in which the user participated, certain events are suggested to him when he logs into the application.

#### _Organizer_
- add the event he organizes in the application with the possibility of having different types of tickets.
- has a section where he can see and edit the organized events, but also how many tickets were sold for each event.

#### _Admin_
- create accounts for the organizers as a result of an agreement between the two.
- delete organizer accounts if the agreement between the two is no longer valid.
- has access to details about organizers and events.
- can generate certain reports.

## Database diagram
![Database Diagram](http://www.plantuml.com/plantuml/svg/hPJ1Rjiy3CVlVWgs7_2X1bOK6z2XC0oZfrrstTsGRSOMOek6HAVnbhpxB3VIuaJ5ac2-cHH__9CYzMfOQ1wwDhghS2KLGgzb9_IOht5ysCQG5bbQKaiw-QzuUVdwUVhzVt9bwTDipRRfKv7vu1LfbBKw1Bj0CN-dWJw8HT6YpOMA9O-mXJI5Xn-VuAsHZ35aMHJTSOCTGjyAgIAc7fYW86Rge98QIvQa8hEMgXTQTD1EN1vx1DBSK1IzfoXeg1lEMPPhP1F3hmXWKQ-iuWGAMKl5asjHZQMzHrRvqR59whVlMNPwUZ2emyTi2IKcO5Fi3xfmW-fQFOresmk4aAw5aZy_L5CAnfhqcC16wB8H-k7BytDiFTNa2LZHb5hsjjtdpeO6VMvLeZi5xTsDj5GAoxgW-036wgT1yHYmov1LL9L6Om8QAJSesFY4xcfy_wwy7b7Eoa9TTsXDBTvMjoFSDCYumQMkluo1D7Cb2LGqJ4cdD5XA8qaTSfOOS7c9yo8Scd6bKDkVIUUyAbYZZe_65ZiXsuj6pUOJnhoHhfQUXSH_Txj-ziAJ6jwpd5Pv8JKtvtkoVBDW_dQxnyVT3fpj_4lWVlbU9RAw3wBsCmdc7W24KTRf67PwbYazm-zoY66VgmiOVzOjmF1E1mgSThs1nU2TW0vcXmdqqUa9IVKOw_IGG3u7iOFyONEjyvZX3zvUpokgQkZQtm00)

## Implementation
### Security
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

</div>