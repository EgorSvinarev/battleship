import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import appInitialState from './initialState';
import { AnyObject } from 'antd/es/_util/type';

export enum ModalType {
    CREATE = 'create',
    UPDATE = 'update',
    FILTER = 'filter',
    VIEWING = 'viewing',
    COLUMNS = 'columns',
  }
  
  export interface IModalState {
    isOpen: boolean;
    modalType: null | ModalType;
    initialData: null | AnyObject;
    title: string;
  }

const appSlice = createSlice({
  name: 'app',
  initialState: appInitialState,
  reducers: {
    setIsModalOpen: (state, action: PayloadAction<IModalState>) => {
      state.modalState = action.payload;
    },
    setAlert: (
      state,
      action: PayloadAction<{ message: string; isError: boolean }>,
    ) => {
      state.alert = action.payload;
    },
  },
});

export default appSlice;
