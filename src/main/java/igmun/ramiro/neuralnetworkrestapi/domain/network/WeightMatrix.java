package igmun.ramiro.neuralnetworkrestapi.domain.network;


import igmun.ramiro.neuralnetworkrestapi.utils.Matrix;

import java.util.stream.IntStream;

public class WeightMatrix {
  double [][] weights;
  double learningRate;

  public WeightMatrix(int inputSize, int outputSize, double learningRate){
   setRandomWeights(inputSize,outputSize);
   this.learningRate = learningRate;
  }

  private void setRandomWeights(int inputSize, int outputSize){
    weights = Matrix.getRandomMatrix(outputSize, inputSize);
  }

  public void updateWeights(double[] outputNeuronErrors, double[] inputNeuronValues){
    for (int outputIndex = 0; outputIndex < weights.length; outputIndex++) {
      for (int inputIndex = 0; inputIndex < weights[1].length; inputIndex++) {
        weights[outputIndex][inputIndex] = weights[outputIndex][inputIndex]
                + (learningRate * outputNeuronErrors[outputIndex] * inputNeuronValues[inputIndex]);
      }
    }
  }

  public double[] getOutputNeuronWeights(int neuronId){
    return weights[neuronId];
  }

  public double[] getInputNeuronWeights(int neuronId){
    double[] column = new double[weights.length];
    IntStream.range(0,weights.length).forEach(i -> column[i] = weights[i][neuronId]);
    return  column;
  }

  public void setWeights(double[][] weights) {
    this.weights = weights;
  }

  public double[][] getWeights() {
    return weights;
  }
}
