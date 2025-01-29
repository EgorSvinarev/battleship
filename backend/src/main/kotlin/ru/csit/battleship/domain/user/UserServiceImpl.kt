package ru.csit.battleship.domain.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.csit.battleship.domain.user.dto.UserDto
import ru.csit.battleship.domain.user.dto.UsersRequest
import ru.csit.battleship.domain.user.mapping.toDto
import ru.csit.battleship.domain.user.mapping.toPageRequest
import ru.csit.battleship.repository.user.UserRepository

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    @Transactional(readOnly = true)
    override fun getById(userId: Long) = userRepository.findByIdOrElseThrow(userId).toDto()

    @Transactional(readOnly = true)
    override fun getAll(request: UsersRequest) = userRepository.findAll(request.toPageRequest()).toDto()
}