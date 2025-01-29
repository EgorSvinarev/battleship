import { Card } from "@mui/material";
import React from "react";
import { GameFieldUIProps } from "./Types";
import "../../styles/componentsStyles/gameField.scss";

const GameFieldUI: React.FC<GameFieldUIProps> = ({ field, onCellClick }) => {
  return (
    <Card>
      {field.map((row, y) => (
        <div key={y} className="game-field-row">
          {row.map((cell, x) => (
            <button
              key={`${x} ${y}`}
              className={"game-field-cell " + cell}
              onClick={() => onCellClick({ x, y, type: cell })}
            ></button>
          ))}
        </div>
      ))}
    </Card>
  );
};

export default GameFieldUI;
