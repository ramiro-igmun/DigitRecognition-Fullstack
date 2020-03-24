package igmun.ramiro.neuralnetworkrestapi.domain.network.neuronLayers;

import igmun.ramiro.neuralnetworkrestapi.domain.network.Network;
import igmun.ramiro.neuralnetworkrestapi.domain.network.Neuron;
import igmun.ramiro.neuralnetworkrestapi.domain.network.WeightMatrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class NeuronLayer implements Serializable {
  protected List<Neuron> neurons = new ArrayList<>();
  protected WeightMatrix inputWeights;
  private final int layerLength;
  private final int inputLength;

  public NeuronLayer() {
    this(10, 15);
  }

  public NeuronLayer(int layerLength, int inputLength) {
    this.layerLength = layerLength;
    this.inputLength = inputLength + 1; // ?? add bias weights
    inputWeights = new WeightMatrix(this.inputLength, layerLength, Network.learningRate);
    initNeurons();
  }

  private void initNeurons() {
    neurons.clear();
    IntStream.range(0, layerLength)
            .forEach(index -> neurons.add(new Neuron(index, inputWeights.getOutputNeuronWeights(index))));
  }

  public void activateNeurons(double[] previousLayerActivationsWithBias) {
    neurons.forEach(neuron -> neuron.activate(previousLayerActivationsWithBias));
  }

  public abstract void calculateNeuronErrors();

  public void updateWeights(double[] previousLayerActivationsWithBias) {
    inputWeights.updateWeights(getNeuronErrors(), previousLayerActivationsWithBias);
    IntStream.range(0, layerLength)
            .forEach(index -> neurons.get(index).setWeights(inputWeights.getOutputNeuronWeights(index)));
  }

  public double[] getNeuronErrors() {
    return neurons.stream().mapToDouble(Neuron::getError).toArray();
  }

  public double[] getNeuronActivations(){
    return neurons.stream().mapToDouble(Neuron::getActivation).toArray();
  }

  public double[] getNeuronActivationsWithBias(){
    List<Double> activationsWithBias = neurons.stream().mapToDouble((Neuron::getActivation)).boxed().collect(Collectors.toList());
    activationsWithBias.add(1D);
    return activationsWithBias.stream().mapToDouble(Double::valueOf).toArray();
  }

  public List<Neuron> getNeurons(){
    return neurons;
  }

  public int getSize() {
    return neurons.size();
  }

  public WeightMatrix getInputWeights() {
    return inputWeights;
  }

  public int getLayerLength() {
    return layerLength;
  }

  public int getInputLength() {
    return inputLength;
  }
}
