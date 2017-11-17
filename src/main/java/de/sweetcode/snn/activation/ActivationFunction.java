package de.sweetcode.snn.activation;

public abstract class ActivationFunction {

    private final double lower;
    private final double upper;

    public ActivationFunction(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public double getLower() {
        return this.lower;
    }

    public double getUpper() {
        return this.upper;
    }

    public boolean isInRange(double x) {
        return (x >= this.lower && x <= this.upper);
    }

    public abstract double calculate(double x);
    public abstract double derivative(double x);

}
