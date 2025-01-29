import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ResetPasswordPage from "./pages/ResetPasswordPage";
import PVPBattlePage from "./pages/PVPBattlePage";
import PVEBattlePage from "./pages/PVEBattlePage";
import UserPage from "./pages/UserPage";
import RankInfoPage from "./pages/RankInfoPage";
import React from "react";
import {useSelector} from "react-redux";
import {selectIsAuth, selectIsInit} from "./store/auth";
import {WebSocketProvider} from "./hooks/useWebSocket";

interface ProtectedRouteProps {
  children: React.ReactNode;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
  const isAuth = useSelector(selectIsAuth);
  const isInit = useSelector(selectIsInit);

  if (!isAuth && isInit) {
    return <Navigate to="/login" />;
  }

  return <>{children}</>;
};

export const Router: React.FC = () => {
  return (
    <Routes>
      {/* Открытые маршруты */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<RegisterPage />} />
      <Route path="/reset-password" element={<ResetPasswordPage />} />

      {/* Защищённые маршруты */}
      <Route
        path="/"
        element={
          <ProtectedRoute>
            <HomePage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/play"
        element={
          <ProtectedRoute>
            <WebSocketProvider>
              <PVPBattlePage />
            </WebSocketProvider>
          </ProtectedRoute>
        }
      />
      <Route
        path="/pve"
        element={
          <ProtectedRoute>
            <PVEBattlePage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/pve/:ai_lvl"
        element={
          <ProtectedRoute>
            <PVEBattlePage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/me"
        element={
          <ProtectedRoute>
            <UserPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/user/:id"
        element={
          <ProtectedRoute>
            <UserPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/rank-info"
        element={
          <ProtectedRoute>
            <RankInfoPage />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};