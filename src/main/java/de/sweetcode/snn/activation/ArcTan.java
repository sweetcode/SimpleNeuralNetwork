package de.sweetcode.snn.activation;

public class ArcTan extends ActivationFunction {

    public ArcTan() {
        super(-(Math.PI / 2), (Math.PI / 2));
    }

    public ArcTan(boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        super(-(Math.PI / 2), (Math.PI / 2), isCachingCalculate, isCachingDerivative, cacheStep);
    }

    @Override
    public double calculate(double x) {
        return Math.atan(x);
    }

    @Override
    public double derivative(double x) {
        return (1 / (1 + Math.pow(x, 2)));
    }
}
