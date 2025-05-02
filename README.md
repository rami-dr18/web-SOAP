# Matrix Calculator Web Service

## Overview

This project implements a SOAP-based web service for matrix operations, including addition, subtraction, multiplication, transpose, and determinant calculations. Developed using Java with JDK 21 and the Jakarta JAX-WS API, the service is published programmatically using `javax.xml.ws.Endpoint`. The project meets all course requirements, providing a robust, error-handled, and well-documented solution testable locally via SoapUI or a Java client.

## Prerequisites

- **JDK 21**: Install from Oracle or use an SDK manager like SDKMAN!.
- **Maven**: Install from Apache Maven.
- **SoapUI**: Optional, for testing the SOAP service. Download from SoapUI.
- **Git**: For cloning the repository (optional).

## Project Setup

### 1. Create the Project

To create the Maven project from scratch, run the following command in your terminal:

```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=MatrixCalculator -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

This creates a project named `MatrixCalculator` in the `com.example` package.

### 2. Clone the Repository (Alternative)

If using a GitHub repository, clone it:

```bash
git clone https://github.com/your-username/MatrixCalculator.git
cd MatrixCalculator
```

### 3. Configure the Project

Replace the generated `pom.xml` with the project’s `pom.xml` (provided in the repository or source files). Key dependencies include:

- Jakarta JAX-WS (`jakarta.xml.ws-api`, `jaxws-rt`)
- JUnit 5 (`junit-jupiter`)

To verify dependencies, run:

```bash
mvn dependency:tree
```

### 4. Build the Project

Navigate to the project directory and build the project:

```bash
mvn clean install
```

This compiles the code, runs tests, and packages the project.

## Project Structure

```
MatrixCalculator/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── model/
│   │   │   │   └── Matrix.java
│   │   │   ├── service/
│   │   │   │   ├── MatrixService.java
│   │   │   │   └── MatrixServiceImpl.java
│   │   │   ├── exception/
│   │   │   │   └── MatrixOperationException.java
│   │   │   └── server/
│   │   │       └── MatrixServer.java
│   │   ├── resources/
│   └── test/
│       └── java/com/example/
│           └── service/
│               └── MatrixServiceTest.java
├── pom.xml
├── README.md
```

## Running the Server

1. Ensure the project is built (`mvn clean install`).
2. Run the server using the `MatrixServer` class:

   ```bash
   mvn exec:java -Dexec.mainClass="com.example.server.MatrixServer"
   ```

   Alternatively, run directly in your IDE by executing `MatrixServer.java`.
3. The service will be available at:

   ```
   http://localhost:8080/services/MatrixService
   ```
4. Access the WSDL at:

   ```
   http://localhost:8080/services/MatrixService?wsdl
   ```

## Testing the Service

### 1. Soap UI

1. Download and install SoapUI from SoapUI.
2. Create a new SOAP project and import the WSDL from:

   ```
   http://localhost:8080/services/MatrixService?wsdl
   ```
3. Test operations with sample requests, e.g., for `add`:

   ```xml
   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                     xmlns:ser="http://service.example.com/">
      <soapenv:Header/>
      <soapenv:Body>
         <ser:add>
            <matrixA>
               <data>
                  <item>1.0</item>
                  <item>2.0</item>
               </data>
               <data>
                  <item>3.0</item>
                  <item>4.0</item>
               </data>
            </matrixA>
            <matrixB>
               <data>
                  <item>5.0</item>
                  <item>6.0</item>
               </data>
               <data>
                  <item>7.0</item>
                  <item>8.0</item>
               </data>
            </matrixB>
         </ser:add>
      </soapenv:Body>
   </soapenv:Envelope>
   ```
4. Verify the response, e.g., `[[6, 8], [10, 12]]`.

### 2. Java Client

Run the provided client to test programmatically:

```bash
mvn exec:java -Dexec.mainClass="com.example.client.MatrixClient"
```

This tests the `add` operation and prints the result.

## Deliverables

The following files are included for submission:

- **Source Files**:
  - `src/main/java/com/example/model/Matrix.java`
  - `src/main/java/com/example/exception/MatrixOperationException.java`
  - `src/main/java/com/example/service/MatrixService.java`
  - `src/main/java/com/example/service/MatrixServiceImpl.java`
  - `src/main/java/com/example/server/MatrixServer.java`
  - `src/main/java/com/example/client/MatrixClient.java`
  - `src/test/java/com/example/service/MatrixServiceTest.java`
- **WSDL**:
  - Download from `http://localhost:8080/services/MatrixService?wsdl` and save as `MatrixService.wsdl`.
- **Configuration**:
  - `pom.xml`
- **Documentation**:
  - This `README.md`

To package deliverables:

```bash
zip -r MatrixCalculator.zip src pom.xml README.md MatrixService.wsdl
```

## Additional Notes

- **Error Handling**: The service includes explicit error messages for invalid operations (e.g., incompatible matrices, null inputs) via `MatrixOperationException`.
- **Documentation**: Javadoc comments are provided in all classes for clarity.
- **JDK 21 Compatibility**: The project uses the Jakarta JAX-WS API to ensure compatibility with JDK 21.

## Sample Output

For matrices:

- A = `[[1, 2], [3, 4]]`
- B = `[[5, 6], [7, 8]]`

**Addition**:

```
[[6, 8], [10, 12]]
```

**Multiplication**:

```
[[19, 22], [43, 50]]
```

**Determinant of A**:

```
-2
```

## Conclusion

The Matrix Calculator web service successfully fulfills all project requirements, providing a functional, locally testable SOAP service with clean code and comprehensive testing. The use of `Endpoint.publish` simplifies deployment, making it an efficient solution for academic purposes.
