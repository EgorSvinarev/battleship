import { getClient } from '../axios';
import {IUser, IUsersRequest} from './usersApi.type';

export const UsersApi = {
    async getUsers(data: IUsersRequest) {
        const response = await getClient().post<Array<IUser>>('/users', data);
        return response.data;
    },

    async getUser(id: number) {
        const response = await getClient().get<IUser>(`/user/${id}`);
        return response.data;
    },
};
