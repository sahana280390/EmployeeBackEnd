#EMPLOYEEAPP-BACKEND
This springboot project was created by spring.io.initializer

#REQUIREMENTS:

For building and running the application you need:

    JDK 1.8
    Maven 3+

Please ensure by checking the version by opening the cmd terminal: 
1. java -version
2. mvn -version
 	
#VERSION DETAILS OF THE FRAMEWORKS USED

1. Spring Boot 2.4
2. Hibernate Core 5.4
3. Hibernate Validator 6.1
4. Junit 4.12
5. Mockito 2.15
6. Sprngfox swagger 2.7
7. H2 Database 1.4.2


#STEPS TO RUN THE APPLICATION LOCALLY:

1. clone the repo from the github
   https://github.com/sahana280390/EmployeeBackEnd.git

2. There are several ways to run a Spring Boot application on your local machine. 
   One way is to execute the main method in the com.springboot.employee.EmployeeAppApplication class from your IDE.(YOu can run it as a JAVA application)

3. Alternatively you can open the cmd terminal and locate till the folder path where your project is present
   run command: mvn spring-boot:run
 
4. Once the application is started,open the browser and hit the below url for checking H2 Database
   http://localhost:8080/h2-employeedb
 
5. Once you are able to connect to H2 memory database,please execute the following 2 scripts for Manager and Department Entries:
 
 
insert into departments(department_name) values ('Finance');
insert into departments(department_name) values ('IT');

insert into managers(manager_name) values ('ABC');
insert into managers(manager_name) values ('DEF');

insert into employees(first_name,last_name,email,phone_number,salary,manager_id,department,created_at,hire_date) 
values('nayana','shriyan','sdf@gb.in','123-456-7890',23.14,1,1,CAST(CURRENT_DATE AS TIMESTAMP),CAST(CURRENT_DATE AS TIMESTAMP));
insert into employees(first_name,last_name,email,phone_number,salary,manager_id,department,created_at,hire_date) 
values('Jane','Doe','sdf@gb.in','123-456-7890',23.14,2,1,CAST(CURRENT_DATE AS TIMESTAMP),CAST(CURRENT_DATE AS TIMESTAMP));


6. Once done,hit the http://localhost:8080/api/employees
   This is the REST API call for fetching the employees based on page size.

7. For running the JUNIT test,you can run the com.springboot.employee.EmployeeAppApplicationTests in the test folder as a JUnit Test

#API INTERFACE DOCUMENT

http://localhost:8080/swagger-ui.html

