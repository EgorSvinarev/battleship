package ru.csit.battleship.exception

class EntityNotFoundException(id: Long = 0, entityName: String? = null) : RuntimeException("${entityName ?: "Сущность"} с идентификатором: $id не найден(а).")

