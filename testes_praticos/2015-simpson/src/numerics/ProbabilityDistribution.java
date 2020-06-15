package numerics;

import java.io.*;
import java.lang.Math;
import java.util.ArrayList;

public class ProbabilityDistribution {

    public static final double INCREMENT = 1E-4;
    protected double mean;
    protected double stddev;

    public ProbabilityDistribution(double mean, double stddev) throws IllegalArgumentException {
        if(mean == 0 && stddev == 0) throw new IllegalArgumentException();
        this.mean = mean;
        this.stddev = stddev;
    }

    public double getMean() {
        return mean;
    }

    public double getStddev() {
        return stddev;
    }

    public double probabilityDensityFunction(double v) {
        return 1 / (Math.sqrt(2*Math.PI)) * Math.exp(-1/2.0 * Math.pow(v, 2));
    }

    public double calcRangeProbability(double a, double b) {
        // wikipedia é nosso amigo
        double area = 0;
        double modifier = 1;
        if(a > b) {
            double tempA = a;
            a = b;
            b = tempA;
            modifier = -1;
        }
        for(double i = a + INCREMENT; i < b; i += INCREMENT) {
            double dFromA = i - a;
            area += (INCREMENT / 2) * (probabilityDensityFunction(a + dFromA) + probabilityDensityFunction(a + dFromA - INCREMENT));
        }
        return (Math.round(area * 100000.0) / 100000.0) * modifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        ProbabilityDistribution p = (ProbabilityDistribution) o;                          // cast the other object
        return mean == p.getMean() && stddev == p.getStddev();        // actual comparison
    }

    @Override
    public String toString() {
        return "N(" + String.valueOf(this.mean) + ", " + String.valueOf(this.stddev) + ")";
    }

}
