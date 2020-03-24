package igmun.ramiro.neuralnetworkrestapi.mnistData.mnistDataSamples;

public interface DataSample {

  double[] getInputValues();
  double[] getTargetOutput();
  int getId();
}
