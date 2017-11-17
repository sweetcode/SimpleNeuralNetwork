package de.sweetcode.snn.activation;

public class SoftPlus extends ActivationFunction {

    public SoftPlus() {
        super(0, Short.MAX_VALUE);
    }

    @Override
    public double calculate(double x) {
        return Math.log(1 + Math.exp(x));
    }

    @Override
    public double derivative(double x) {
        return (1 / (1 + Math.exp(-x)));
    }
}
