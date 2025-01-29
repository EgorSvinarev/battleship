package ru.csit.battleship.gameserver.networking.session.room

import mu.KotlinLogging.logger
import ru.csit.battleship.core.Game
import ru.csit.battleship.core.Player
import ru.csit.battleship.gameserver.config.Constants.AFTER_ROOM_CREATED_DELAY
import ru.csit.battleship.gameserver.config.Constants.MAX_PLAYERS_IN_ROOM
import ru.csit.battleship.gameserver.config.Constants.SHOT_TIMER_DELAY
import ru.csit.battleship.gameserver.event.player.PlayerShotEvent
import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.message.MessageType.*
import ru.csit.battleship.gameserver.networking.message.outgoing.BoardConfigMessage
import ru.csit.battleship.gameserver.networking.message.outgoing.OutgoingMessage
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class GameRoom(
    val id: UUID,
    private val schedulerService: ScheduledExecutorService,
) : RoomLifecycleHandler, RoomWebsocketHandler {

    private val futures: MutableList<ScheduledFuture<*>> = arrayListOf()
    override val sessions: MutableMap<UUID, PlayerSession> = ConcurrentHashMap()
    private lateinit var game: Game
    private val readyCounter: AtomicInteger = AtomicInteger(0)

    override fun onRoomCreated() {
        log.info("Room with id: $id has been created for sessions: [${sessions.values.joinToString(" | ")}]")

        sendBroadcast(OutgoingMessage(ROOM_CREATE))

        futures.add(
            schedulerService.schedule(this::onPlayersNotReady, AFTER_ROOM_CREATED_DELAY, TimeUnit.SECONDS)
        )
    }

        override fun onPlayerReady(session: PlayerSession) {
        val counter = readyCounter.addAndGet(1)
        if (counter == MAX_PLAYERS_IN_ROOM) {
            clearFutures()
            start()
        }
    }

    override fun onPlayersNotReady() {
        destroy()
    }

    override fun start() {
        if (sessions.values.size < MAX_PLAYERS_IN_ROOM) {
            destroy()
        }

        game = Game(
            firstPlayer = sessions.values.elementAt(0).player!!,
            secondPlayer = sessions.values.elementAt(1).player!!
        )

        onRoomStarted()
    }

    override fun onRoomStarted() {
        sendGameConfiguration()
        sendBroadcast(OutgoingMessage(ROOM_START))

        log.info("Room with id: $id and sessions: [${sessions.values.joinToString(" | ")}] has been started.")

        sendBroadcast(OutgoingMessage(ROOM_SWITCH_TURN))
        waitForCurrentPlayerShot()
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun onPlayerTimeout() {
        sendCurrentPlayer(OutgoingMessage(PLAYER_LOSE))
        sendOpponentPlayer(OutgoingMessage(PLAYER_WIN))
        destroy()
    }

    override fun onPlayerShot(session: PlayerSession, event: PlayerShotEvent) {
        val sessionPlayer = session.player
        if (sessionPlayer != game.currentPlayer) {
            log.info("Incorrect action in room: $id. Non-current player has tried to complete action.")
            return
        }

        log.info { "Received shot from player: ${sessionPlayer.name} for room: $id." }

        clearFutures()

        val isHit = game.takeShot(event.x, event.y)
        sendGameConfiguration()

        sessionPlayer.board.printBoard(true)


        if (game.isCurrentPlayerWin()) {
            sendCurrentPlayer(OutgoingMessage(PLAYER_WIN))
            sendOpponentPlayer(OutgoingMessage(PLAYER_LOSE))
            destroy()
        }
        else {
            if (!isHit) {
                game.switchTurn()
                sendBroadcast(OutgoingMessage(ROOM_SWITCH_TURN))
            }
            waitForCurrentPlayerShot()
        }
    }

    override fun destroy() {
        clearFutures()
        sendBroadcast(OutgoingMessage(ROOM_CLOSE))
    }

    override fun onDestroy() {

    }

    override fun onDisconnect(session: PlayerSession) {
        send(session.channel, OutgoingMessage(PLAYER_LOSE))
        destroy()
    }

    private fun sendGameConfiguration() {
        sessions.forEach { (id, session) ->
            send(session.channel, OutgoingMessage(BOARD_CONFIG, BoardConfigMessage(
                playerBoard = session.player!!.board.getGrid(true),
                opponentBoard = game.getOpponent(session.player!!).board.getGrid(false)
            )))
        }
    }

    private fun clearFutures() =
        futures.forEach { futures -> futures.cancel(true) }

    private fun waitForCurrentPlayerShot() {
        log.info { "Waiting for a current player with id: ${game.currentPlayer.name} shot in room: $id" }

        sendCurrentPlayer(OutgoingMessage(ROOM_WAIT_PLAYER_SHOT))
        futures.add(schedulerService.schedule(this::onPlayerTimeout, SHOT_TIMER_DELAY, TimeUnit.SECONDS))
    }

    private fun sendCurrentPlayer(message: AbstractMessage) {
        val currentPlayer = game.currentPlayer
        val session = sessionByPlayer(currentPlayer)

        send(session.channel, message)
    }

    private fun sendOpponentPlayer(message: AbstractMessage) {
        val opponentPlayer = game.opponent
        val session = sessionByPlayer(opponentPlayer)

        send(session.channel, message)
    }

    private fun sessionByPlayer(player: Player) =
        sessions.values.first { s ->
            s.player == player
        }

    companion object {
        val log = logger {}
    }
}