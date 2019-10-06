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

    @Test
    fun `player should pay rent to another player`() {
        val gameLedger = GameLedger()
        gameLedger.payStartingBalance(player1, 100)
        gameLedger.payStartingBalance(player2, 100)

        gameLedger.payRent(from = player1, to = player2, amount = 50)

        assertThat(gameLedger.calculateBalance(player2)).isEqualTo(150)
        assertThat(gameLedger.calculateBalance(player1)).isEqualTo(50)
    }

    @Test
    fun `player should purchase location`() {
        val gameLedger = GameLedger()
        gameLedger.payStartingBalance(player1, 200)

        val factory1 = Factory("MRP-8")

        gameLedger.purchase(factory1, player1)

        assertThat(gameLedger.calculateBalance(player1)).isEqualTo(100)
        assertThat(gameLedger.whoOwns(factory1)).isEqualTo(player1)
    }
}