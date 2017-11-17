package de.sweetcode.snn.activation;

public class SoftExponential extends ActivationFunction {

    private final double alpha;

    public SoftExponential(double alpha) {
        super(Short.MIN_VALUE, Short.MAX_VALUE);
        this.alpha = alpha;
    }

    @Override
    public double calculate(double x) {
        if(this.alpha < 0) {
            return -(Math.log(1 - this.alpha * (x + this.alpha)) / this.alpha);
        } else if(this.alpha > 0) {
            return (((Math.exp(this.alpha * x) - 1) / this.alpha) + this.alpha);
        } else {
            return x;
        }
    }

    @Override
    public double derivative(double x) {

        if(this.alpha < 0) {
            return (1 / (1 - this.alpha * (this.alpha + x)));
        } else /* alpha >= 0 */ {
            return Math.exp(this.alpha * x);
        }

    }
}
