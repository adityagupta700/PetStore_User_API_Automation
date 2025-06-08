# PetStore User API Test Automation

This is an API test automation project for the **User** module of the [PetStore application](https://petstore.swagger.io/#/user).

## Overview

This project validates the CRUD operations for user management:

- Create User
- Get User
- Update User
- Delete User

## Tech Stack

| Tool               | Purpose                              |
| ------------------ | ------------------------------------ |
| Java               | Core programming language            |
| TestNG             | Test framework                       |
| RestAssured        | API interaction                      |
| Maven              | Build and dependency management      |
| Log4j              | Logging                              |
| ExtentReports      | Detailed HTML test reporting         |
| Excel (Apache POI) | Source for TestNG DataProvider input |

## Features

- Data-driven testing using **TestNG DataProvider** with Excel input
- Structured logging with **Log4j**
- Clean and interactive test reports via **ExtentReports**
- Full coverage of **User API** operations defined in Swagger documentation

## Usage
To run the project in your local system, you must have Java and Maven installed in the system.
 1. Clone the repo:
    ```bash
    git clone https://github.com/you/MyAwesomeApp.git
    
 2. Run this command in bash:
    ```bash
    mvn test
