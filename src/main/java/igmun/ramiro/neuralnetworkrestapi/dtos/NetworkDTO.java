package igmun.ramiro.neuralnetworkrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkDTO {

  private int[] layers;
  private int[] connections;
  private double learningRate;
  private double accuracy = 0;

  public NetworkDTO(NetworkDTO networkDTO) {
    this.layers = IntStream.range(0,networkDTO.layers.length+2)
            .map(i -> {
              if (i==0) {return 784;}
              if (i==networkDTO.layers.length+1){return 10;}
              else {return networkDTO.layers[i-1];}
            }).toArray();
    this.learningRate = networkDTO.learningRate;
    connections = new int[layers.length - 1];
    for (int i = 0; i < connections.length; i++) {
      connections[i] = layers[i] * layers[i + 1];
    }
  }
}
