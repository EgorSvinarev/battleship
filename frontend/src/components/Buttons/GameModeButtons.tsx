import * as React from 'react';
import Button from '@mui/material/Button';
import '../../styles/componentsStyles/gameModeButtons.scss';
import { Link } from 'react-router-dom';

const GameModeButtons: React.FC = () => {
  return (
    <div className="gameModeButtons">
      <Link to={'/play'}><Button className="gameButton">Play</Button></Link>
    </div>
  );  
};

export default GameModeButtons;
