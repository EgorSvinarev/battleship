package ru.csit.battleship.gameserver.event

import com.google.gson.annotations.SerializedName

class AuthEvent(val bearerToken: String) : AbstractEvent()
