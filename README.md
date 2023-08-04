# Customer Reward Program

# Introduction
The Customer Reward Program is a simple SpringBoot based repository that allows businesses to implement a customer loyalty program based on recorded purchases. This system enables businesses to reward their customers with points for each purchase made, encouraging repeat business and fostering customer loyalty.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar
spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

# Requirements
To run this application, you will need the following:

1. Java Development Kit (JDK) 17 or higher
2. Apache Maven
3. Internet connection to download dependencies

# Technology Stack
1. SpringBoot - 3.1.2
2. Junit - 5
3. OpenJDK - 17
4. OpenAPI for Swagger page - 2.1.0

# Getting Started
Follow these steps to get the application up and running on your local machine:
1. Clone the repository: git clone https://github.com/neerajkmsharma/customer-reward-program.git
2. Navigate to the project directory: cd customer-reward-program
4. Build the project using Maven: mvn clean install
5. Run the application: mvn customer-reward-program:run

The application should now be accessible at http://localhost:9090/swagger-ui/index.html

API end point - http://localhost:9090/customer-reward-program/api/v1/{customerId}/reward-points

# Application Structure
1. The application follows a standard SpringBoot project structure:

   ![image](https://github.com/neerajkmsharma/search-sort-algorithm/assets/78490716/4ee47a56-0fd5-48e5-bc87-e70d73721307)

2. Swagger page

    ![image](https://github.com/neerajkmsharma/search-sort-algorithm/assets/78490716/5ab4a507-6457-4012-abaf-e237abd8b96c)

3. H2 Database screenshot
   A. Table CUSTOMER_DETAIL
      ![image](https://github.com/neerajkmsharma/search-sort-algorithm/assets/78490716/87d22661-9978-4347-80fa-e1379f8453cf)
   B. TRANSACTION_DETAIL
      ![image](https://github.com/neerajkmsharma/search-sort-algorithm/assets/78490716/971a2c62-9810-4010-adeb-f88f7db671dd)

1. src/main/java: Contains all Java source code, including controllers, models, repositories, services and etc.
2. src/main/resources: Contains application yaml.
3. src/test/java: Contains test classes for unit and integration testing.
4. pom.xml: The Maven configuration file for managing project dependencies.

# Features
The Customer Reward Program application showcases the following features:
1. RESTful API endpoints for basic CRUD operations on entities.
2. Dependency injection using Spring's @Autowired annotation.
3. Persistence using Spring Data JPA with an embedded H2 database (can be easily switched to other databases).
4. Custom exception handling using @RestControllerAdvice.
5. Enabled Swagger page with OpenAPI

# Configuration
The application's default configuration can be found in the application.yaml file under the src/main/resources directory. Customize the properties as per your needs

# License
None

# Contact
Neeraj Kumar Sharma
