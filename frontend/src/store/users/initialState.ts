import { UserType } from './types';

const usersInitialState = {
  users: [] as Array<UserType>,
  currentUser: null as null | UserType,
  loading: false as boolean,
  page: 0 as number,
};

export default usersInitialState;
