import { UserType } from './types';
import { IUser } from '../../api/users/usersApi.type';

function getUsersAdapter(data: Array<IUser>): Array<UserType> {
  return data.map((el) => {
    return {
      id: el.id,
      username: el.username,
      email: el.email,
      xp: el.xp,
      statistics: {
        userId: el.stats.userId,
        total: el.stats.total,
        win: el.stats.win,
        lost: el.stats.lost,
        winRate: el.stats.winRate,
      }
    };
  });
}

function getUserAdapter(user: IUser): UserType {
  return {
    id: user.id,
    username: user.username,
    email: user.email,
    xp: user.xp,
    statistics: {
      userId: user.stats.userId,
      total: user.stats.total,
      win: user.stats.win,
      lost: user.stats.lost,
      winRate: user.stats.winRate,
    }
  };
}

export { getUsersAdapter, getUserAdapter };
