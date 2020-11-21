package com.elliottco.susamongus.model

import java.util.*
import com.elliottco.susamongus.model.PlayerColor

/**
 * Data class representing suspicious behavior details
 */
data class DeadBodyReport(
    val id: UUID = UUID.randomUUID(),
    var reportedPlayerColor: PlayerColor? = null,
    var description: String = "",
    var location: String = ""
)