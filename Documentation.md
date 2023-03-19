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
![Database Diagram](https://www.planttext.com/api/plantuml/svg/hLPDRzim3BtxLx0TCCJ0Yc83EXI6OVIqowwx6xB5s49iCv3aDDdI_pv9iHr_fabHtIoIZu-F8k4NfPdKTLbuTnpNeaBOIb4oUOWtU5ZjVGYJA4of81byIPwVVtutdr-DAGeVtMsq3n-Jg0jUaqIHLKrm6yZS6GTsGPeb1TfSM6cX3x27C8JLds_mcWkDFaIHvztf74j3hc2YyZHjGHCaf3MLm8N4L0kgFBKHrPP9La9XSFLXYq1Iv-HvfnG9rLckOotI3MhbmpyFO52lu7u0YSX4fQsfNur5EqNokETcgFxwRGYEl6k5Si7Db91Y03R5MyCBTwRo6_dah2Yk848oOvNukyWcPHepaeS0TaocEPEh7qzFlhLmOvcvHPLAiRLoJuqvrN9wMx5o3DGMlcOxagR7yK21Fl--6gsexzNSZt70iq4j1ola5TeppYKgDLCUnGgPLfrp2zn9VsDdMPfIVIAzn5bIWLUWNb3-GpFGYTbtkF22X7AL1C2fDabD6ao5ifDwocddO6BgABfsDsBSIDJgOtYwASCCfTzxALWoKLprIcq-fjGhIRwWegNuUTsrOfhzDsNX8XP6DAPrd0YpvIOKHyC5zhmzF1oFHeFppUW4SDzsEH3y_X88kEeNG9fPTHZ3jNjoEHuToE1kZ35SkOjG3Pr5DcCwSyKt66uNB64q6_SzZFFOps3qrx43clDkj87108o0pi_8mSpfU7Elv_8r86xXB0dJJbkBCNRyG3qlM77pK_8V)


