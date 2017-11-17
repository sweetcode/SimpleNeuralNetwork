package de.sweetcode.snn.activation;

public abstract class ActivationFunction {

    //--- Range
    private final double lower;
    private final double upper;

    //--- Cache
    private final boolean isCachingCalculate;
    private final boolean isCachingDerivative;
    private double cacheStep;
    private int cacheBias = 0;

    public ActivationFunction(double lower, double upper, boolean isCachingCalculate, boolean isCachingDerivative, double cacheStep) {
        this.lower = lower;
        this.upper = upper;
        this.isCachingCalculate = isCachingCalculate;
        this.isCachingDerivative = isCachingDerivative;

        if(isCachingCalculate || isCachingDerivative) {
            this.generateCache(lower, upper, this.cacheStep = cacheStep);
        }
    }

    public ActivationFunction(double lower, double upper) {
        this(lower, upper, false, false, 0);
    }

    public double getLower() {
        return this.lower;
    }

    public double getUpper() {
        return this.upper;
    }

    public abstract double calculate(double x);
    public abstract double derivative(double x);

    //--- Cache
    private double[] cacheCalculateKeys;
    private double[] cacheCalculateValues;

    private double[] cacheDerivativeKeys;
    private double[] cacheDerivativeValues;

    public double cachedCalculate(double x) {
        return this.getCache(this.cacheCalculateKeys, this.cacheCalculateValues, x, true);
    }

    public double cachedDerivative(double x) {
        return this.getCache(this.cacheDerivativeKeys, this.cacheDerivativeValues, x, false);
    }

    public boolean isCachingCalculate() {
        return isCachingCalculate;
    }

    public boolean isCachingDerivative() {
        return isCachingDerivative;
    }

    private void generateCache(double lower, double upper, double step) {

        this.cacheBias = (int) Math.abs(lower);

        //---
        int size = (int) ((upper - lower) / step) + 2;
        this.cacheCalculateKeys = new double[size];
        this.cacheCalculateValues = new double[size];

        this.cacheDerivativeKeys = new double[size];
        this.cacheDerivativeValues = new double[size];

        //---
        int i = 0;
        for(double input = lower; i < size; input += step) {

            //--- Calculate
            if(this.isCachingCalculate) {
                this.cacheCalculateKeys[i] = input;
                this.cacheCalculateValues[i] = this.calculate(input);
            }

            //--- Derivative
            if(this.isCachingDerivative) {
                this.cacheDerivativeKeys[i] = input;
                this.cacheDerivativeValues[i] = this.derivative(input);
            }

            i++;
        }
    }

    private double getCache(double[] inputs, double[] values, double x, boolean isCalculate) {
        //--- Index
        int index = (int) (x / this.cacheStep) + this.cacheBias;

        if(index + 1 >= inputs.length) {
            if(isCalculate) return this.calculate(x);
            else return this.derivative(x);
        }

        double lowerInput = inputs[index];
        double upperInput = inputs[index + 1];

        double lower = values[(index)];
        double upper = values[(index + 1)];

        //--- Exact match; lower
        if(lowerInput == x) {
            return lower;
        }
        //--- Exact match; upper
        else if(upperInput == x) {
            return upper;
        }
        //--- Indirect Match; Interpolate
        else if(lowerInput < x && upperInput > x) {
            return lower + (((upperInput - lowerInput) / (upper - lower)) * (x / upperInput));
        }

        throw new IllegalStateException(String.format("%d -> %d", index, inputs.length));
    }

}
