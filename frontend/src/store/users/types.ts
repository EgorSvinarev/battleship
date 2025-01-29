export type UserType = {
  id: number;
  username: string;
  email: string;
  xp: number;
  statistics: UserStatistics;
};

export type UserStatistics = {
  userId: number;
  total: number;
  win: number;
  lost: number;
  winRate: number;
}