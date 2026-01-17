# 📌 Spring Boot + MySQL Project with Docker

This project is a **Java Spring Boot** application integrated with **MySQL**, fully containerized using **Docker**.  
It is designed to run seamlessly with a single command, making setup and execution straightforward.

---

## Technologies
- [Java 17+](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## Project Structure

📁 src/                      # Application source code
📄 pom.xml                   # Maven configuration
📄 Dockerfile                # Application container build
📄 docker-compose.yml        # Service orchestration
📘 README.md                 # Project documentation

## Requirements
 - Docker
 - Docker Compose

## Recommended tools
 - Git (Recommended, but optional, as you can download the project's .zip file. If you are a Linux user some distributions already come with Git installed by default; you can check it using the following command: `git --version`)
 - Postman (It is not required to run the application, but it is recommended for testing. Postman is a tool for testing APIs. You can call the application's endpoints in an easier and more practical way.)

## Installing programs
 - **Docker and Docker Compose** (Both are installed along with Docker Desktop). You can install by following this documentation:
    |- Windows: https://docs.docker.com/desktop/setup/install/windows-install/
    |- Linux: https://docs.docker.com/desktop/setup/install/linux/
 - **Git** (If needed): You can follow this documentation: 
    |- Windows: https://git-scm.com/install/windows
    |- Linux: https://git-scm.com/install/linux
 - **Postman** You can follow this documentation: 
    https://learning.postman.com/docs/getting-started/installation/installation-and-updates/

## Running the application
 - If you are using Windows, open Git Bash in the directory where you want to run the project. If you are using Linux, simply open the terminal in the directory where you want to run the project.
 - Clone the project by using the following command: `git clone git@github.com:KNIGHT-2/SchoolManagement.git`
 - Use the command `cd SchoolManagement` to navigate to the application directory.
 - Use the command `docker compose up` and wait for the application to start correctly.
 - The application will be available at: http://localhost:8080

## Using the Application Here are some example requests you can use to test the API:

 - It's important to remember that the application was built to be accessible only to students and staff of an educational institution. Therefore, the only way to create an account is if another account with administrator privileges does so.

   
   | Method |  Endpoints                                     |    Role   |  Description             | 
   |:------:|:----------------------------------------------:|:---------:|:------------------------:|
   |  POST  |  /login                                        |    None   | This endpoint is used to log in to the application.|
   |  POST  |  /users/register                               |    ADMIN  | This endpoint is used to register a new account in the application.|
   |  POST  |  /frequencies/insertUserIntoDiscipline/{userId}|    ADMIN  | It is used to enroll a student or teacher in a subject.|
   |  POST  |  /frequencies/saveNote/{userId}                |  TEACHER or ADMIN  |  Adds a grade for a student enrolled in the course.|
   |  POST  |  /fee/{userId}                                 |  ADMIN |  Adds a monthly fee to a specific student. |
   |  POST  |  /fee/toAllStudents                            |  ADMIN |  Add a monthly fee for all students. |
   |  GET   |  /fee/{userId}/values                          |  Any   |  Find out the fees a student needs to pay. |
   |  GET   |  /users/{userId}/frequencies                   |  TEACHER or ADMIN  |  Lists all the subjects a student or professor is enrolled in.  |
   |  GET   |  /users                                        |  ADMIN |  Returns all users who are registered in the application. |
   |  GET   |  /frequencies                                  |  Any | Returns all subjects, with grades and absences for students, that are registered in the system.|
   |  GET   |  /frequencies/search?discipline=DISCIPLINE     |  Any   |  Returns grades and absences for students enrolled in a specific subject.|
   |  PUT   |  /users/{userId}                               |  ADMIN |  Changes the user's registration data.  |
   |  PUT   |  /frequencies/saveNote/{userId}                |  ADMIN or TEACHER  |  Changes the grade of a student enrolled in a subject.|
   | DELETE |  /users/{userId}                               |   ADMIN   |   Delete a specific user. |

## Stopping the application (You can run it in another terminal window, or Git Bash window, without problems, as long as it's in the program's directory.)
 - Run the following command to stop the application: `docker compose stop`
 - Run the following command to delete the application container: `docker compose down`
