package de.sweetcode.snn;

/**
 * <span>Represents the connection between two Neurons.</span>
 */
public class Connection {

    private final Neuron from;
    private final Neuron to;

    private double weight;

    public Connection(Neuron from, Neuron to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Neuron from() {
        return this.from;
    }

    public Neuron to() {
        return this.to;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
