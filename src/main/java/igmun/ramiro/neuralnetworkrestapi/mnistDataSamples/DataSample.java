package igmun.ramiro.neuralnetworkrestapi.mnistDataSamples;

public interface DataSample {

  double[] getInputValues();
  double[] getTargetOutput();
  int getId();
}
