import React, {createRef, useRef, useEffect} from 'react';

const Number = ({number, index}) => {
  const canvasRef = createRef();

  useEffect(() => {
    const canvas = canvasRef.current;
    const context = canvas.getContext('2d');
    context.fillStyle = 'dodgerblue';
    context.clearRect(0,0,250,10);
    context.fillRect(0, 0, 230 * number, 10);
  }, [canvasRef])
  
  return (
      <div style={{marginBottom:5}}width='100%'>{index} <canvas style={{backgroundColor:'white', borderStyle:'solid', borderColor:'lightgrey', borderWidth:1}}ref={canvasRef} height='10px' width='220'></canvas></div>
  )
}

const Answer = ({ answers }) => {

  const style = {
    width: 254,
    margin: '0 auto',
    padding: '0.5em',
  }

  const numbers = () => answers.map((number, index) => {
      
      return (
        <Number key={index} number={number} index={index}/>
      )
    })
  
  return <div style={style}>{numbers()}</div>

}

export default Answer;
