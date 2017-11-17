package de.sweetcode.snn.activation;

public class PReLU extends ActivationFunction {

    private final double alpha;

    public PReLU(double alpha) {
        super(Short.MIN_VALUE, Short.MAX_VALUE);
        this.alpha = alpha;

    }

    public PReLU(double alpha, boolean isCachingCalculate, double cacheStep) {
        super(Short.MIN_VALUE, Short.MAX_VALUE, isCachingCalculate, false, cacheStep);
        this.alpha = alpha;
    }

    @Override
    public double calculate(double x) {
        return (x >= 0 ? x : (this.alpha * x));
    }

    @Override
    public double derivative(double x) {
        return (x >= 0 ? 1 : this.alpha);
    }
}
