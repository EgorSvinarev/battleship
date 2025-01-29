import {IBattlefieldProps} from "../../../types/Battlefield.type";
import '../../../styles/componentsStyles/battlefield.scss';
import {TABLE_COLUMNS, TABLE_LETTERS, TABLE_ROWS} from "../../../constants";
import {useWebSocket} from "../../../hooks/useWebSocket";

export default function Battlefield({isOpponent}: IBattlefieldProps) {

  const {isCurrentPlayerShot, playerBoardConfig, opponentBoardConfig, sendMessage} = useWebSocket()

  const handleCellClick = (rowIndex: number, colIndex: number) => {
    if (isCurrentPlayerShot) {
      const message = { type: 'PLAYER_SHOT', data: {x: rowIndex, y: colIndex} };
      sendMessage(message)
    }
  };

  function getCellClassname(rowIndex: number, colIndex: number, board: any[]) {
    if (board && board.length == TABLE_ROWS) {
      switch (board[rowIndex][colIndex]) {
        case ('S'): {
          return 'ship'
        }
        case ('O'): {
          return 'miss'
        }
        case('X'): {
          return 'hit'
        }
        default: {
          return ''
        }
      }
    }
    return ''
  }

  return (
    <div className="battlefield">
      <table className="battlefield_table">
        <thead>
        <tr>
          <th></th>
          {TABLE_LETTERS.map((letter, index) => (
            <th key={index}>{letter}</th>
          ))}
        </tr>
        </thead>
        <tbody>
        {Array.from({length: TABLE_ROWS}).map((_, rowIndex) => (
          <tr key={rowIndex}>
            <th>{rowIndex + 1}</th>

            {isOpponent ? (
              <>
                {Array.from({ length: TABLE_COLUMNS }).map((_, colIndex) => (
                  <td
                    key={colIndex}
                    className={getCellClassname(rowIndex, colIndex, opponentBoardConfig) + ' battlefield_cell_highlight-on-hover'}
                    onClick={() => handleCellClick(rowIndex, colIndex)}
                  ></td>
                ))}
              </>
            ) : (
              <>
                {Array.from({length: TABLE_COLUMNS}).map((_, colIndex) => (
                  <td
                    key={colIndex}
                    className={getCellClassname(rowIndex, colIndex, playerBoardConfig)}>
                  </td>
                ))}
              </>
            )}
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  );
};