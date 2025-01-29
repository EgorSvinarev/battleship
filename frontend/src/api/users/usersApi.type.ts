export interface IUser {
  id: number;
  username: string;
  email: string;
  xp: number;
  gameHistory: Array<any>
  stats: IStatistics
}

export interface IStatistics {
  userId: number;
  total: number;
  win: number;
  lost: number;
  winRate: number;
}

export interface IUsersRequest {
  paging: {
    offset: number,
    limit: number;
  }
}