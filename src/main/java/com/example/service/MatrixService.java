package com.example.service;

import com.example.exception.MatrixOperationException;
import com.example.model.Matrix;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface MatrixService {
    @WebMethod
    Matrix add(@WebParam(name = "matrixA") Matrix a, @WebParam(name = "matrixB") Matrix b) throws MatrixOperationException;

    @WebMethod
    Matrix subtract(@WebParam(name = "matrixA") Matrix a, @WebParam(name = "matrixB") Matrix b) throws MatrixOperationException;

    @WebMethod
    Matrix multiply(@WebParam(name = "matrixA") Matrix a, @WebParam(name = "matrixB") Matrix b) throws MatrixOperationException;

    @WebMethod
    Matrix transpose(@WebParam(name = "matrix") Matrix a) throws MatrixOperationException;

    @WebMethod
    double determinant(@WebParam(name = "matrix") Matrix a) throws MatrixOperationException;
}