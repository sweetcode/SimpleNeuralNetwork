package de.sweetcode.snn.activation;

public class TanH extends ActivationFunction {

    public TanH() {
        super(-1, 1);
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
