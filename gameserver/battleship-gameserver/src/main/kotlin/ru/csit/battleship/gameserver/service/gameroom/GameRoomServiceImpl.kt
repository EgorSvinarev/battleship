package ru.csit.battleship.gameserver.service.gameroom

import mu.KotlinLogging.logger
import org.springframework.stereotype.Service
import ru.csit.battleship.core.Board
import ru.csit.battleship.core.Player
import ru.csit.battleship.gameserver.config.Constants.MAX_PLAYERS_IN_ROOM
import ru.csit.battleship.gameserver.event.player.GameRoomJoinEvent
import ru.csit.battleship.gameserver.networking.protocol.GameProtocol
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import ru.csit.battleship.gameserver.networking.session.room.GameRoom
import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ScheduledExecutorService

@Service
class GameRoomServiceImpl(
    private val schedulerService: ScheduledExecutorService,
    private val gameProtocol: GameProtocol
) : GameRoomService {

    private val queue: Queue<GameRoomJoinEvent> = ConcurrentLinkedQueue()
    private val roomsByKey: MutableMap<UUID, GameRoom> = ConcurrentHashMap()

    override fun addPlayerToQueue(event: GameRoomJoinEvent) {
        log.info("Player with session id: ${event.session.uuid} has been added to the queue.")

        queue.add(event)

        if (queue.size < MAX_PLAYERS_IN_ROOM) return;

        initializeGameRoom()
    }

    override fun removePlayerFromQueue(session: PlayerSession?) {
        queue.removeIf { event -> event.session == session }
    }

    override fun getRoomById(id: UUID) =
        roomsByKey[id] ?: throw RuntimeException(ROOM_NOT_FOUND.format(id))

    private fun initializeGameRoom() {
        val sessions = mutableListOf<PlayerSession>()
        val gameRoom = GameRoom(
            id = UUID.randomUUID(),
            schedulerService = schedulerService
        )

        while (sessions.size != MAX_PLAYERS_IN_ROOM) {
            val event = queue.poll()
            val session = event.session
            session.roomId = gameRoom.id
            session.player = Player(
                name = session.uuid.toString(),
            )
            sessions.add(session)

            gameRoom.sessions[session.uuid] = session

            roomsByKey[gameRoom.id] = gameRoom
        }

        gameRoom.onRoomCreated()
        gameProtocol.apply(sessions)
    }

    fun onBattleEnd(room: GameRoom) {
        roomsByKey.remove(room.id)
    }

    companion object {
        val log = logger {}

        const val ROOM_NOT_FOUND = "Gameroom with id: %s doesn't exists"
    }
}