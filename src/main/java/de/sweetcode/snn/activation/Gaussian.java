package de.sweetcode.snn.activation;

public class Gaussian extends ActivationFunction {

    public Gaussian() {
        super(0, 1);
    }

    @Override
    public double calculate(double x) {
        return Math.exp(Math.pow(-x,2));
    }

    @Override
    public double derivative(double x) {
        return (-2 * x * Math.exp(Math.pow(-x, 2)));
    }
}
