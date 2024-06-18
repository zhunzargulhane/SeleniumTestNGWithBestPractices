**Selenium Page Object Model (POM) with Factory Design Pattern, Allure Reports, TestNG, REST Assured, Jackson Databind API, Java Faker, Maven, WebDriverManager, Jsoup**

**Overview**
This project demonstrates a robust test automation framework leveraging Selenium WebDriver with a Page Object Model (POM) design pattern, integrated with Allure Reports for comprehensive test reporting. It also incorporates TestNG for test execution and management, REST Assured for API testing, Jackson Databind API for JSON manipulation, Java Faker for generating test data, Maven for build management, WebDriverManager for easy WebDriver setup, Jsoup for HTML parsing, and various other utilities for efficient test automation in Java. It supports the parallel test execution as well in addition to above features.

**Features**
Page Object Model (POM): Maintainable and scalable test automation architecture.
Factory Design Pattern: Centralized creation of Chromedriver and firefoxdriver instances by maintaining the Single Responsibility principle.
Allure Reports: Detailed and interactive HTML reports with screenshots.
TestNG: Flexible test execution, grouping, parameterization capabilities and parallel execution using testng.xml file.
REST Assured: Validate and test RESTful APIs directly in test automation to setup application state and user state by creating the user through NON UI channel.
Jackson Databind API: JSON (de)serialization for handling API responses.
Java Faker: Generate realistic test data dynamically.
Maven: Dependency management and build automation.
WebDriverManager: Automated chromedriver or firefoxdriver setup and management accordingly to our systems browser version
Jsoup: Parse, manipulate, and clean HTML response coming from the API calls.
Allure TestNG: Integration for enhanced reporting capabilities.
Singleton Design Pattern - To load or intialize the properties file only during the entire execution.
OOPs Concept - Inheritance, Interface, Encapsulation, Polymorphism.

**Requirements**
Java Development Kit (JDK) 8 or higher
Maven
Selenium WebDriver
Allure CLI (optional for generating and viewing Allure reports)


**Setup Instructions**
1. Clone the repository:
        git clone "https://github.com/zhunzargulhane/SeleniumTestNGWithBestPractices.git"

2. Install dependencies:
        Install all the dependencies present in the POM.xml file.
   
3. Run tests using Maven:
    mvn test -Dbrowser=CHROME -Denv="STAGE"
    mvn test -Dbrowser=FIREFOX -Denv="PROD"

4. Generate Allure reports:
        allure serve target/allure-reports


**Project Structure**
.
├── Drivers
├── src
│   ├── test
│   │   ├── java
│   │   │   └── com.selenium.pom
│   │   │           ├── base            # BaseTest and BasePage to handle the prerequisites for test cases and page object classes.
│   │   │           ├── dataprovider    # Data providers for test cases
│   │   │           └── pages           # Reusable page components
|   |   │              ├── components   # Applied composition concept to store the reusable page object definitions and their methods.         
│   │   |           ├── utils           # Utility classes eg: JacksonUtil for deserialization,ConfigLoader,CookieUtils,PropertyUtils,Screenshotutility, JavaFakerUtils.
│   │   │           ├── tests           # Test Cases in place
│   │   │           ├── objects         # POJO classes for Users, BillingAddress, Products
│   │   │           ├── factory         # Implementation of factory design pattern using Interface, Factory method, implementing classes
│   │   │           ├── constants       # Enum constants for DriverName, Endpoints, EnvType
│   │   │           ├── api.actions     # To setup the Application and user state RestAssured API's
|   |   │              ├── apiUtils     # Reusable API request and spec builder to add common prerequisites.
│   │   └── resources
│   │       ├── testng.xml               # TestNG suite configuration
│   │       └── allure-results           # Allure report directory (auto-generated)
│   │       └── allure.properties        # Holds the location of results directory and patterns for links
│   │       └── config.properties        # Holds the baseURL and user information 
│   │       └── TestDataFiles            # TestDataFiles which would be deserialized.
├── pom.xml                             # Maven build configuration
└── README.md                           # This file


**Integrating REST Assured Cookies with Selenium**
Use REST Assured to retrieve cookies from API responses.
Inject retrieved cookies into Selenium WebDriver session for seamless integration.

**Generating Allure Reports with Screenshots**
Allure automatically captures screenshots for failed test cases.
Reports are generated in target/allure-results and can be viewed using Allure CLI.

This README.md provides comprehensive information about setting up, usage of test automation framework.
