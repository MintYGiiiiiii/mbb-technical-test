
# Backend Technical Test

This repository contains a Spring Boot application with various functionalities related to getting random quotes from an third-party API.

## Getting Started

### Prerequisites

- JDK 17 & above
- Apache Maven 3.8.4 or above
- MSSQL Database
- Your preferable IDE (I'm using Eclipe IDE)

### How to Run

1. After cloning this repo, import to your IDE.
2. Right click on the project, select `Run As` -> `Maven build`. In the configuration, set the Goals as `clean install`.
3. Right click on `Application.java`, select `Run As` -> `Java application`. 

### API List
1. #### Get Random Quote
   - Method: `POST`
   - URL: `api/v1/random`
   - Query Parameters:
     - `limit` (Required: `false`) - The number of random quotes to retrieve.
   - Description: Get one or more random quotes from third-party API and store into the database.
2. #### Get Quote History 
   - Method: `GET`
   - URL: `/api/v1/getQuoteHistory`
   - Query Parameters:
     - `page` (Required: `false`) - The page no of quote history to query.
   - Description: Get a list quote history with each page consisting of 10 records from the database.
3. #### Update Quote
   - Method: `PATCH`
   - URL: `/api/v1/updateQuote/{id}`
   - Query Variable:
     - `id` - The unique id of each quote records.
   - Request Body:
     - `{"quote": "your quote"}` - New quote to be updated into the database.
   - Description: Update quote record based on unique id.

### This repo contains

- Package `com.main`:
    - `Application.java`: Main class to launch this application.

- Package `com.main.controller`:
    - `MainController.java`: Controller class

- Package `com.main.dao`:
    - `QuoteDAO.java`: Interface to access Quotes data.

- Package `com.main.dto`:
    - `QuoteDTO.java`: DTO for quote infomations.
    - `QuoteResponseDTO.java`: Response from third-party quote API.

- Package `com.main.entity`:
    - `Quotes.java`: Model for quote.

- Package `com.main.exception`:
    - `MainExceptionControllerAdvice.java`: Exception handling for the application.

- Package `com.main.service`:
    - `QuoteService.java`: Service interface for quote-related actions.
    - `QuoteServiceImpl.java`: Implementation of the Reprvice interface.

- Package `com.main.util`:
    - `CommonUtil.java`: Common utility class to be shared across.
    - `JsonUtil.java`: Utility class to handle JSON.
    - `RestfulrequestSender.java`: Utility class to send Restful request.

- File `application.yml`: Spring Boot application configuration file.
- File `log4j2.xml`: Log4j configuration file.

### Database
- Database Name: `TESTDB`
- Table Name: `quotes`


