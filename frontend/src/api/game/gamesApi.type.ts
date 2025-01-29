export interface ICreateGameRequest {
  userId: number;
  isWinner: boolean;
}

export interface IGame {
  userId: number;
  isWinner: boolean;
  createdDate: string;
}