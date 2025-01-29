import React, {useEffect, useState} from "react";
import "../styles/pageStyles/pvpBattlePage.scss";
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";
import Display from "../components/Display/Display";
import {useWebSocket} from "../hooks/useWebSocket";
import {Button, Modal, Spin} from 'antd';
import {useNavigate} from "react-router-dom";
import {GamesApi} from "../api/game/gamesApi";
import {getUserIdFromToken} from "../helpers";

export default function PVPBattlePage() {
  const {isRoomCreated, isGameEnded, isPlayerWon} = useWebSocket();
  const [isModalVisible, setIsModalVisible] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (isGameEnded) {
      GamesApi.createGame({
        userId: getUserIdFromToken(),
        isWinner: isPlayerWon
      })
      setIsModalVisible(true);
    }
  }, [isGameEnded]);

  const startNewGame = () => {
    setIsModalVisible(false);
    navigate('/play');
    window.location.reload();
  };


  const goToMainMenu = () => {
    setIsModalVisible(false);
    navigate('/')
  };

  return (
    <div className="page">
      <HeaderContainer/>
      <div className="content">
        <Modal
          title={isPlayerWon ? 'Поздравляем! Вы выиграли!' : 'Увы, вы проиграли'}
          open={isModalVisible}
          onCancel={() => setIsModalVisible(false)}
          footer={[
            <Button key="back" onClick={goToMainMenu}>
              Главное меню
            </Button>,
            <Button key="submit" type="primary" onClick={startNewGame}>
              Новая игра
            </Button>,
          ]}
        >
          <p>{isPlayerWon ? 'Вы победили в этой игре!' : 'Попробуйте снова в следующей игре.'}</p>
        </Modal>

        {!isRoomCreated && (
          <div className="overlay">
            <Spin size="large" tip="Loading room..."/>
          </div>
        )}
        <Display/>
      </div>
    </div>
  );
};