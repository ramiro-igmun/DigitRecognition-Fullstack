import React from 'react';
import { Container, Col, Row } from 'react-bootstrap';

const Article = (props) => {
  return (
    <article>
      <section>
        <header>
          <h4 className='border-bottom border-secondary mb-4'>How does it work?</h4>
        </header>
        <ul style={{ listStyleType: 'none' }}>
          <li>
            <h5>Network Creation</h5>
            <p>First you have to create a new Neural Network. The input and output layers are fixed, since in this case, the input is always going
          to be a 28x28(784 pixels) matrix gotten from the drawing canvas and the output must be number between 0 and 9.<br />
          You can enter any number of hidden layers with any number of neurons each, and experiment with the results. The bigger the layers, the
          longer it will take to train the network.
            </p>
          </li>
          <li>
            <h5>Training the Network</h5>
            <p>The training is made using the <a href='https://en.wikipedia.org/wiki/MNIST_database'>MNIST dataset.</a> It goes through 60.000 samples, comparing
            the guessed output with the expected one and adjusting the network accordingly<br />
            You can calculate the network's accuracy at any moment. It uses the test samples of the MNIST dataset and calculates the deviation between the expected
            outputs and the guessed ones.</p>
          </li>
        </ul>
        <p style={{ color: 'gray', fontSize: '0.8em' }}>Note: the MNIST dataset numbers are centered. For the guessing to work correctly, it's best to draw the numbers as centered as possible in the canvas.</p>

      </section>
      <section>
        <header>
          <h4 className='border-bottom border-secondary mb-4'>The Network</h4>
        </header>
        <Container>
          <Row>
            <Col lg='7'>
              <p>An artificial neural network is an interconnected group of nodes, inspired by a simplification of neurons in a brain. Here, each circular node represents an artificial neuron and an arrow represents a connection from the output of one artificial neuron to the input of another.</p>
              <ul style={{ listStyleType: 'none' }}>
                <li><b>Neurons: </b>They receive input, combine the input with their internal state (activation) using an activation function, and produce output using an output function. The initial inputs are external data, in this case the matrix representing the image of a number. The ultimate outputs accomplish the task, in this case, recognizing the nuber.</li>
                <li><b>Connections and Weights: </b>The network consists of connections, each connection providing the output of one neuron as an input to another neuron. Each connection is assigned a weight that represents its relative importance. A given neuron can have multiple input and output connections</li>
              </ul>
            </Col>
            <Col>
              <img src='https://upload.wikimedia.org/wikipedia/commons/4/46/Colored_neural_network.svg' alt='artificial neural network' />
            </Col>
          </Row>
        </Container>
      </section>
      <section>
        <header>
          <h4 className='border-bottom border-secondary mb-4'>About the Project</h4>
        </header>
        <p>The project consists on a Rest API backend, that holds the neural network architecture, and a frontend, that works just as a GUI to communicate with the API.</p>
        <ul>
          <li>
            <h5>Backend</h5>
            <p>The neural network architecture is built in plain Java, without the use of any additional libraries. On top of the Neural Network, I have built a Rest API using Spring Boot
            with Spring Web.
            </p>
          </li>
          <li>
            <h5>Frontend</h5>
            <p>The front end is a single page application built using ReactJS. In addition to having an UI for the creation and training of a Neural Network, it has a canvas that can get
            the handwritten digits entered by the user and send the data to the Backend for proccessing.
            </p>
          </li>
        </ul>
      </section>
    </article>
  )
}

export default Article;