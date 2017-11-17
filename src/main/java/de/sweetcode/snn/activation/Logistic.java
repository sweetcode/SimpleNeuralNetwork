package de.sweetcode.snn.activation;

public class Logistic extends ActivationFunction {

    public Logistic() {
        super(0, 1);
    }

    public Logistic(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(0, 1, isCachingCalculate, isCachingDerivative, cacheStep);
    }

    @Override
    public double calculate(double x) {
        return (1 / (1 + Math.exp(-x)));
    }

    @Override
    public double derivative(double x) {
        double c = this.calculate(x);
        return (c * (1 - c));
    }
}
