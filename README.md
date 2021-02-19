# RSEG0127_Spring2021

GitLab project for library system class project

## 2021-02-18
To run the application locally...

1. Install MariaDB
2. Run SQL files in this order:
    1. ddl.sql
    2. schema.sql
    3. initial-data.sql
3. `mvn clean install`
4. `mvn spring-boot:run`
5. Navigate to `localhost:8080` in a browser
