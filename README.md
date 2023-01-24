# PhonebookApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.1.1.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

*******Project Tools/Setup*******
Back-end Development: 
- Java v19 + Spring Boot v3.0.1
- Intellij IDE
- Project generated through Spring Initializr
- Junit 5 tests
- Maven v4.0.0 Plugin

Database:
- PostGres (apiBaseUrl: 'http://localhost:8080/api/v1')

Front-end Development:
- Angular/TypeScript/CLI
- HTML/CSS/JavaScript
- MS Studio Code 

Project Approach:
Starting this project my prior knowledge of Web-Development was working with mySQL, HTML/CSS, and PHP. This gave me a general idea of how to approach creating a Front-End webpage, connecting to a database, and sending/receiving data. My disadvantages were that I was unfamiliar with all the specific tools required to complete this project. After researching the fundamentals of using Java, Spring Boot, and Angular online through videos and intro docs, I started building a Backend beginning with creating my project, Phonebook-app, and making a model, Person Class, that contained all the needed variables for a single entry object.
**Backend**
Model: Class Object Person
- Constructors, Setters, Getters
- Constraints for variables added for FirstName, Email, Phone
- Represent object as string for printing
- Boolean nameIsValid() used for UnitTest
Repository Interface: Extend CRUD Services
- JPA Repo utilized for built-in ease-of-use functions 
Service: Service Layer 
- @Transactional Annotation to declaratively control transaction boundaries on Dependency Injection managed beans
- Dependency Injection from Repository
-Inherits methods from JPA Repo to save, delete, & find CRUD (Get, Post, Delete, Put)
- Send/receive CRUD Operations requests between Database Layer & API Layer
Exception:
-Handles error output for service class
Controller: API Layer
- JPA Persistence
- @RestController Annotation adds restful web services needed to handle the requests made by the client
- Dependency Injection from PersonService
- Verified Localhost connection: localhost:8080/api/v1/person
- Send/receives CRUD Operations requests between Service Layer & Client (HTTP Protocol used to communicate to the client side)     
Testing:
 - UnitTest with Junit 5 - firstName constraint & CRUD Operations
 - Used Postman to test Get/Post entry into DB
 
CORS: (Cross-Origin Resource Sharing) was added to allow Frontend to access Backend domain

**Database**
DB: PostGres
- Connected Backend through app properties
- Url: Localhost:5432/person

**Frontend**
Model:
- Response Object Type
- Marks datatypes to return to Backend
Service:
- Made to be injectable into Component and API URL imported through environment file
- Send/receives CRUD Operations requests between Component & Backend Controller (HTTP Protocol used to communicate to Controller)
Component:
- Bind Person data model
- Dependency Injection from Service
- Utilizing Observables for passing messages between parts of UI and Service
- CRUD Operations use subscribe to listen to and handle console logs
- onOpenModal() 
* Directs update and delete button clicks to their UI section
* From there the HTML form or pop-up request loads and finalizes the request on click to their specific functional component (Example: onDeletePerson)
* Originally included add (See Issues)
User Interface:
- HTML/CSS/JS imported from https://bootdey.com/snippets/view/bs4-contact-cards
- Features a Navigation Bar where you can add an entry, and shows contact cards as a single viewable entry which I utilized to show individual Phonebook entries
- Utilizes Bootstrap 4, NgModal for individual entries, and NgForm for the pop-up form


*****ISSUES*****

onOpenModel null error & NgModal form popup add entry CRUD operation not responding on click:
    To get all CRUD operations to work I placed all inputs into the function onOpenModal which would place them into their correct functions. I initially had issues with an error with my onOpenModel when receiving a null when it expects to receive an instance of my Person model, but found some workaround by using "!" to tell the compiler that I understand that the model is null and "?" to flag it as optional so it would allow me to pass it though. This issue arises when I try to add an entry since the initial Person model would be empty at this point until I enter the form and submit it. After looking into other causes such as version differences with angular and bootstrap, checking syntax, using the HTML debugger, and verifying my logic, I decided to simplify the add entry feature and isolate it. This quickly resolved the issue.

Spring-boot-starter-validation not found after adding to pom.xml:
    After trying other dependencies for validation I stumbled upon information say to add the dependency, right-click, go to "Maven", and Reload the project. 

DB Backend Access:
    Mistakenly thought the Database was receiving the wrong names when creating the elements in Person table. (Example: firstName --On DB--> first_name)
    I believed this to be the issue with testing requests to DB, but quickly realized my mistake as it just outputs that way on the DB. The issue ultimately was a syntax issue when using Postman.
