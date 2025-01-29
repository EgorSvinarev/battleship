import React, { useState } from 'react';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import DraggableBox from './DraggableBox';
import DropArea from './DropArea';

const DragAndDropContainer: React.FC = () => {
  const [droppedBoxId, setDroppedBoxId] = useState<number | null>(null);

  const handleDrop = (id: number) => {
    setDroppedBoxId(id);
  };

  return (
    <DndProvider backend={HTML5Backend}>
      <div style={{ padding: '16px' }}>
        <h2>Пример Drag-and-Drop</h2>
        <div style={{ display: 'flex', gap: '16px' }}>
          <div>
            <h3>Перетаскиваемые элементы</h3>
            <DraggableBox id={1} text="Элемент 1" />
            <DraggableBox id={2} text="Элемент 2" />
          </div>

          <div>
            <h3>Зона для Drop</h3>
            <DropArea onDrop={handleDrop} />
            {droppedBoxId !== null && (
              <p>Элемент с ID {droppedBoxId} перемещён!</p>
            )}
          </div>
        </div>
      </div>
    </DndProvider>
  );
};

export default DragAndDropContainer;
