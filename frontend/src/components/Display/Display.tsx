import React from "react";
import '../../styles/componentsStyles/display.scss';
import Battlefield from "./Battlefield/Battlefield";
import {useWebSocket} from "../../hooks/useWebSocket";
import { Typography } from "antd"

export default function Display() {
  const { isRoomCreated, isCurrentPlayerShot } = useWebSocket();

  return (
    <div className="display">
      <div className="playerTurnPlaceholder">
        {isRoomCreated && (
          <Typography.Title level={2} style={{color:'blue'}}>
            {isCurrentPlayerShot ? "Ваш ход!" : "Ход противника"}
          </Typography.Title>
        )}
      </div>
      <div className="battlefields">
        <Battlefield isOpponent={false}/>
        <Battlefield isOpponent={true}/>
      </div>
    </div>
  );
};