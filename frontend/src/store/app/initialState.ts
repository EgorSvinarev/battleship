import { AnyObject } from "antd/es/_util/type";

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

const appInitialState = {
  modalState: {
    isOpen: false,
    modalType: null,
    initialData: null,
    title: '',
  } as IModalState,
  alert: {
    message: '',
    isError: false,
  },
};

export default appInitialState;
