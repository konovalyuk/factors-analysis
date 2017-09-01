package com.intapp.platform.factorial.service.factorial;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
public class MatrixLogic {

    public static Vector multiplyMatrixOnVector(Matrix matrix, Vector vectorInput) {
        double[] vector = vectorInput.getVector();
        Vector resultVector = new Vector(vector.length);
        double[] result = Arrays.stream(matrix.getMatrix())
                .mapToDouble(row ->
                        IntStream.range(0, row.length)
                                .mapToDouble(col -> row[col] * vector[col])
                                .sum()
                ).toArray();
        resultVector.setVector(result);
        return resultVector;
    }

    public static Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) throws MultipleException {
        int verticalSize = matrixA.getVerticalSize();
        int horizontalSize = matrixB.getHorizontalSize();
        int temp = matrixA.getHorizontalSize();
// проверка возможности умножения
        if (temp != matrixB.getVerticalSize())
            throw new MultipleException();
// создание матрицы результата
        Matrix result = new Matrix(verticalSize, horizontalSize);
// умножение
        for (int i = 0; i < verticalSize; i++)
            for (int j = 0; j < horizontalSize; j++) {
                int value = 0;
                for (int k = 0; k < temp; k++) {
                    value += matrixA.getElement(i, k) * matrixB.getElement(k, j);
                }
                result.setElement(i, j, value);
            }
        return result;
    }

    public static Matrix transposeMatrix(Matrix matrix) {
        int verticalSize = matrix.getVerticalSize();
        int horizontalSize = matrix.getHorizontalSize();
        Matrix result = new Matrix(horizontalSize, verticalSize);

        for (int i = 0; i < verticalSize; i++)
            for (int j = 0; j < horizontalSize; j++)
                result.setElement(j, i, matrix.getElement(i, j));
        return result;
    }

    public static Matrix invert(Matrix matrix) {
        double[][] a = matrix.getMatrix();
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];

            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }

        matrix.setMatrix(x);
        return matrix;
    }

    // Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
    public static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;

            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }

            c[i] = c1;
        }
        // Search the pivoting element from each column

        int k = 0;

        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];

                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;

            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];

            }
        }
    }

}
