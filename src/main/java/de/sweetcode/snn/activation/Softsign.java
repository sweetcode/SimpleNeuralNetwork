package de.sweetcode.snn.activation;

public class Softsign extends ActivationFunction {

    public Softsign() {
        super(-1, 1);
    }

    public Softsign(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(-1, 1, isCachingCalculate, isCachingDerivative, cacheStep);
    }

    @Override
    public double calculate(double x) {
        return (x / (1 + Math.abs(x)));
    }

    @Override
    public double derivative(double x) {
        return (x / (1 + Math.pow(Math.abs(x), 2)));
    }
}
