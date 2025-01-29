package ru.csit.battleship.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.csit.battleship.entity.base.PrimaryEntity
import ru.csit.battleship.entity.game.Game

@Entity
@Table(name = "users")
class User(

    @Column(name = "username", unique = true, nullable = false)
    @JvmField
    val username: String,

    @Column(name = "email", unique = true, nullable = false)
    val email: String,

    @Column(name = "password_hash", nullable = false)
    val passwordHash: String,

    @OneToMany(mappedBy = "user")
    val gameHistory: Set<Game> = setOf(),

    @Column(name = "xp", nullable = false)
    var xp: Long,
) : PrimaryEntity(), UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword() = passwordHash

    override fun getUsername() = username
}