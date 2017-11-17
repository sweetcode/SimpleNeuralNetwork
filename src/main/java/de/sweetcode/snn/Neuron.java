package de.sweetcode.snn;

import java.util.LinkedList;
import java.util.List;

public class Neuron {

    private final List<Connection> ins = new LinkedList<>();
    private final List<Connection> outs = new LinkedList<>();

    private double output = 0;
    private double outputDerivative = 0;

    private double error = 0;

    private double bias = 0;

    public Neuron() {}

    public List<Connection> getIns() {
        return this.ins;
    }

    public List<Connection> getOuts() {
        return this.outs;
    }

    public double getOutput() {
        return this.output;
    }

    public double getOutputDerivative() {
        return this.outputDerivative;
    }

    public double getError() {
        return this.error;
    }

    public double getBias() {
        return this.bias;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public void setOutputDerivative(double outputDerivative) {
        this.outputDerivative = outputDerivative;
    }

    public void setError(double error) {
        this.error = error;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
