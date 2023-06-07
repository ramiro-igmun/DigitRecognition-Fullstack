# Digit Recognition with Deep Neural Network 
The project consists on a Rest API backend, that holds the neural network architecture, and a frontend, that works just as a GUI to communicate with the API. [Link to deployed App](https://digit-recognition-fullstack.herokuapp.com/)


  ### Running The App:
  
   To run the program you must first __uncompress the mnist_test and mnist_train zip files__ in the working directory.

   You can run the app from the command line after installing Maven on your computer with the following command
   from the project folder:

   `mvn spring-boot:run`
   
   To access the app navigate to `http://localhost:8080`
      
   Press `Ctrl+c` to stop it.
 
 ### Structure 
  
  * __Backend__
  
    The neural network architecture is built in plain Java, without the use of any additional libraries. On top of the Neural Network, I
    have built a REST API using Spring Boot with Spring Web.

  * __Frontend__
  
    The front end is a single page application built using ReactJS. In addition to having an UI for the creation and training of a
    Neural Network, it has a canvas that can get the handwritten digits entered by the user and send the data to the Backend for processing.
     
### The Network
   
An artificial neural network is an interconnected group of nodes, inspired by a simplification of neurons in a brain. Here, each circular node represents an artificial neuron and an arrow represents a connection from the output of one artificial neuron to the input of another.
   
    
  * __Neurons:__ They receive input, combine the input with their internal state (activation) using an activation function, and produce output using an output function. The initial inputs are external data, in this case the matrix representing the image of a number. The ultimate outputs accomplish the task, in this case, recognizing the nuber.
  
  * __Connections and Weights:__ The network consists of connections, each connection providing the output of one neuron as an input to another neuron. Each connection is assigned a weight that represents its relative importance. A given neuron can have multiple input and output connections.
  
  ![alt text](https://www.researchgate.net/publication/299474560/figure/fig6/AS:349583008911366@1460358492284/An-example-of-a-deep-neural-network-with-two-hidden-layers-The-first-layer-is-the-input.png "Neural Network")
 
