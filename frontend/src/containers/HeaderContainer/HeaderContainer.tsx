import Header from "../../components/Header/Header";
// import {logout} from "../../helpers";
import {useNavigate} from "react-router-dom";
import {useAppDispatch} from "../../store";
import {authActions} from "../../store/auth";

export default function HeaderContainer() {

  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  function logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    dispatch(authActions.setIsAuth(false));
    navigate('/login');
  }

  return (
    <Header
      logout={logout}
    />
  );
}