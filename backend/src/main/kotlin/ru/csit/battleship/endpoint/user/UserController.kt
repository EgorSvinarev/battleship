package ru.csit.battleship.endpoint.user

import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import ru.csit.battleship.domain.user.UserService
import ru.csit.battleship.domain.user.dto.UsersRequest
import ru.csit.battleship.endpoint.UserPath.USERS_PATH
import ru.csit.battleship.endpoint.UserPath.USER_PATH

@RestController
class UserController(
    private val userService: UserService
) : UserApi {

    @GetMapping(USER_PATH, produces = [APPLICATION_JSON_VALUE])
    override fun getById(@PathVariable userId: Long) = userService.getById(userId)

    @PostMapping(USERS_PATH, consumes = [APPLICATION_JSON_VALUE])
    override fun getAll(@RequestBody @Valid request: UsersRequest) = userService.getAll(request)
}