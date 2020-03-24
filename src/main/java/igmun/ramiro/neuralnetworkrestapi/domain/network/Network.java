package igmun.ramiro.neuralnetworkrestapi.domain.network;

import igmun.ramiro.neuralnetworkrestapi.mnistData.mnistDataSamples.DataSample;
import igmun.ramiro.neuralnetworkrestapi.domain.network.neuronLayers.HiddenNeuronLayer;
import igmun.ramiro.neuralnetworkrestapi.domain.network.neuronLayers.NeuronLayer;
import igmun.ramiro.neuralnetworkrestapi.domain.network.neuronLayers.OutputNeuronLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Network {

  private List<NeuronLayer> hiddenLayers;
  private OutputNeuronLayer outputNeuronLayer;
  public static double learningRate = 0.5;

  private Network(Builder builder) {
    this.hiddenLayers = builder.hiddenLayers;
    this.outputNeuronLayer = builder.outputNeuronLayer;
  }

  public double[] getAnswerArray(double[] inputData) {
    forwardPass(addBiasToInputData(inputData));
    //return outputNeuronLayer.getNeurons().stream().max(Comparator.comparing(Neuron::getActivation)).get().getId();
    return outputNeuronLayer.getNeuronActivations();
  }

  private void forwardPass(double[] inputDataWithBias) {
    for (int i = 0; i < hiddenLayers.size(); i++) {
      if (i == 0) {
        hiddenLayers.get(i).activateNeurons(inputDataWithBias);
      } else {
        hiddenLayers.get(i).activateNeurons(hiddenLayers.get(i - 1).getNeuronActivationsWithBias());
      }
    }
    outputNeuronLayer.activateNeurons(hiddenLayers.get(hiddenLayers.size() - 1).getNeuronActivationsWithBias());
  }

  public void train(List<DataSample> dataSamples) {
    for (DataSample dataSample : dataSamples) {
      double[] inputDataWithBias = addBiasToInputData(dataSample.getInputValues());
      forwardPass(inputDataWithBias);
      outputNeuronLayer.setTargetOutput(dataSample.getTargetOutput());
      outputNeuronLayer.calculateNeuronErrors();
      outputNeuronLayer.updateWeights(hiddenLayers.get(hiddenLayers.size() - 1).getNeuronActivationsWithBias());
      for (int i = hiddenLayers.size() - 1; i >= 0; i--) {
        hiddenLayers.get(i).calculateNeuronErrors();
        if (i == 0) {
          hiddenLayers.get(i).updateWeights(inputDataWithBias);
        } else {
          hiddenLayers.get(i).updateWeights(hiddenLayers.get(i - 1).getNeuronActivationsWithBias());
        }
      }
    }
  }

  public double calculateAccuracy(List<DataSample> testDataSamples) {
    double numberOfSamples = testDataSamples.size();
    double successfulGuesses = 0;
    for (DataSample testDataSample : testDataSamples) {
      int guess = getMaxValue(getAnswerArray(testDataSample.getInputValues()));
      if (guess == testDataSample.getId()) {
        successfulGuesses++;
      }
    }
    return successfulGuesses / numberOfSamples;
  }

  private int getMaxValue(double[] array){
    return IntStream.range(0,array.length).boxed()
            .max(Comparator.comparing(i -> array[i]))
            .orElseThrow(IllegalStateException::new);
  }

  private double[] addBiasToInputData(double[] inputData) {
    return DoubleStream.concat(Arrays.stream(inputData), DoubleStream.of(1D))
            .toArray();
  }

  public static class Builder {

    private int dataSampleSize;
    private int outputNeuronLayerSize = 10;
    private List<NeuronLayer> hiddenLayers;
    private OutputNeuronLayer outputNeuronLayer;

    public Builder(int dataSampleSize) {
      this.dataSampleSize = dataSampleSize;
      hiddenLayers = new ArrayList<>();
    }

    public Builder addHiddenLayer(int layerSize) {
      if (hiddenLayers.isEmpty()) {
        hiddenLayers.add(new HiddenNeuronLayer(layerSize, dataSampleSize));
      } else {
        hiddenLayers.add(new HiddenNeuronLayer(layerSize, getLastLayerSize()));
      }
      return this;
    }

    public Builder setLearningRate(double rate) {
      learningRate = rate;
      return this;
    }

    public Builder setOutputNeuronLayerSize(int size) {
      this.outputNeuronLayerSize = size;
      return this;
    }

    public Network build() {
      if (hiddenLayers.isEmpty()) {
        hiddenLayers.add(new HiddenNeuronLayer(outputNeuronLayerSize, dataSampleSize));
      }

      outputNeuronLayer = new OutputNeuronLayer(outputNeuronLayerSize, getLastLayerSize());
      for (int i = 0; i < hiddenLayers.size(); i++) {
        HiddenNeuronLayer layer = (HiddenNeuronLayer) hiddenLayers.get(i);
        if (i == hiddenLayers.size() - 1) {
          layer.setNextNeuronLayer(outputNeuronLayer);
        } else {
          layer.setNextNeuronLayer(hiddenLayers.get(i + 1));
        }
      }
      return new Network(this);
    }

    private int getLastLayerSize() {
      return hiddenLayers.get(hiddenLayers.size() - 1).getSize();
    }
  }

  public List<NeuronLayer> getHiddenLayers() {
    return hiddenLayers;
  }

  public OutputNeuronLayer getOutputNeuronLayer() {
    return outputNeuronLayer;
  }

  public static double getLearningRate() {
    return learningRate;
  }
}
