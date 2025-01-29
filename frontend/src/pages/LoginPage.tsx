import React from "react";
import {useSelector} from 'react-redux';
import {postLoginData} from '../store/auth';
import AuthForm from "../components/Forms/AuthForm";
import "../styles/pageStyles/loginPage.scss";
import {ILoginData} from '../types/Login.type';
import {useAppDispatch} from "../store";
import {selectFetchStatus, selectIsAuth} from "../store/auth";
import {useNavigate} from 'react-router-dom';
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";

const LoginPage: React.FC = () => {
  const dispatch = useAppDispatch();
  const fetchStatus = useSelector(selectFetchStatus);
  const navigate = useNavigate();
  const postLogin = async (data: ILoginData) => {
    const resultAction = await dispatch(postLoginData(data));
    if (postLoginData.fulfilled.match(resultAction)) {
      navigate('/');
    }
  };

  const isAuth = useSelector(selectIsAuth);

  if (isAuth) {
    navigate('/');
  }

  return (
    <div className="page">
      <HeaderContainer/>
      <main>
        <AuthForm postLogin={postLogin} fetchStatus={fetchStatus}/>
      </main>
    </div>
  );
};

export default LoginPage;
