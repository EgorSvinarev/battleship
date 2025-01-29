import moment from "moment";
import { columnLabel } from "./constants";
import {jwtDecode} from "jwt-decode";
import {useNavigate} from "react-router-dom";
import {useAppDispatch} from "./store";
import {authActions} from "./store/auth";

export const getCurrentTime = () => moment().format("LTS");

export const getUserIdFromToken = () => {
  const token = localStorage.getItem("accessToken");

  if (token) {
    const decoded = jwtDecode(token);
    return decoded.userId
  }
}

export function logout () {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  dispatch(authActions.setIsAuth(false));
  navigate('/login');
};
