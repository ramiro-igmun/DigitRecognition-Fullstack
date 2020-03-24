package igmun.ramiro.neuralnetworkrestapi.controllers;

import igmun.ramiro.neuralnetworkrestapi.domain.dtos.NetworkDTO;
import igmun.ramiro.neuralnetworkrestapi.domain.network.Network;
import igmun.ramiro.neuralnetworkrestapi.services.NetworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@RestController
public class NetworkController {

  private NetworkService networkService;

  public NetworkController(NetworkService networkService) {
    this.networkService = networkService;
  }

  @PostMapping("/network")
  public ResponseEntity<NetworkDTO> createNewNeuralNetwork(@RequestBody NetworkDTO networkDTO) {
    return ResponseEntity
            .created(URI.create("/network"))
            .body(networkService.createNewNetwork(networkDTO));
  }

  @GetMapping("/network")
  public ResponseEntity<Network> getCurrentNetwork(HttpSession httpSession){
    Network network = (Network)httpSession.getAttribute("neuralNetwork");
    return ResponseEntity
            .of(Optional.ofNullable(network));
  }

  @PostMapping("/network/guess")
  public double[] guessOutput(@RequestBody String data) throws IOException {
    return networkService.guessOutput(data);
  }

  @GetMapping("/network/accuracy")
  public double calculateNetworkAccuracy(){
    return networkService.calculateNetworkAccuracy();
  }

  @PutMapping("network")
  public ResponseEntity<Network> trainNetwork(){
    return ResponseEntity
            .of(Optional.ofNullable(networkService.trainNetwork()));
  }

}
