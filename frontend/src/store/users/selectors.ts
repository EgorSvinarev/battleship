import { RootState } from '../index';

function selectUsers(state: RootState) {
  return state.users.users;
}

function selectCurrentUser(state: RootState) {
  return state.users.currentUser;
}

function selectIsUserLoading(state: RootState) {
  return state.users.loading;
}

function selectPage(state: RootState) {
  return state.users.page;
}

export { selectUsers, selectCurrentUser, selectIsUserLoading, selectPage };
