package de.sweetcode.snn.activation;

public class ReLU extends ActivationFunction {

    public ReLU() {
        super(0, Double.MAX_VALUE);
    }

    @Override
    public double calculate(double x) {
        return (x < 0 ? 0 : x);
    }

    @Override
    public double derivative(double x) {
        return (x < 0 ? 0 : 1);
    }

}
