package de.sweetcode.snn.activation;

public class PReLU extends ActivationFunction {

    private final double a;

    public PReLU(double a) {
        super(Short.MIN_VALUE, Short.MAX_VALUE);
        this.a = a;
    }

    @Override
    public double calculate(double x) {
        return (x >= 0 ? x : (this.a * x));
    }

    @Override
    public double derivative(double x) {
        return (x >= 0 ? 1 : this.a);
    }
}
