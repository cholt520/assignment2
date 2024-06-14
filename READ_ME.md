Book and Author Management Application<br>

This application demonstrates a simple CRUD (Create, Read, Update, Delete) functionality for managing books and authors using Spring Boot with Spring Data JPA. It includes repository interfaces, service classes, entity classes, and unit tests.

Prerequisites<br>
Java 17<br>
Maven 3.6.x or higher<br>
MySQL <br>

Dependencies<br>
Spring Boot Starter Data JPA<br>
MySQL Connector Java<br>
Spring Boot Starter Test<br>
Mockito (for unit testing)<br>

Getting Started
To run this application locally, follow these steps:

Clone the repository:

bash
Copy code
```
git clone https://github.com/cholt520/assignment2.git
cd assignment2
```
Database Configuration:

Configure your MySQL database settings in src/main/resources/application.properties:

properties
Copy code
```
spring.datasource.url=jdbc:mysql://localhost:3306/assignment2
spring.datasource.username=
spring.datasource.password=
```

Run the Application:

You can run the application using Maven. Navigate to the project directory and execute:

bash
Copy code
```
mvn spring-boot:run
```
The application will start at http://localhost:8080.

Testing:

Unit tests are provided for BookService and AuthorService using JUnit and Mockito. To run the tests:

bash
Copy code
mvn test
Usage
Endpoints
The application exposes the following RESTful endpoints:

Books:
```
GET /api/books: Get all books
GET /api/books/{id}: Get book by ID
POST /api/books: Create a new book
PUT /api/books/{id}: Update an existing book
DELETE /api/books/{id}: Delete a book
```
Authors:
```
GET /api/authors: Get all authors
GET /api/authors/{id}: Get author by ID
POST /api/authors: Create a new author
PUT /api/authors/{id}: Update an existing author
DELETE /api/authors/{id}: Delete an author
```
Sample Requests
Get All Books
http
Copy code
GET /api/books
Create a Book
http
Copy code
POST /api/books
Content-Type: application/json
```
{
"title": "Sample Book",
"publicationYear": 2023,
"genre": "Fiction"
}
```
Update a Book
http
Copy code
PUT /api/books/{id}
Content-Type: application/json
```
{
"title": "Updated Book Title",
"publicationYear": 2024,
"genre": "Adventure"
}
```
Delete a Book
http
Copy code
```
DELETE /api/books/{id}
```