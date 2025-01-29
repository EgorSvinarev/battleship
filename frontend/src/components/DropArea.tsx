import React from 'react';
import { useDrop } from 'react-dnd';
import DraggableBox from './DraggableBox';

interface DropAreaProps {
  onDrop: (id: number) => void;
}

const DropArea: React.FC<DropAreaProps> = ({ onDrop }) => {
  const [{ isOver }, drop] = useDrop(() => ({
    accept: 'BOX',
    drop: (item: { id: number }) => onDrop(item.id),
    collect: (monitor) => ({
      isOver: monitor.isOver(),
    }),
  }));

  return (
    <div
      ref={drop}
      style={{
        backgroundColor: isOver ? 'lightgreen' : 'lightgray',
        height: '200px',
        width: '100%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: '4px',
        border: '2px dashed gray',
      }}
    >
      {isOver ? 'Отпустите здесь' : 'Перетащите элемент сюда'}
    </div>
  );
};

export default DropArea;
