package com.example.client;

import com.example.exception.MatrixOperationException;
import com.example.model.Matrix;
import com.example.service.MatrixService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

/**
 * Client to test the Matrix Calculator web service, including exception handling.
 */
public class MatrixClient {
    public static void main(String[] args) {
        try {
            // Initialize the web service client
            URL wsdlURL = new URL("http://localhost:8080/services/MatrixService?wsdl");
            QName qname = new QName("http://service.example.com/", "MatrixServiceImplService");
            Service service = Service.create(wsdlURL, qname);
            MatrixService matrixService = service.getPort(MatrixService.class);

            // Test Case 1: Valid Addition
            System.out.println("Test Case 1: Valid Addition");
            Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
            Matrix b = new Matrix(new double[][]{{5, 6}, {7, 8}});
            try {
                Matrix resultAdd = matrixService.add(a, b);
                System.out.println("Addition Result:\n" + resultAdd);
            } catch (MatrixOperationException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            System.out.println("---------------------------------");

            // Test Case 2: Valid Multiplication
            System.out.println("Test Case 2: Valid Multiplication");
            Matrix c = new Matrix(new double[][]{{1, 2}, {3, 4}});
            Matrix d = new Matrix(new double[][]{{5, 6}, {7, 8}});
            try {
                Matrix resultMultiply = matrixService.multiply(c, d);
                System.out.println("Multiplication Result:\n" + resultMultiply);
            } catch (MatrixOperationException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            System.out.println("---------------------------------");

            // Test Case 2: Valid Multiplication
            System.out.println("Test Case 3: Valid Substraction");
            Matrix l = new Matrix(new double[][]{{9, 10}, {7, 8}});
            Matrix m = new Matrix(new double[][]{{3, 4}, {2, 5}});
            try {
                Matrix resultSubstract = matrixService.subtract(l, m);
                System.out.println("Substraction Result:\n" + resultSubstract);
            } catch (MatrixOperationException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            
            
            System.out.println("---------------------------------");

            // Test Case 3: Addition with Incompatible Matrices
            System.out.println("Test Case 3: Addition with Incompatible Matrices");
            Matrix e = new Matrix(new double[][]{{1, 2}, {3, 4}});
            Matrix f = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
            try {
                Matrix resultAddInvalid = matrixService.add(e, f);
                System.out.println("Addition Result:\n" + resultAddInvalid);
            } catch (MatrixOperationException e1) {
                System.out.println("Exception: " + e1.getMessage());
            }
            System.out.println("---------------------------------");

            // Test Case 4: Multiplication with Incompatible Matrices
            System.out.println("Test Case 4: Multiplication with Incompatible Matrices");
            Matrix g = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
            Matrix h = new Matrix(new double[][]{{1, 2}, {3, 4}});
            try {
                Matrix resultMultiplyInvalid = matrixService.multiply(g, h);
                System.out.println("Multiplication Result:\n" + resultMultiplyInvalid);
            } catch (MatrixOperationException e1) {
                System.out.println("Exception: " + e1.getMessage());
            }
            System.out.println("---------------------------------");

            // Test Case 5: Null Matrix Input
            System.out.println("Test Case 5: Null Matrix Input");
            Matrix i = null;
            Matrix j = new Matrix(new double[][]{{1, 2}, {3, 4}});
            try {
                Matrix resultAddNull = matrixService.add(i, j);
                System.out.println("Addition Result:\n" + resultAddNull);
            } catch (MatrixOperationException e1) {
                System.out.println("Exception: " + e1.getMessage());
            }
            System.out.println("---------------------------------");

            // Test Case 6: Determinant of Non-Square Matrix
            System.out.println("Test Case 6: Determinant of Non-Square Matrix");
            Matrix k = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
            try {
                double det = matrixService.determinant(k);
                System.out.println("Determinant: " + det);
            } catch (MatrixOperationException e1) {
                System.out.println("Exception: " + e1.getMessage());
            }
            System.out.println("---------------------------------");

        } catch (Exception e) {
            System.err.println("Failed to connect to the web service: " + e.getMessage());
            e.printStackTrace();
        }
    }
}