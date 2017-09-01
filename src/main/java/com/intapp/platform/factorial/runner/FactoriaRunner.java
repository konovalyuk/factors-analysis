package com.intapp.platform.factorial.runner;

import com.intapp.platform.factorial.service.factorial.Matrix;
import com.intapp.platform.factorial.service.factorial.MatrixFactory;
import com.intapp.platform.factorial.service.factorial.Vector;

import static com.intapp.platform.factorial.service.factorial.MatrixLogic.*;


/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
public class FactoriaRunner {

    public static void main(String[] args) {
        int n = 2, m = 3, l = 4;

        n = 3;
        m = 4;
        Matrix inputMatrix = MatrixFactory.createMatrix(n, m);
        Vector inputVector = MatrixFactory.createVector(n);
        System.out.println(" inputVector : " + inputVector);
        System.out.println(" inputMatrix : " + inputMatrix);

        Matrix step = transposeMatrix(inputMatrix);
        System.out.println("step 1 transposeMatrix: " + step);
        step = multiplyMatrix(step,inputMatrix);
        System.out.println("step 2 multiplyMatrix: " + step);
        step = invert(step);
        System.out.println("step 3 invert: " + step);
        step = multiplyMatrix(step,transposeMatrix(inputMatrix));
        System.out.println("step 4 : " + step);
        Vector result = multiplyMatrixOnVector(step,inputVector);
        System.out.println(" result : " + result);

    }
}
