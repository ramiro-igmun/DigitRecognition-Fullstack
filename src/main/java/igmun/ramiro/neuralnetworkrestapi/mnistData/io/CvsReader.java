package igmun.ramiro.neuralnetworkrestapi.mnistData.io;



import igmun.ramiro.neuralnetworkrestapi.mnistData.mnistDataSamples.DataSample;
import igmun.ramiro.neuralnetworkrestapi.mnistData.mnistDataSamples.NumberDataSample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CvsReader {

  public List<DataSample> readTrainingDataSet(){
    try {
      return readDataSet("mnist_train.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  public List<DataSample> readTestDataSet(){
    try {
      return readDataSet("mnist_test.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  public List<DataSample> readDataSet(String path) throws FileNotFoundException {
    File file = new File(path);
    Scanner reader = new Scanner(file);
    List<DataSample> dataSamples = new ArrayList<>();

    while(reader.hasNextLine()) {
      String stringData = reader.nextLine();
      double[] data = Arrays.stream(stringData.split(",")).mapToDouble(Double::valueOf).toArray();
      dataSamples.add(new NumberDataSample(data));
    }

    reader.close();
    return dataSamples;
  }
}
