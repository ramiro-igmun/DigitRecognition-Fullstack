package igmun.ramiro.neuralnetworkrestapi.mnistDataSamples;

import java.io.Serializable;
import java.util.Arrays;

public class NumberDataSample implements DataSample, Serializable {

  private final double[] inputValues;
  private final double[] targetOutput = new double[10];
  private final int id;
  private static final long serialVersionUID = 1234567L;

  public NumberDataSample(double[] rawData){
    inputValues = normalizeRawData(rawData);
    id = (int)rawData[0];
    targetOutput[id] = 1;
  }

  private double[] normalizeRawData(double[] rawData) {
    double[] inputRaw = Arrays.copyOfRange(rawData,1,rawData.length);
    return Arrays.stream(inputRaw).map(v -> v/255).toArray();
  }

  @Override
  public double[] getInputValues() {
    return inputValues;
  }

  @Override
  public double[] getTargetOutput() {
    return targetOutput;
  }

  @Override
  public int getId() {
    return id;
  }
}
