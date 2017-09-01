package com.intapp.platform.factorial.service.factorial;

import lombok.*;

import java.util.Arrays;

/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
@EqualsAndHashCode
public class Matrix {

    private double[][] matrix;

    public Matrix(int n, int m) {
        matrix = new double[n][m];
    }

    public int getVerticalSize() {
        return matrix.length;
    }

    public int getHorizontalSize() {
        return matrix[0].length;
    }

    public double getElement(int i, int j) {
        return matrix[i][j];
    }

    public void setElement(int i, int j, double value) {
        matrix[i][j] = value;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public String toString() {
        String s = "\nMatrix : " + matrix.length +
                "x" + matrix[0].length + "\n";
        for (double[] vector : matrix) {
            for (double value : vector)
                s += value + " ";
            s += "\n";
        }
        return s;
    }

}
