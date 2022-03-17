# Capstone - Master of Software Engineering
## January 13th, 2021 - March 23rd, 2021

This was a 10-week course. Our assignment was to create a Library Management System. Users would be able to log in and reserve books. Librarians would log in to add new titles, and manage copies of books.

The group assignment was meant for 4-6 students. Due to low enrollment, it was only me and one other student. Because of this, we had to manage expectations regarding which features would be implemented. Some features were pushed to a theoretical future sprint. This was presented to the instructor along with the final application.

### Technologies used
#### Back end
* [Java 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
* [Project Lombok](https://projectlombok.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [MariaDB](https://mariadb.com/)
* [Swagger](https://swagger.io/)

#### Front end
* [Mustache templating](https://mustache.github.io/)
* [HTML5](https://www.w3.org/TR/html52/)
* [JavaScript ES6](https://262.ecma-international.org/6.0/)
* [jQuery](https://jquery.com/)
* [CSS](https://www.w3.org/Style/CSS/Overview.en.html)

---
## Features
* Multiple user types:
  * Administrator
  * Librarian
  * Patron

* Admin tools for library management:
  * Add new books
  * Manage multiple copies of books
  * Create new users
  * View all current loans and status
  * Swagger documentation for library API

* Patron self-serve tools:
  * Filter book list
  * Reserve copies
  * Check out copies
  * Return copies

* Asset status:
  * Reserving an item makes it immediately unavailable to other users
  * Hourly check for loaned item status
  * Remove reserved items from cart after an hour

---
## Running the application
Prerequisites:
* Apache Maven
* JDK 8
* MariaDB

Default settings:
* Database is `localhost:3306`
* Web server is `localhost:8888`

These settings can be modified in `app/src/main/resources/application.properties`.

### Create the database
* Using the `.sql` files in `app/src/main/resources/sql` directory:
  * Import `ddl.sql`
  * Import `schema.sql`
  * Import `initial-data.sql`

### Install and run the application
* `cd app`
* `mvn install`
* `./mvnw spring-boot:run`

## It's running - now what?
* point your browser at http://localhost:8888
* try to log in as the default administrator: `admin` / `password`
* go to "Assets", click books, add copies
* go to "Browse", click books, reserve copies
* go to "Bookbag", and "Check out"
* go to "Admin Tools > All Loans", and see your loans
* go to "Loans" and return items
* go to "Admin Tools > Users" and create new users
* go to "Admin Tools > Swagger" to explore the API
* try other user types if you like:
  * `librarian` / `password`
  * `patron` / `password`

---
## Troubleshooting
### I'm getting errors like this: 
```
Exception calling "DownloadFile" with "2" argument(s): "An exception occurred during a WebClient request."

Error: Could not find or load main class org.apache.maven.wrapper.MavenWrapperMain
```
Try generating a new Maven wrapper.
* delete `app/.mvn`
* run `mvn -N wrapper:wrapper`
* in the `app` directory, run `./mvnw spring-boot:run`

---
### I want to use MySQL
You'll need to update the following:
* MariaDB dependency in `pom.xml` (lines 36-39)
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
* `spring.datasource.url` and `datasource.driver-class-name` in `application.properties` (lines 12-13)
```ini
spring.datasource.url=jdbc:mysql://localhost:3306/lms
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
You may also need to update the tables as follows:
```sql
alter table user modify creation_date timestamp default current_timestamp;
alter table asset_copy modify due_date timestamp default current_timestamp;
alter table audit modify action_date timestamp default current_timestamp;
```
Rebuild with `mvn clean install` and try running it!

---
### Random Thing isn't working
This was a school project, so it is probably super buggy. Sorry!