package de.sweetcode.snn.activation;

public class Gaussian extends ActivationFunction {

    public Gaussian() {
        super(0, 1);
    }

    public Gaussian(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(0, 1, isCachingCalculate, isCachingDerivative, cacheStep);
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
