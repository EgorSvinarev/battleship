import React from "react";
import { Link } from "react-router-dom";
import "../../styles/componentsStyles/authForm.scss";
import {LoginProps} from "../../types/Login.type";
import {Button, Form, Input} from 'antd';

export default function AuthForm({ postLogin, fetchStatus }: LoginProps) {
  return (
    <div className="form">
      {fetchStatus.error && (
        <p data-testid={'loginError'} className="error">
          Некорректные данные аутентификации
        </p>
      )}
      <h2>Авторизация</h2>
      <Form
        onFinish={postLogin}
      >
        <Form.Item
          name="email"
        >
          <div className="form-item">
            <label htmlFor="email-input">Электронная почта</label>
            <Input type={'email'} placeholder="address@mail.ru"/>
          </div>
        </Form.Item>
        <Form.Item
          name="password"
        >
          <div className="form-item">
            <label htmlFor="password-input">Пароль</label>
            <Input type={'password'} placeholder="************"/>
          </div>
        </Form.Item>
        <Form.Item>
          <div className="form-item">
            <Button
              loading={fetchStatus.isLoading}
              type={'text'}
              htmlType="submit"
            >
              {!fetchStatus.isLoading && 'Войти'}
            </Button>
          </div>
        </Form.Item>
      </Form>
      {/*<div className="form-item">*/}
      {/*  <Link to="/reset-password">Забыли пароль?</Link>*/}
      {/*</div>*/}
    </div>
  );
};
