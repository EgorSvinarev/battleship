package ru.csit.battleship.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import ru.csit.battleship.exception.EntityNotFoundException

@NoRepositoryBean
interface GeneralRepository<T> : JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    val entityName: String

    /**
     * @throws EntityNotFoundException при отсутствии сущности с запрошенным идентификатором
     */
    fun findByIdOrElseThrow(id: Long): T = findById(id).orElseThrow { EntityNotFoundException(id, "entityName") }
}