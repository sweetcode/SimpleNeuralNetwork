package de.sweetcode.snn.activation;

public class Sinc extends ActivationFunction {

    public Sinc() {
        super(-0.217234, 1);
    }

    @Override
    public double calculate(double x) {
        return ((x == 0) ? 1 : (Math.sin(x) / x));
    }

    @Override
    public double derivative(double x) {
        return ((x == 0) ? 0 : ((Math.cos(x) / x) - (Math.sin(x) / Math.pow(x, 2))));
    }
}
