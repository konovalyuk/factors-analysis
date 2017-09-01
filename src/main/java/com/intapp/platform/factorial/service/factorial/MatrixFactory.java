package com.intapp.platform.factorial.service.factorial;


/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
public class MatrixFactory {

    public static Matrix createMatrix(int n, int m) {
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.setElement(i, j, (int) (Math.random() * n * n));
            }
        }
        return matrix;
    }

    public static Vector createVector(int n) {
        Vector vector = new Vector(n);
        for (int i = 0; i < n; i++) {
            vector.setElement(i, (int) (Math.random() * n * n));
        }
        return vector;
    }

}
