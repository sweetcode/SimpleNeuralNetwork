package de.sweetcode.snn.activation;

public class Identity extends ActivationFunction {

    public Identity() {
        super(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @Override
    public double calculate(double x) {
        return x;
    }

    @Override
    public double derivative(double x) {
        return 1;
    }

}
