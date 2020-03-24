import React, { useState, useRef, useEffect } from 'react';
import Answer from './Answer'
import Canvas from './Canvas';
import services from './services';
import CreateNetwork from './CreateNetwork'
import CurrentNetwork from './CurrentNetwork'
import Article from './Article'
import { Container, Row, Col } from 'react-bootstrap'

function App() {

  const canvasRef = useRef(null);
  const [answeredValues, setAnswer] = useState([0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
  const [networkDTO, setNetworkDTO] = useState({
    layers: [],
    learningRate: 0
  });
  const [currentNetwork, setCurrentNetwork] = useState({
    layers: [0, 0, 0],
    learningRate: 0,
    connections: [0],
    accuracy: 0
  });

  // useEffect(() => {

  //   const networkJSON = window.localStorage.getItem('currentNetwork');
  //   if (networkJSON) {
  //     setCurrentNetwork(JSON.parse(networkJSON));
  //   }

  // },[])

  const getData = async () => {
    const canvas = canvasRef.current;
    const data = canvas.getData().replace(/^data:image\/(png|jpeg);base64,/, "");
    const answer = await services.guessOutput(data);
    setAnswer(answer)
    console.log('answer', answer);
  }

  const handleCreate = async () => {
    const network = await services.createNetwork(networkDTO);
    setCurrentNetwork(network);
    window.localStorage.setItem('currentNetwork', JSON.stringify(network));
  }

  const handleTrain = async () => {
    if (currentNetwork.layers[0] === 0) {
      return;
    }
    const trainedNetwork = await services.trainNetwork();
    console.log('trainedNetwork', trainedNetwork);
  }

  const handleGetNetwork = async () => {
    const detailedNetwork = await services.getNetwork();
    console.log('detailedNetwork', detailedNetwork);
  }

  const handleGetAccuracy = async () => {
    if (currentNetwork.layers[0] === 0) {
      return;
    }
    const accuracy = await services.getAccuracy();
    setCurrentNetwork({ ...currentNetwork, accuracy: accuracy })
    window.localStorage.setItem('currentNetwork', JSON.stringify({ ...currentNetwork, accuracy: accuracy }))
    console.log('accuracy', accuracy)
  }

  const windowStyle = {
    backgroundColor: 'white',
    margin: '0.5rem',
    boxShadow: '2px 2px 10px 1px lightgray',
    padding: '1rem'
  }

  const centerElement = {
    width: 'max-content',
    margin: '0 auto 1em',
  }

  return (
    <Container>
      <Row style={centerElement}>
        <h1>Digit Recognition With Neural Network</h1>
      </Row>
      <Row >
        <Col md lg='4' style={{ padding: '1rem' }}>
          <div onMouseUp={getData} ><Canvas style={{...centerElement, backgroundColor:'white'}} ref={canvasRef} width={250} height={250} /></div>
          <Answer style={centerElement} answers={answeredValues}></Answer>
        </Col>
        <Col lg>
          <CreateNetwork style={windowStyle} handleCreate={handleCreate} setNetworkDTO={setNetworkDTO} networkDTO={networkDTO}></CreateNetwork>
          <CurrentNetwork style={windowStyle} currentNetwork={currentNetwork} handleTrain={handleTrain} handleGetAccuracy={handleGetAccuracy} />
        </Col>
      </Row>
      <Row>
        <Col style={windowStyle}>
          <Article/>
        </Col>
      </Row>
    </Container>
  )
}

export default App;
