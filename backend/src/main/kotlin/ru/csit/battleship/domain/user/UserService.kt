package ru.csit.battleship.domain.user

import ru.csit.battleship.domain.user.dto.UserDto
import ru.csit.battleship.domain.user.dto.UsersRequest

interface UserService {

    fun getById(userId: Long): UserDto

    fun getAll(request: UsersRequest): List<UserDto>
}