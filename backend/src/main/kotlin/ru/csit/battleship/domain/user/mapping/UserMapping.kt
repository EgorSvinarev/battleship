package ru.csit.battleship.domain.user.mapping

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import ru.csit.battleship.domain.user.dto.UserDto
import ru.csit.battleship.entity.user.User
import ru.csit.battleship.domain.game.mapping.toDto
import ru.csit.battleship.domain.game.mapping.toStatistics
import ru.csit.battleship.domain.user.dto.UsersRequest

fun User.toDto() = UserDto(
    id = id,
    username = username,
    email = email,
    gameHistory = gameHistory.map { it.toDto() },
    stats = gameHistory.toStatistics(this),
    xp = xp
)

fun Page<User>.toDto() = toList().map { it.toDto() }

fun UsersRequest.toPageRequest() = PageRequest.of(paging.offset.dec(), paging.limit, Sort.by("xp").descending())