In order to learn more about spring security. How to set up jwt authentication and basic authentication. I passionatly created spring-security-jwt-authentication-with-docker project 
where you can register and login with users using jwt authentication, using docker-compose, postgres db, flyway for database migration, testcontainer to automaticly create database for integration tests.

To run the project follow these steps:
1- create a .env file you can copy the env.example file
2- run the command docker-compose up db
3- run the command docker-compose up jwt-authentication (run this after the database is up and running)
4- use postman collection "apps/jwt-authentication-service/ilyes-zouaoui-jwt-authentication.postman_collection.json" to interact with the application

notes:
- the sql script V1.1__InserAdminInUsersTable.sql, create a user with email 'admin@email.test' and password 'admin' and role 'ROLE_ADMIN'.
- for the endpoints that have 'app' in the url, such us '/api/app/v1/users' they are expected to be called by mobile or web client and they use jwt authentication.
- for the endpoints that have 'internal' in the url, such us '/api/internal/v1/users' they are expected to be called by another microservice and they use basic authentication.
