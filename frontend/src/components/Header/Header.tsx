import React from 'react';
import '../../styles/componentsStyles/header.scss';
import {Link} from 'react-router-dom';
import {HeaderProps} from "./Header.type";
import {useSelector} from "react-redux";
import {selectIsAuth} from "../../store/auth";
import {Button, Popover} from "antd";

export function renderLogout(logout: () => void) {
  return (
    <div data-testid={'popoverLogout'}>
      <Button onClick={logout} data-testid={'logoutButton'}>
        {"Да"}
      </Button>
    </div>
  );
}

export default function Header({
 logout,
}: HeaderProps) {

  const isAuth = useSelector(selectIsAuth);

  return (
    <header className="header">
      <div className="logo">
        <Link to="/">Морской бойчик</Link>
      </div>
      <nav className="navigation">
        <ul>
          {isAuth ? (
            <>
              <li>
                <Popover
                  content={() => renderLogout(logout)}
                  title={"Вы уверены, что хотите выйти?"}
                  trigger="click"
                >
                  <Button data-testid={'logoutPopoverButton'} type={'text'}>Выход</Button>
                </Popover>
              </li>
            </>
          ) : (
            <>
              <li>
                <Link to="/signup"><Button>Регистрация</Button></Link>
              </li>
              <li>
                <Link to="/login"><Button>Вход</Button></Link>
              </li>
            </>
          )
          }
        </ul>
      </nav>
    </header>
  )
}
