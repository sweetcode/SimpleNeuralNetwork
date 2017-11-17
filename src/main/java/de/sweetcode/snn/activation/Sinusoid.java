package de.sweetcode.snn.activation;

public class Sinusoid extends ActivationFunction {

    public Sinusoid() {
        super(-1, 1);
    }

    public Sinusoid(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(-1, 1, isCachingCalculate, isCachingDerivative, cacheStep);
    }

    @Override
    public double calculate(double x) {
        return Math.sin(x);
    }

    @Override
    public double derivative(double x) {
        return Math.cos(x);
    }

}
