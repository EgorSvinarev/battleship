package ru.csit.battleship.entity.game

import jakarta.persistence.*
import ru.csit.battleship.entity.base.PrimaryEntity
import ru.csit.battleship.entity.user.User

@Entity
@Table(name = "games")
class Game (

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "is_winner", nullable = false)
    val isWinner: Boolean
): PrimaryEntity()