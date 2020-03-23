package igmun.ramiro.neuralnetworkrestapi.network;

import java.util.stream.IntStream;

public class Neuron {
  private final int id;
  private double[] weights;
  private double activation = 0;
  private double error = 0;

  public Neuron(int id, double[] weights) {
    this.id = id;
    this.weights = weights;
  }

  public void activate(double[] inputLayer) {
    activation = sigmoid(multiplyByWeightsAndAdd(inputLayer));
  }

  private double sigmoid(double number) {
    return 1 / (1 + Math.pow(Math.E, (-1 * number)));
  }

  private double multiplyByWeightsAndAdd(double[] inputLayer) {
    return IntStream.range(0, inputLayer.length).asDoubleStream()
            .reduce(0, (accumulated, index) -> accumulated +
                    (inputLayer[(int) index] * weights[(int) index]));
  }

  public void calculateError(double targetOutput) {
    error = activation * (1 - activation) * (targetOutput - activation);
  }

  public void calculateError(double[] outputWeights, double[] outputErrors) {
    double backPropagatedError = 0;
    for (int i = 0; i < outputWeights.length; i++) {
      backPropagatedError += outputErrors[i] * outputWeights[i];
    }
    error = activation * (1 - activation) * (backPropagatedError);
  }

  public double getError() {
    return error;
  }

  public double getActivation() {
    return activation;
  }

  public int getId() {
    return id;
  }

  public void setWeights(double[] weights){
    this.weights = weights;
  }

  public double[] getWeights() {
    return weights;
  }
}
