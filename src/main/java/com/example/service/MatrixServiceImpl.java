package com.example.service;

import com.example.exception.MatrixOperationException;
import com.example.model.Matrix;
import jakarta.jws.WebService;

/**
 * Implementation of the MatrixService interface for matrix operations.
 * Provides methods for matrix addition, subtraction, multiplication, transpose, and determinant.
 */
@WebService(endpointInterface = "com.example.service.MatrixService")
public class MatrixServiceImpl implements MatrixService {

    /**
     * Adds two matrices.
     * @param a First matrix
     * @param b Second matrix
     * @return Resultant matrix after addition
     * @throws MatrixOperationException if matrices are incompatible or invalid
     */
    @Override
    public Matrix add(Matrix a, Matrix b) throws MatrixOperationException {
        validateMatrices(a, b, "Addition");
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
            throw new MatrixOperationException("Matrices must have the same dimensions for addition");
        }
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result[i][j] = a.getData()[i][j] + b.getData()[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Subtracts the second matrix from the first.
     * @param a First matrix
     * @param b Second matrix
     * @return Resultant matrix after subtraction
     * @throws MatrixOperationException if matrices are incompatible or invalid
     */
    @Override
    public Matrix subtract(Matrix a, Matrix b) throws MatrixOperationException {
        validateMatrices(a, b, "Subtraction");
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
            throw new MatrixOperationException("Matrices must have the same dimensions for subtraction");
        }
        double[][] result = new double[a.getRows()][a.getCols()];
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result[i][j] = a.getData()[i][j] - b.getData()[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Multiplies two matrices.
     * @param a First matrix
     * @param b Second matrix
     * @return Resultant matrix after multiplication
     * @throws MatrixOperationException if matrices are incompatible or invalid
     */
    @Override
    public Matrix multiply(Matrix a, Matrix b) throws MatrixOperationException {
        validateMatrices(a, b, "Multiplication");
        if (a.getCols() != b.getRows()) {
            throw new MatrixOperationException("Number of columns in first matrix must equal number of rows in second matrix");
        }
        double[][] result = new double[a.getRows()][b.getCols()];
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                for (int k = 0; k < a.getCols(); k++) {
                    result[i][j] += a.getData()[i][k] * b.getData()[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    /**
     * Computes the transpose of a matrix.
     * @param a Input matrix
     * @return Transposed matrix
     * @throws MatrixOperationException if matrix is invalid
     */
    @Override
    public Matrix transpose(Matrix a) throws MatrixOperationException {
        validateMatrix(a, "Transpose");
        double[][] result = new double[a.getCols()][a.getRows()];
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result[j][i] = a.getData()[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Calculates the determinant of a square matrix.
     * @param a Input matrix
     * @return Determinant value
     * @throws MatrixOperationException if matrix is not square or invalid
     */
    @Override
    public double determinant(Matrix a) throws MatrixOperationException {
        validateMatrix(a, "Determinant");
        if (a.getRows() != a.getCols()) {
            throw new MatrixOperationException("Matrix must be square to calculate determinant");
        }
        return calculateDeterminant(a.getData());
    }

    /**
     * Validates a single matrix for null or empty conditions.
     * @param m Matrix to validate
     * @param operation Operation name for error message
     * @throws MatrixOperationException if matrix is invalid
     */
    private void validateMatrix(Matrix m, String operation) throws MatrixOperationException {
        if (m == null || m.getData() == null || m.getRows() == 0 || m.getCols() == 0) {
            throw new MatrixOperationException(operation + " cannot be performed: Matrix is null or empty");
        }
    }

    /**
     * Validates two matrices for null or empty conditions.
     * @param a First matrix
     * @param b Second matrix
     * @param operation Operation name for error message
     * @throws MatrixOperationException if either matrix is invalid
     */
    private void validateMatrices(Matrix a, Matrix b, String operation) throws MatrixOperationException {
        validateMatrix(a, operation);
        validateMatrix(b, operation);
    }

    /**
     * Recursively calculates the determinant of a matrix.
     * @param data Matrix data as 2D array
     * @return Determinant value
     */
    private double calculateDeterminant(double[][] data) {
        int n = data.length;
        if (n == 1) return data[0][0];
        if (n == 2) return data[0][0] * data[1][1] - data[0][1] * data[1][0];

        double det = 0;
        for (int j = 0; j < n; j++) {
            det += data[0][j] * cofactor(data, 0, j);
        }
        return det;
    }

    /**
     * Calculates the cofactor for a given element.
     * @param data Matrix data
     * @param row Row index
     * @param col Column index
     * @return Cofactor value
     */
    private double cofactor(double[][] data, int row, int col) {
        return Math.pow(-1, row + col) * calculateDeterminant(getSubMatrix(data, row, col));
    }

    /**
     * Creates a sub-matrix excluding the specified row and column.
     * @param data Matrix data
     * @param excludeRow Row to exclude
     * @param excludeCol Column to exclude
     * @return Sub-matrix as 2D array
     */
    private double[][] getSubMatrix(double[][] data, int excludeRow, int excludeCol) {
        int n = data.length;
        double[][] subMatrix = new double[n - 1][n - 1];
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i == excludeRow) continue;
            r++;
            int c = -1;
            for (int j = 0; j < n; j++) {
                if (j == excludeCol) continue;
                subMatrix[r][++c] = data[i][j];
            }
        }
        return subMatrix;
    }
}