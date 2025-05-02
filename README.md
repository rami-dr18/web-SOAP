Matrix Calculator Web Service
Overview
This project implements a SOAP-based web service for matrix operations (addition, subtraction, multiplication, transpose, determinant) using JAX-WS and JDK 21. The service is published programmatically using javax.xml.ws.Endpoint.
Setup

Ensure JDK 21 and Maven are installed.
Clone the repository and navigate to the project directory.
Run mvn clean install to build the project.

Running the Server

Run com.example.server.MatrixServer to start the service.
Access the WSDL at http://localhost:8080/services/MatrixService?wsdl.

Testing

SoapUI:
Download SoapUI (https://www.soapui.org/).
Import the WSDL and test operations (add, subtract, multiply, transpose, determinant).


Java Client:
Run com.example.client.MatrixClient to test programmatically.


Unit Tests:
Run mvn test to execute unit tests.



Deliverables

Source Files: src/main/java/com/example/
WSDL: MatrixService.wsdl
Configuration: pom.xml
Documentation: This README

Notes

The project uses Endpoint.publish for simplicity, eliminating the need for web.xml or cxf-servlet.xml.
Submit the project as a zip file before the course deadline.

