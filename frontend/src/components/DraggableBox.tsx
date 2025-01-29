import React from 'react';
import { useDrag } from 'react-dnd';

interface DraggableBoxProps {
  id: number;
  text: string;
}

const DraggableBox: React.FC<DraggableBoxProps> = ({ id, text }) => {
  const [{ isDragging }, drag] = useDrag(() => ({
    type: 'BOX',
    item: { id },
    collect: (monitor) => ({
      isDragging: monitor.isDragging(),
    }),
  }));

  return (
    <div
      ref={drag}
      style={{
        opacity: isDragging ? 0.5 : 1,
        backgroundColor: 'lightblue',
        padding: '16px',
        margin: '8px',
        cursor: 'move',
        borderRadius: '4px',
        border: '1px solid gray',
      }}
    >
      {text}
    </div>
  );
};

export default DraggableBox;
