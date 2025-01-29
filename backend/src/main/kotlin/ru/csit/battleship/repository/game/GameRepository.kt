package ru.csit.battleship.repository.game

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.csit.battleship.entity.game.Game
import ru.csit.battleship.entity.user.User
import ru.csit.battleship.repository.GeneralRepository

@Repository
@Transactional(propagation = Propagation.MANDATORY)
interface GameRepository : GeneralRepository<Game> {

    override val entityName: String get() = Game::class.java.simpleName
}