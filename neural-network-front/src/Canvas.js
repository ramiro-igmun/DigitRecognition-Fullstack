import React, { useCallback, useEffect, useRef, useState, forwardRef, useImperativeHandle } from 'react';

const Canvas = forwardRef(({ width, height, style },ref) => {
  const canvasRef = useRef(null);
  const [isPainting, setIsPainting] = useState(false);
  const [mousePosition, setMousePosition] = useState(undefined);

    const exitAndClear = useCallback(() => {
    setIsPainting(false);
    setMousePosition(undefined);
    const canvas = canvasRef.current;
    const context = canvas.getContext('2d');
      context.clearRect(0,0,width,height);
    }, [width,height]);

  const startPaint = useCallback((event) => {
    const coordinates = getCoordinates(event);
    if (coordinates) {
      exitAndClear();
      setMousePosition(coordinates);
      setIsPainting(true);
    }
  }, [exitAndClear]);

  useEffect(() => {
    if (!canvasRef.current) {
      return;
    }
    const canvas = canvasRef.current;
    canvas.addEventListener('mousedown', startPaint);
    return () => {
      canvas.removeEventListener('mousedown', startPaint);
    };
  }, [startPaint]);

  const paint = useCallback(
    (event) => {
      if (isPainting) {
        const newMousePosition = getCoordinates(event);
        if (mousePosition && newMousePosition) {
          drawLine(mousePosition, newMousePosition);
          setMousePosition(newMousePosition);
        }
      }
    },
    [isPainting, mousePosition]
  );

  useEffect(() => {
    if (!canvasRef.current) {
      return;
    }
    const canvas = canvasRef.current;
    canvas.addEventListener('mousemove', paint);
    return () => {
      canvas.removeEventListener('mousemove', paint);
    };
  }, [paint]);

  const exitPaint = useCallback(() => {
    setIsPainting(false);
    setMousePosition(undefined);
  }, []);

  useEffect(() => {
    if (!canvasRef.current) {
      return;
    }
    const canvas = canvasRef.current;
    canvas.addEventListener('mouseup', exitPaint);
    canvas.addEventListener('mouseleave', exitPaint);
    return () => {
      canvas.removeEventListener('mouseup', exitPaint);
      canvas.removeEventListener('mouseleave', exitPaint);
    };
  }, [exitPaint]);

  const getCoordinates = (event) => {
    if (!canvasRef.current) {
      return;
    }

    const canvas = canvasRef.current;
    const rect = canvas.getBoundingClientRect()
    
    return { x: event.clientX - rect.left, y: event.clientY - rect.top };
  };

  const drawLine = (originalMousePosition, newMousePosition) => {
    if (!canvasRef.current) {
      return;
    }
    const canvas = canvasRef.current;
    const context = canvas.getContext('2d');
    if (context) {
      context.strokeStyle = 'dimgray';
      context.lineJoin = 'round';
      context.lineWidth = 20;
      context.filter = 'blur(3px)'

      context.beginPath();
      context.moveTo(originalMousePosition.x, originalMousePosition.y);
      context.lineTo(newMousePosition.x, newMousePosition.y);
      context.closePath();

      context.stroke();
    }
  };

  const getData = () => {
    if (!canvasRef.current) {
      return;
    }
    const canvas = canvasRef.current;
    return canvas.toDataURL('image/png');
  }

  useImperativeHandle(ref, () => {
    return {getData}
  })

  return <div style={{...style,border:'solid', borderWidth: '1px', borderColor: 'gray'}}><canvas ref={canvasRef} height={height} width={width} /></div>;
});

Canvas.defaultProps = {
  width: window.innerWidth,
  height: window.innerHeight,
};

export default Canvas;