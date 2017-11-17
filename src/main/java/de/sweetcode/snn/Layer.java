package de.sweetcode.snn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Layer {

    private int size;

    private double[] output;
    private double[] output_derivative;

    private double[] error_signal;

    private List<Neuron> neurons = new ArrayList<>();

    public Layer(int size) {
        IntStream.range(0, size).forEach(i -> this.neurons.add(new Neuron()));

        this.size = size;
        this.output = new double[this.size];
        this.output_derivative = new double[this.size];
        this.error_signal = new double[this.size];
    }

    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    public Neuron getNeuron(int index) {
        return this.neurons.get(index);
    }

    public int getSize() {
        return this.neurons.size();
    }

    public Connection getConnection(Neuron from, Neuron to) {

        for (Neuron neuron : this.neurons) {
            Optional<Connection> optional = neuron.getIns().stream()
                    .filter(connection -> (connection.from() == from && connection.to() == to)).findAny();

            if(optional.isPresent()) {
                return optional.get();
            }
        }

        throw new IllegalArgumentException();
    }

    public double[] getOutputs() {
        double[] outputs = new double[this.getSize()];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = this.getNeuron(i).getOutput();
        }
        return outputs;
    }

}
