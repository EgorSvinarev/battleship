import {createAsyncThunk} from '@reduxjs/toolkit';
import {getUserAdapter, getUsersAdapter} from './adapters';
import {UsersApi} from '../../api/users/usersApi';
import {IUsersRequest} from "../../api/users/usersApi.type";

const getUser = createAsyncThunk('user', async (id: number) => {
  const response = await UsersApi.getUser(id);
  return getUserAdapter(response)
});

const getUsers = createAsyncThunk('users', async (data: IUsersRequest) => {
  const response = await UsersApi.getUsers(data);
  return getUsersAdapter(response);
});

export { getUser, getUsers };
