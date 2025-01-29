package ru.csit.battleship.gameserver.event.listener

import ru.csit.battleship.gameserver.event.AbstractEvent

fun interface EventListener<T: AbstractEvent> {
    fun onEvent(event: T)
}
