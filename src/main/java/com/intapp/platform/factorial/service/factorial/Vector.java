package com.intapp.service.factorial;

/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
public class Vector {

    private double[] vector;

    public Vector(int n) {
        vector = new double[n];
    }

    public int getSize() {
        return vector.length;
    }

    public double getElement(int i) {
        return vector[i];
    }

    public void setElement(int i, double value) {
        vector[i] = value;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public String toString() {
        String s = "\nMatrix : " + vector.length +
                "x" + "\n";
        for (double value : vector)
            s += value + " ";
        s += "\n";
        return s;
    }
}
