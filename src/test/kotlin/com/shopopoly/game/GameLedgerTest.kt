package com.shopopoly.game

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameLedgerTest {
    @Test
    fun `bank should pay player starting balance`() {
        val gameLedger = GameLedger()

        gameLedger.payStartingBalance(Player("id"), 100)

        assertThat(gameLedger.calculatePlayerBalance(Player("id")))
    }
}