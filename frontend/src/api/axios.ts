import axios from 'axios';
import {logout} from "../helpers";

const baseUrl = process.env.REACT_APP_BASE_URL ?? 'http://147.45.183.148:8080/api';

export const getClient = () => {
  const client = axios.create({
    baseURL: `${baseUrl}`,
    headers: {
      'Content-Type': 'application/json',
      'accept': 'application/json'
    },
  });

  client.interceptors.request.use(function (config) {
    const token = localStorage.getItem('accessToken');
    config.headers.Authorization = token ? `Bearer ${token}` : '';
    return config;
  });

  client.interceptors.response.use(function (response) {
    if (response.status == 403) {
      logout()
    }
    return response;
  });
  return client;
};
