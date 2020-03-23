package igmun.ramiro.neuralnetworkrestapi.services;

import igmun.ramiro.neuralnetworkrestapi.dtos.NetworkDTO;
import igmun.ramiro.neuralnetworkrestapi.io.CvsReader;
import igmun.ramiro.neuralnetworkrestapi.network.Network;
import igmun.ramiro.neuralnetworkrestapi.utils.ImageToMatrix;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class NetworkService {

  private HttpSession httpSession;

  public NetworkService(HttpSession httpSession) {
    this.httpSession = httpSession;
  }

  public NetworkDTO createNewNetwork(NetworkDTO networkDTO) {
    Network.Builder builder = new Network.Builder(784);
    for (int i = 0; i < networkDTO.getLayers().length ; i++) {
      builder.addHiddenLayer(networkDTO.getLayers()[i]);
    }
    builder.setOutputNeuronLayerSize(10).setLearningRate(networkDTO.getLearningRate());
    Network network = builder.build();
    httpSession.setAttribute("neuralNetwork", network);
    return new NetworkDTO(networkDTO);
  }

  public double calculateNetworkAccuracy(){
    Network network = getNetwork();
    if (network == null){
      return 0;
    }
    return network.calculateAccuracy(new CvsReader().readTestDataSet());
  }

  public Network trainNetwork(){
    Network network = getNetwork();
    if (network != null) {
      network.train(new CvsReader().readTrainingDataSet());
    }
    httpSession.setAttribute("neuralNetwork", network);
    return network;
  }

  public double[] guessOutput(String encoded) throws IOException {
    Network network = getNetwork();
    if (network == null){
      return new double[]{};
    }
    return network.getAnswerArray(test(encoded));
  }

  private Network getNetwork() {
    return (Network) httpSession.getAttribute("neuralNetwork");
  }

  public double[] test(String encoded) throws IOException {
    byte[] decoded = Base64.getDecoder().decode(encoded);
    System.out.println(Arrays.toString(decoded));
    ImageToMatrix imagetoMatrix = new ImageToMatrix(decoded);
    return imagetoMatrix.scaleToMnistSize();
  }
}
