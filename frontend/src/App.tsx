import React, {StrictMode, useEffect} from 'react';
import {BrowserRouter} from 'react-router-dom';
import './styles/App.scss';
import {Provider, useSelector} from 'react-redux';
import {ConfigProvider} from 'antd';
import store, {useAppDispatch} from "./store";
import {authActions, selectIsAuth, selectIsInit} from "./store/auth";
import { Router } from "./Router";

function App() {
  const dispatch = useAppDispatch();

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      dispatch(authActions.setIsAuth(true));
    }
    dispatch(authActions.setIsInit(true));
  }, [dispatch]);

  return <Router />;
}

export default function AppContainer() {
  return (
    <ConfigProvider>
      <Provider store={store}>
        <BrowserRouter>
          <App/>
        </BrowserRouter>
      </Provider>
    </ConfigProvider>
  );
}