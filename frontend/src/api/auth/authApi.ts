import { getClient } from '../axios';
import type { ILoginData } from './authApi.type';

export const authApi = {
    async login(email: string, password: string) {
        const response = await getClient().post<ILoginData>('/auth/login', {
            email: email,
            password: password,
        });
        return response.data;
    },

    async register(username: string, email: string, password: string) {
        const response = await getClient().post<void>('/auth/register', {
            username: username,
            email: email,
            password: password,
        });
        return response.data;
    },

    async refresh(accessToken: string, refreshToken: string) {
        const response = await getClient().post<ILoginData>('/auth/refresh', {
            accessToken: accessToken,
            refreshToken: refreshToken,
        })
        return response.data;
    },
};
