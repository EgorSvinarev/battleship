export interface ILoginData {
    email: string;
    password: string;
  }
  export interface LoginProps {
    postLogin: (data: ILoginData) => void;
    fetchStatus: {
      isLoading: boolean;
      error: string;
    };
  }

export interface IRegisterData {
  username: string;
  email: string;
  password: string;
}
export interface RegisterProps {
  postRegister: (data: IRegisterData) => void;
  fetchStatus: {
    isLoading: boolean;
    error: string;
  };
}
  