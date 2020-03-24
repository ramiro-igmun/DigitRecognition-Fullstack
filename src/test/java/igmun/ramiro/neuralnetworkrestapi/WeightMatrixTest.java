package igmun.ramiro.neuralnetworkrestapi;

import igmun.ramiro.neuralnetworkrestapi.network.Neuron;
import igmun.ramiro.neuralnetworkrestapi.network.WeightMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightMatrixTest {

  @Test
  public void testNeuronWeightMatrixConnection() {
    WeightMatrix weightMatrix = new WeightMatrix(3, 2, 1);
    Neuron neuron = new Neuron(0, weightMatrix.getOutputNeuronWeights(0));

    assertArrayEquals(weightMatrix.getOutputNeuronWeights(0), neuron.getWeights());
    weightMatrix.updateWeights(new double[]{0.2, 0.1}, new double[]{1, 2, 3});
    assertArrayEquals(weightMatrix.getOutputNeuronWeights(0), neuron.getWeights());
  }

  @Test
  public void testUpdateWeights() {
    WeightMatrix weightMatrix = new WeightMatrix(2, 2, 1);
    weightMatrix.setWeights(new double[][]{{0.1, 0.8}, {0.4, 0.6}});
    double[] outputNeuronErrors = {-0.002406, -0.007916};
    double[] inputNeuronValues = {0.35, 0.9};
    weightMatrix.updateWeights(outputNeuronErrors, inputNeuronValues);
    assertArrayEquals(new double[][]{{0.09915790000000001, 0.7978346000000001}, {0.3972294, 0.5928756}}
            , weightMatrix.getWeights());
  }

  @Test
  public void testGetInputNeuronWeights(){
    WeightMatrix weightMatrix = new WeightMatrix(2,4,1);
    weightMatrix.setWeights(new double[][]{{1,2},{2,3},{1,4},{3,5}});
    assertArrayEquals(new double[]{2,3,4,5},weightMatrix.getInputNeuronWeights(1));
  }

}