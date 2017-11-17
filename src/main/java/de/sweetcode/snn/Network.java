package de.sweetcode.snn;

import de.sweetcode.snn.activation.ActivationFunction;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Network {

    private final List<Layer> layers = new LinkedList<>();
    private final ActivationFunction activationFunction;

    /**
     * <span>Initializes a new network.</span>
     *
     * @param NETWORK_LAYER_SIZES An array describing the amount of layers, and the amount of neurons each layer has.
     *                            <span>Example: <code>new int[] {2, 3, 1}</code> would init a network: input layer (2 neurons),
     *                            1 hidden layer (3 neurons), and an output layer (1 output).</span>
     */
    public Network(long seed, ActivationFunction activationFunction, int[] NETWORK_LAYER_SIZES) {
        this.activationFunction = activationFunction;

        for(int i = 0; i < NETWORK_LAYER_SIZES.length; i++) {
            Layer layer = new Layer(NETWORK_LAYER_SIZES[i]);
            this.layers.add(layer);
        }

        //@TODO https://arxiv.org/pdf/1511.06422.pdf - Improved initial weights
        //@TODO http://ruder.io/optimizing-gradient-descent/index.html - Improved learning rates
        Random random = new Random(seed);

        for(int layI = 0; layI < this.layers.size() - 1; layI++) {

            Layer layer = this.layers.get(layI);
            Layer nextLayer = this.layers.get(layI + 1);

            layer.getNeurons().forEach(neuron -> nextLayer.getNeurons().forEach(nextNeuron -> {
                double weight = activationFunction.getLower() + (random.nextDouble() * activationFunction.getUpper() - activationFunction.getLower());
                Connection connection = new Connection(neuron, nextNeuron, weight);
                neuron.getOuts().add(connection);
                nextNeuron.getIns().add(connection);
            }));

        }

    }

    public void train(double[][] inputs, double[][] targets, double learningRate, int iterations) {
        for(int c = 0; c < iterations; c++) {
            for(int i = 0; i < inputs.length; i++) {
                this.train(inputs[i], targets[i], learningRate);
            }
        }
    }

    public double[] think(double[] input) {

        if(!(input.length == this.layers.get(0).getSize())) {
            throw new IllegalArgumentException();
        }

        //--- set input
        Layer inputLayer = this.layers.get(0);
        for (int i = 0; i < input.length; i++) {
            inputLayer.getNeuron(i).setOutput(input[i]);
        }

        this.layers.stream().skip(1).forEachOrdered(layer -> layer.getNeurons().forEach(neuron -> {

            //--- Activation Function
            double sum = neuron.getBias();

            for (Connection connection : neuron.getIns()) {
                sum += connection.from().getOutput() * connection.getWeight();
            }

            if(this.activationFunction.isCachingCalculate()) {
                neuron.setOutput(this.activationFunction.cachedCalculate(sum));
            } else {
                neuron.setOutput(this.activationFunction.calculate(sum));
            }

            if(this.activationFunction.isCachingDerivative()) {
                neuron.setOutputDerivative(this.activationFunction.cachedDerivative(sum));
            } else {
                neuron.setOutputDerivative(this.activationFunction.derivative(sum));
            }

            if(!(Double.isFinite(neuron.getOutput()) && Double.isFinite(neuron.getOutputDerivative()))) {
                System.out.println(sum + " -> " + neuron.getOutput() + " <> " + neuron.getOutputDerivative());
            }
        }));

        return this.layers.get(this.layers.size() - 1).getOutputs();

    }

    private void backpropagationError(double[] target) {
        //--- Output layer
        Layer outputLayer = this.layers.get(this.layers.size() - 1);
        for (int index = 0; index < outputLayer.getSize(); index++) {
            Neuron neuron = outputLayer.getNeuron(index);
            neuron.setError((neuron.getOutput() - target[index]) * neuron.getOutputDerivative());
        }

        //--- Hidden Layers
        for(int layI = this.layers.size() - 2; layI > 0; layI--) {
            Layer layer = this.layers.get(layI);

            layer.getNeurons().forEach(neuron -> {
                double sum = 0;
                for (Connection connection : neuron.getOuts()) {
                    sum += connection.getWeight() * connection.to().getError();
                }
                neuron.setError(sum * neuron.getOutputDerivative());
            });

        }
    }

    private void updateWeights(double learningRate) {

        this.layers.forEach(layer -> layer.getNeurons().forEach(neuron -> {
            neuron.getIns().forEach(connection -> connection.setWeight(
                connection.getWeight() + (-learningRate * connection.from().getOutput() * neuron.getError())
            ));
            neuron.setBias(
                neuron.getBias() + (-learningRate * neuron.getError())
            );
        }));

    }

    private void train(double[] input, double[] target, double learningRate) {
        this.think(input);
        this.backpropagationError(target);
        this.updateWeights(learningRate);
    }

}
