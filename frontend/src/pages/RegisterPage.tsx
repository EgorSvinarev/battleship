import React from "react";
import {postRegisterData} from '../store/auth';
import RegisterForm from "../components/Forms/RegisterForm";
import "../styles/pageStyles/signupPage.scss";
import {IRegisterData} from '../types/Login.type';
import {useAppDispatch} from "../store";
import {selectFetchStatus} from "../store/auth";
import {useSelector} from "react-redux";
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";
import {useNavigate} from "react-router-dom";

const RegisterPage: React.FC = () => {
  const dispatch = useAppDispatch();
  const fetchStatus = useSelector(selectFetchStatus);
  const navigate = useNavigate();
  const postRegister = async (data: IRegisterData) => {
    const resultAction = await dispatch(postRegisterData(data))
    if (postRegisterData.fulfilled.match(resultAction)) {
      navigate('/');
    }
  };

  return (
    <div className="page">
      <HeaderContainer/>
      <main>
        <RegisterForm postRegister={postRegister} fetchStatus={fetchStatus}/>
      </main>
      {/* <Footer /> */}
    </div>
  );
};

export default RegisterPage;
