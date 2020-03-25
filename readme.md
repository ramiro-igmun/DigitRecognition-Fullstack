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
  
    
 
