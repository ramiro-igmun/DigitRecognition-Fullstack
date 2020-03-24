package igmun.ramiro.neuralnetworkrestapi.domain.network.neuronLayers;

public class HiddenNeuronLayer extends NeuronLayer {

  NeuronLayer nextLayer;

  public HiddenNeuronLayer(int layerLength, int inputLength){
    super(layerLength,inputLength);
  }

  @Override
  public void calculateNeuronErrors() {
    super.neurons.forEach(neuron -> neuron.calculateError(nextLayer.inputWeights.getInputNeuronWeights(neuron.getId()),nextLayer.getNeuronErrors()));
  }

  public void setNextNeuronLayer(NeuronLayer nextLayer){
    this.nextLayer = nextLayer;
  }
}
