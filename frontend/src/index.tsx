import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.scss'; // Импорт глобальных стилей
import App from './App'; // Импорт компонента App

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement); // Создание корневого элемента
root.render(
  <App />
);
