package igmun.ramiro.neuralnetworkrestapi.domain.network.neuronLayers;

public class OutputNeuronLayer extends NeuronLayer {

  private double[] targetOutput;

  public OutputNeuronLayer(int layerLength, int inputLength){
    super(layerLength,inputLength);
  }

  @Override
  public void calculateNeuronErrors() {
    super.neurons.forEach(neuron -> neuron.calculateError(targetOutput[neuron.getId()]));
  }

  public void setTargetOutput(double[] targetOutput){
    this.targetOutput = targetOutput;
  }

  public double[] getTargetOutput() {
    return targetOutput;
  }
}
