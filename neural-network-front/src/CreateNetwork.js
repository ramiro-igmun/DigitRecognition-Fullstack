import React, { useState } from 'react';
import { Badge, Container, Col, Row, Button, FormControl, InputGroup, Form, FormGroup } from 'react-bootstrap';


const Title = () => {

  return (
    <Row >
      <Col><h4 className='border-bottom border-secondary'>Create New Neural Network</h4></Col>
    </Row>
  )
}

const CreateBody = ({ setLayers, handleNewLayer, handleAddLayer, handleLearningRateChange, handleCreate }) => {

  const resetAndCreate = event => {
    setLayers([])
    handleCreate()
  }


  return (
    <Row className='pt-4 pl-5 pr-5'>
      <label htmlFor="hiddenLayers">Add new hidden layer:</label>
        <InputGroup size='sm' className="mb-3">
          <Form.Control onChange={handleNewLayer} id="hiddenLayers" placeholder="Number of neurons" aria-label="Number of neurons" />
          <InputGroup.Append>
            <Button onClick={handleAddLayer} variant="outline-secondary">Add</Button>
          </InputGroup.Append>
        </InputGroup>

      <label htmlFor="learningRate">Set a learning rate:</label>
      <FormControl id='learningRate' size='sm' placeholder='Learning rate' onChange={handleLearningRateChange}></FormControl>
      <Button onClick={resetAndCreate} className='mt-4' variant='primary' size='sm'>Create Network</Button>
    </Row>
  )
}

const Layers = ({ layers }) => {

  const layerList = () => layers.map((layer, index) =>
    <span key={index} > <Badge variant='secondary'>{layer}</Badge> </span>
  )

  const style = {
    borderStyle: 'solid',
    borderColor: 'lightgray',
    borderRadius: 4,
    borderWidth: 1,
    padding: '0.5em',
    marginRight: '0.5em',
    fontSize: '0.8em',
    display: 'inline-block',
    backgroundColor: 'white'
  }

  return (
    <Row style={{ backgroundColor: 'whitesmoke' }} className='p-2 mt-3 justify-content-md-center'>
      <Col xs='auto'><span style={{ ...style, float: 'right' }}>Input Layer <Badge variant='primary'>784</Badge></span></Col>
      <Col sm='auto'><span style={style}>Hidden Layers{layerList()}</span></Col>
      <Col xs='auto'><span style={style}>Output Layer <Badge variant='success'>10</Badge></span></Col>
    </Row>
  )
}


const CreateNetwork = ({ handleCreate, style, setNetworkDTO, networkDTO }) => {

  const [newLayer, setNewLayer] = useState();
  const [layers, setLayers] = useState([]);

  const handleNewLayer = event => {
    setNewLayer(event.target.value)
  }

  const handleAddLayer = () => {
    setNetworkDTO({ ...networkDTO, layers: layers.concat(newLayer) })
    setLayers(layers.concat(newLayer))
  }

  const handleLearningRateChange = event => {
    setNetworkDTO({ ...networkDTO, learningRate: event.target.value })
  }

  return (
    <Container style={style}>
      <Title handleCreate={handleCreate} />
      <CreateBody
        handleNewLayer={handleNewLayer}
        handleAddLayer={handleAddLayer}
        handleLearningRateChange={handleLearningRateChange}
        setLayers={setLayers}
        handleCreate={handleCreate}
      />
      <Layers layers={layers} />
    </Container>
  )
}

export default CreateNetwork;
