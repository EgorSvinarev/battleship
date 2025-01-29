import { getClient } from '../axios';
import {ICreateGameRequest, IGame} from "./gamesApi.type";

export const GamesApi = {
  async createGame(data: ICreateGameRequest) {
    const response = await getClient().post<IGame>('/game/create', data)
    return response.data;
  }
}