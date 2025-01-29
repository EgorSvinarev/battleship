import React, {useState, useEffect, useRef, createContext, ReactNode, useContext} from 'react';
import {useSelector} from "react-redux";
import {selectIsAuth, selectIsInit} from "../store/auth";
import {Navigate} from "react-router-dom";

type WebSocketContextType = {
  isReady: boolean;
  isRoomCreated: boolean;
  isCurrentPlayerShot: boolean;
  isGameEnded: boolean;
  isPlayerWon: boolean;
  playerBoardConfig: any[];
  opponentBoardConfig: any[];
  sendMessage: (message: object) => void;
  socketRef: React.RefObject<WebSocket | null>;
};

const WebSocketContext = createContext<WebSocketContextType | undefined>(undefined);

type WebSocketProviderProps = {
  children: ReactNode;
};

const baseUrl = process.env.REACT_APP_WEBSOCKETS_URL;

export const WebSocketProvider: React.FC<WebSocketProviderProps> = ({ children }) => {
  const [isReady, setIsReady] = useState(false);
  const [isRoomCreated, setIsRoomCreated] = useState(false);
  const [isCurrentPlayerShot, setCurrentPlayerShot] = useState(false);
  const [isGameEnded, setGameEnded] = useState(false);
  const [isPlayerWon, setPlayerWon] = useState(false);
  const [playerBoardConfig, setPlayerBoardConfig] = useState<any[]>([]);
  const [opponentBoardConfig, setOpponentBoardConfig] = useState<any[]>([]);
  const socketRef = useRef<WebSocket | null>(null);

  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8081/game');
    socketRef.current = ws;

    ws.onopen = () => {
      console.log('WebSocket connection established');

      // Отправка сообщения AUTH при подключении
      const authMessage = { type: 'AUTHENTICATION', data: {bearerToken: "dasdasdad"} };
      sendMessage(authMessage);
      console.log('AUTH message sent:', authMessage);

      const joinQueueMessage = { type: 'JOIN', data: {} };
      sendMessage(joinQueueMessage);
      console.log('JOIN message sent:', joinQueueMessage);
    };

    ws.onmessage = (event) => {
      const message = JSON.parse(event.data);
      console.log('Message received:', message);

      switch (message.type) {
        case 'ROOM_CREATE': {
          setIsRoomCreated(true);

          const readyMessage = { type: 'PLAYER_READY', data: {} }
          sendMessage(readyMessage);
          break;
        }
        case 'BOARD_CONFIG': {
          setPlayerBoardConfig(message.data.playerBoard);
          setOpponentBoardConfig(message.data.opponentBoard);
          break;
        }
        case 'ROOM_WAIT_PLAYER_SHOT': {
          setCurrentPlayerShot(true);
          break;
        }
        case 'ROOM_SWITCH_TURN': {
          setCurrentPlayerShot(false);
          break;
        }
        case 'PLAYER_WIN': {
          setGameEnded(true);
          setPlayerWon(true);
          break;
        }
        case 'PLAYER_LOSE': {
          setGameEnded(true);
          setPlayerWon(false);
          break;
        }
        case 'ROOM_CLOSE': {
          ws.close();
          break;
        }
      }
    };

    ws.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    ws.onclose = () => {
      console.log('WebSocket connection closed');
      setIsReady(false);
    };

    return () => {
      if (ws.readyState === 1) {
        ws.close();
        socketRef.current = null;
      }
    };
  }, []);

  const sendMessage = (message: object) => {
    if (socketRef.current && socketRef.current.readyState === WebSocket.OPEN) {
      const messageString = JSON.stringify(message);
      socketRef.current.send(messageString);
      console.log('Message sent:', messageString);
    } else {
      console.warn('WebSocket is not open. Message not sent:', message);
    }
  };

  return (
    <WebSocketContext.Provider
      value={{ isReady, isRoomCreated, isCurrentPlayerShot, isGameEnded, isPlayerWon, playerBoardConfig, opponentBoardConfig, sendMessage, socketRef}}>
      {children}
    </WebSocketContext.Provider>
  );
};

export const useWebSocket = () => {
  const context = useContext(WebSocketContext);
  if (!context) {
    throw new Error('useWebSocket must be used within a WebSocketProvider');
  }
  return context;
};