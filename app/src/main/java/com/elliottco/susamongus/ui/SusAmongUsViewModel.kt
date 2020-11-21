package com.elliottco.susamongus.ui

import androidx.lifecycle.ViewModel
import com.elliottco.susamongus.model.DeadBodyReport
import com.elliottco.susamongus.model.PlayerColor

class SusAmongUsViewModel : ViewModel() {

    private val rooms = listOf(
        "Cafeteria",
        "Weapons",
        "Navigation",
        "O2",
        "Shields",
        "Communications",
        "Storage",
        "Admin",
        "Electrical",
        "Lower Engine",
        "Security",
        "Reactor",
        "Upper Engine",
        "MedBay"
    )

    private val playerColors = listOf(
        PlayerColor.RED,
        PlayerColor.BLUE,
        PlayerColor.YELLOW,
        PlayerColor.ORANGE,
        PlayerColor.GREEN,
        PlayerColor.WHITE,
        PlayerColor.BLACK,
        PlayerColor.PURPLE
    )

    private val testDescriptions = listOf(
        "Took too long doing tasks",
        "Saw coming through vent",
        "Didn't report a body",
        "Reported body"
    )

    val reports = mutableListOf<DeadBodyReport>()

    init {
        for(i in 0 until 100) {
            val report = DeadBodyReport()
            report.description = testDescriptions[i%4]
            report.reportedPlayerColor = playerColors[i%8]
            report.location = rooms[i % 14]
            reports += report
        }
    }
}