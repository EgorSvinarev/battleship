import React from "react";
import { Link } from "react-router-dom";
import "../../styles/componentsStyles/resetPasswordForm.scss";

const ResetPasswordForm: React.FC = () => {
  return (
    <div className="form">
      <h2>Восстановление пароля</h2>
      <form>
        <div className="form-item">
          <label htmlFor="email-input">Электронная почта</label>
          <input type="email" placeholder="address@mail.ru" id="email-input" />
        </div>
        <div className="form-item">
          <button type="submit" className="form-button">
            Сбросить пароль
          </button>
          <Link to="/login" type="button" className="form-button">
            Отмена
          </Link>
        </div>
      </form>
    </div>
  );
};

export default ResetPasswordForm;
