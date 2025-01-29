import {createAsyncThunk} from '@reduxjs/toolkit';
import {authApi} from '../../api/auth/authApi';
import {FetchLoginDataProps, FetchRegisterDataProps} from './types';

const postLoginData = createAsyncThunk(
  'auth/postLoginData',
  async ({email, password}: FetchLoginDataProps, {dispatch}) => {
    const response = await authApi.login(email, password);
    localStorage.setItem('accessToken', response.accessToken);
    localStorage.setItem('refreshToken', response.refreshToken);
  },
);

const postRegisterData = createAsyncThunk(
  'auth/postRegisterData',
  async ({username, email, password}: FetchRegisterDataProps, {dispatch}) => {
    await authApi.register(username, email, password);
  },
);

export {postLoginData, postRegisterData};
