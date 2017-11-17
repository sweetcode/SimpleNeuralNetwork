package de.sweetcode.snn.activation;

public class Sinusoid extends ActivationFunction {

    public Sinusoid() {
        super(-1, 1);
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
