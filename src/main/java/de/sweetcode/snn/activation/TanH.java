package de.sweetcode.snn.activation;

public class TanH extends ActivationFunction {

    public TanH() {
        super(-1, 1);
    }

    public TanH(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(-1, 1, isCachingCalculate, isCachingDerivative, cacheStep);
    }

    @Override
    public double calculate(double x) {
        return Math.tanh(x);
    }

    @Override
    public double derivative(double x) {
        return (1 - Math.pow(this.calculate(x), 2));
    }

}
