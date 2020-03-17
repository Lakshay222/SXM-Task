# Create Vehicle

## Prerequisites
```bash
JDK 1.8 or higher
Maven
```

## To Run the Application
```bash
$ mvn clean (Command to rebuild target directory into which Maven normally builds your project.)
$ mvn spring-boot:run (The "run" goal can be used to quickly compile and run application.)
$ mvn javadoc:javadoc ( Command to generate our Javadocs to a directory in target\site:)
```
## To Run the test cases
```bash
$ mvn clean
$ mvn test
```

## Brief

This application demonstrate implementation of asynchronous "Create Vehicle" REST API endpoint using Springboot. Error handlings and validations has been done for the exceptional cases. Log messages are included for the input and output verifications of the method.

### Request

```bash
Enpoint URL: localhost:8080/vehicle-api/v1/vehicles/vehicle
Request Method: POST
Content-Type: application/json
Request Body: {
  "vin": "1A4AABBC5KD501999",
  "year": 2019,
  "make": "FCA",
  "model": "RAM"
  "transmissionType": "MANUAL",
}
```
### Response
```bash
{
    "vin": "1A4AABBC5KD501999"
}
```

## Access Java Documentation

Go to: Project Directory --> target --> site --> apidocs --> com --> index.html

Note - Open index.html file in browser.

## Scenarios Considered

 * Status code 200 - Ok for valid responses.
 * Status code 400 - Bad Request if Transmission Type is neither "AUTO" nor "MANUAL" ignoring the cases.
 * Status code 500 - Internal Server Error if any field in the request body is null, empty or "null".