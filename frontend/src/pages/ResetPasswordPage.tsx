import React from "react";
//import Footer from "../components/Footer";
import ResetPasswordForm from "../components/Forms/ResetPasswordForm";
import "../styles/pageStyles/resetPasswordPage.scss";
import HeaderContainer from "../containers/HeaderContainer/HeaderContainer";

const ResetPasswordPage: React.FC = () => {
  return (
    <div className="page">
      <HeaderContainer/>
      <main>
        <ResetPasswordForm/>
      </main>
      {/* <Footer /> */}
    </div>
  );
};

export default ResetPasswordPage;
