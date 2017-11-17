package de.sweetcode.snn.activation;

public class BinaryStep extends ActivationFunction {

    public BinaryStep() {
        super(0, 1);
    }

    @Override
    public double calculate(double x) {
        return (x < 0 ? 0 : 1);
    }

    @Override
    public double derivative(double x) {
        return (x == 0 ? 1 : 0);
    }
}
