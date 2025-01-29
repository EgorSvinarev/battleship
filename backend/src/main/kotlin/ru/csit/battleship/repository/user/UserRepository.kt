package ru.csit.battleship.repository.user

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation.MANDATORY
import org.springframework.transaction.annotation.Transactional
import ru.csit.battleship.entity.user.User
import ru.csit.battleship.repository.GeneralRepository

@Repository
@Transactional(propagation = MANDATORY)
interface UserRepository : GeneralRepository<User> {

    override val entityName: String get() = User::class.java.simpleName

    fun findByEmail(email: String): User?

    fun findByUsername(email: String): User?
}