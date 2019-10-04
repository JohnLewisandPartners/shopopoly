package com.shopopoly.game.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameLedgerTest {
    val player1 = Player("id1")
    val player2 = Player("id2")

    @Test
    fun `bank should pay player starting balance`() {
        val gameLedger = GameLedger()

        gameLedger.payStartingBalance(player1, 100)

        assertThat(gameLedger.calculateBalance(player1)).isEqualTo(100)
    }

    @Test
    fun `should calculate balance for correct player`() {
        val gameLedger = GameLedger()

        gameLedger.payStartingBalance(player1, 25)
        gameLedger.payStartingBalance(player2, 55)

        assertThat(gameLedger.calculateBalance(player2)).isEqualTo(55)
    }
}