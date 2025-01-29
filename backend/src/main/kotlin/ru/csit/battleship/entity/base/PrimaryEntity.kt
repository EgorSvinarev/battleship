package ru.csit.battleship.entity.base

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.util.ProxyUtils
import java.time.Instant

@MappedSuperclass
abstract class PrimaryEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = 0

    @Column(nullable = false, updatable = false)
    var createdDate: Instant = Instant.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != ProxyUtils.getUserClass(other)) return false

        return id == (other as PrimaryEntity).id
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}