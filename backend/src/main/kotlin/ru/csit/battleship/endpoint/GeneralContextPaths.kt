package ru.csit.battleship.endpoint

object AuthPath {

    /**
     * [ru.csit.battleship.endpoint.auth.AuthController.login]
     */
    const val AUTH_LOGIN_PATH = "/auth/login"

    /**
     * [ru.csit.battleship.endpoint.auth.AuthController.register]
     */
    const val AUTH_REGISTER_PATH = "/auth/register"

    /**
     * [ru.csit.battleship.endpoint.auth.AuthController.refresh]
     */
    const val AUTH_REFRESH_TOKENS_PATH = "/auth/refresh"
}

object UserPath {

    /**
     * [ru.csit.battleship.endpoint.user.UserController.getById]
     */
    const val USER_PATH = "/user/{userId}"

    /**
     * [ru.csit.battleship.endpoint.user.UserController.getAll]
     */
    const val USERS_PATH = "/users"
}

object GamePath {

    /**
     * [ru.csit.battleship.endpoint.game.GameController.create]
     */
    const val GAME_CREATE_PATH = "/game/create"
}