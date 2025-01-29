import React from "react";
import { Link } from "react-router-dom";
import "../../styles/componentsStyles/registerForm.scss";
import {LoginProps, RegisterProps} from "../../types/Login.type";
import {Button, Form, Input} from "antd";

export default function RegisterForm({ postRegister, fetchStatus }: RegisterProps)  {
  return (
    <div className="form">
      {fetchStatus.error && (
        <p data-testid={'loginError'} className="error">
          Некорректные данные
        </p>
      )}
      <h2>Регистрация</h2>
      <Form
        onFinish={postRegister}
      >
        <Form.Item
          name="username"
        >
          <div className="form-item">
            <label htmlFor="username-input">Псевдоним</label>
            <input id="username-input" placeholder="" />
          </div>
        </Form.Item>
        <Form.Item
          name="email">
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
        <div className="form-item">
          <Button
            loading={fetchStatus.isLoading}
            type={'text'}
            htmlType="submit"
          >
            {!fetchStatus.isLoading && 'Регистрация'}
          </Button>
        </div>
      </Form>
      <div className="form-item">
        Уже есть аккаунт?
        <Link to="/login" className="signin-text">
          Войти
        </Link>
      </div>
    </div>
  );
};
