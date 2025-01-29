import { getCurrentTime } from "./helpers";

export const TABLE_ROWS = 10;
export const TABLE_COLUMNS = 10;

export const TABLE_LETTERS = ['А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К'];

export enum MESSAGE_TYPE {
  Auth = "AUTHENTICATION",
  Join = "JOIN",
  RoomCreate = "ROOM_CREATE",
  RoomStart = "ROOM_START",
  RoomClose = "ROOM_CLOSE",
  RoomWaitPlayerShot = "ROOM_WAIT_PLAYER_SHOT",
  PlayerReady = "PLAYER_READY",
  PlayerShoot = "PLAYER_SHOOT",
  PlayerShipsPlaced = "PLAYER_SHIPS_PLACED",
};