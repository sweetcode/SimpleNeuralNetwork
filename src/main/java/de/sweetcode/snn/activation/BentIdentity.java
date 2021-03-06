package de.sweetcode.snn.activation;

public class BentIdentity extends ActivationFunction {

    public BentIdentity() {
        super(Short.MIN_VALUE, Short.MAX_VALUE);
    }

    public BentIdentity(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(Short.MIN_VALUE, Short.MAX_VALUE, isCachingCalculate, isCachingDerivative, cacheStep);
    }

    @Override
    public double calculate(double x) {
        return ((Math.sqrt(Math.pow(x,2) + 1) - 1) / 2) + x;
    }

    @Override
    public double derivative(double x) {
        return ((x / (2 * Math.sqrt(Math.pow(x,2) + 1))) + 1);
    }
}
