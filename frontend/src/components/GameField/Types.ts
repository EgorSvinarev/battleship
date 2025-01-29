export const GAME_FIELD_SIZE_X = 10;
export const GAME_FIELD_SIZE_Y = 10;

export type GameFieldCellType = "empty" | "wounded" | "missed" | "ship";
export type GameFieldRow = Array<GameFieldCellType>;
export type GameField = Array<GameFieldRow>;

export interface GameFieldUIProps {
  field: GameField;
  onCellClick: (event: {
    x: number;
    y: number;
    type: GameFieldCellType;
  }) => void;
}

export function createEmptyGameField(): GameField {
  let result: GameField = [];
  for (let i = 0; i < GAME_FIELD_SIZE_X; i++) {
    let row: GameFieldRow = [];
    for (let j = 0; j < GAME_FIELD_SIZE_Y; j++) {
      row.push("empty");
    }
    result.push(row);
  }
  return result;
}
