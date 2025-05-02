package com.example.exception;

import jakarta.xml.ws.WebFault;

@WebFault(name = "MatrixOperationException")
public class MatrixOperationException extends Exception {
    public MatrixOperationException() {
        super();
    }

    public MatrixOperationException(String message) {
        super(message);
    }

    public MatrixOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}