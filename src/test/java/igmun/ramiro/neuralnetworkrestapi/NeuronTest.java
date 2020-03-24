package igmun.ramiro.neuralnetworkrestapi;

import igmun.ramiro.neuralnetworkrestapi.domain.network.Neuron;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeuronTest {

  @Test
  public void testNeuronActivation(){
    Neuron neuron = new Neuron(0,new double[]{0.1,0.8});
    neuron.activate(new double[]{0.35,0.9});
    assertEquals(0.68,(double) Math.round((neuron.getActivation()*100))/100);
  }

  @Test
  public void testCalculateErrorForOutputNeuron(){
    Neuron neuron = new Neuron(0,new double[]{0.3,0.9});
    neuron.activate(new double[]{0.68,0.6637});
    neuron.calculateError(0.5);
    assertEquals(-0.0407,(double) Math.round((neuron.getError()*10000))/10000);
  }

  @Test
  public void testCalculateErrorForHiddenNeuron(){
    Neuron neuron = new Neuron(0,new double[]{0.1,0.8});
    neuron.activate(new double[]{0.35,0.9});
    neuron.calculateError(new double[]{0.272392},new double[]{-0.0406});
    assertEquals(-0.002405,(double) Math.round((neuron.getError()*1000000))/1000000);
  }

}