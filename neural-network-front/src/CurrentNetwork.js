import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button, Spinner } from 'react-bootstrap';

const ArrayData = ({ array, title, append }) => {
  const layerList = () =>
    array.map((number, index) => {
      let yux = '';
      if (index > 0) {
        yux = '+';
      }
      return <b key={index}><span style={{ color: 'dimgrey' }}>{yux}{number}</span></b>
    })


  return <tr><td>{title}</td><td>{layerList()}<span style={{ fontSize: '0.9rem', color: 'gray' }}> {append}</span></td></tr>

}


const CurrentNetwork = ({ style, currentNetwork, handleTrain, handleGetAccuracy }) => {

  const [training, setTraining] = useState(false);
  const [loadingAccuracy, setLoadingAccuracy] = useState(false);

  const train = () => {
    setTraining(true);
  }

  const getAccuracy = () => {
    setLoadingAccuracy(true);
  }

  useEffect(() => {
    const asyncLoadAccuracy = async () => {
      await handleGetAccuracy();
      setLoadingAccuracy(false);
    }
    if (loadingAccuracy) {
      asyncLoadAccuracy();
    }
  },[loadingAccuracy])

  useEffect(() => {
    const asyncTrain = async () => {
      await handleTrain();
      setTraining(false);
    }
    if (training) {
      asyncTrain();
    }
  }, [training])


  return (
    <>
      <Container style={style}>
        <h4 className='border-bottom border-secondary'>Current Network</h4>
        <Row className='pt-4 pl-4 pr-5'>
          <Col>
            <table>
              <tbody>
                <tr>
                  <td>Input Layer: </td><td><b><span style={{ color: 'blue' }}>{currentNetwork.layers[0]}</span></b> <span style={{ fontSize: '0.9rem', color: 'gray' }}>neurons</span></td>
                </tr>
                <ArrayData array={currentNetwork.layers.slice(1, currentNetwork.layers.length - 1)} title={'Hidden Layers:'} append={'neurons'} />
                <tr>
                  <td>Output Layer:</td><td><b><span style={{ color: 'green' }}>{currentNetwork.layers[currentNetwork.layers.length - 1]}</span></b> <span style={{ fontSize: '0.9rem', color: 'gray' }}>neurons</span></td>
                </tr>
              </tbody>
            </table>
          </Col>
          <Col>
            <table>
              <tbody>
                <tr>
                  <td>Learning rate:</td><td><b><span style={{ color: 'blue' }}>{currentNetwork.learningRate}</span></b></td>
                </tr>
                <ArrayData array={currentNetwork.connections} title={'Connections:'} append={'w'} />
                <tr>
                  <td>Accuracy:</td><td><b><span style={{ color: 'green' }}>{`${Math.round(currentNetwork.accuracy * 100)}%`}</span></b></td>
                </tr>
              </tbody>
            </table>
          </Col>
        </Row>
        <Row >
          <div style={{ paddingLeft: '2.5em', marginTop: '1.5em' }}>
            {training ? <Button size='sm'><Spinner animation='border' size='sm'/> Training...</Button> : <Button onClick={train} size='sm'>Train</Button>}
            {loadingAccuracy ? <Button className='ml-3'  size='sm'><Spinner animation='border' size='sm'/> Calculating...</Button> : <Button onClick={getAccuracy} className='ml-3' size='sm'>Calculate Accuracy</Button>}
          </div>
        </Row>
      </Container>
    </>
  )
}

export default CurrentNetwork;