package com.shopopoly.game.model

class GameLedger {
    private val transactions: MutableList<Transaction> = mutableListOf()

    fun payStartingBalance(player: Player, startingBalance: Int) {
        transactions.add(Transaction(player, startingBalance))
    }

    fun calculateBalance(player: Player): Int {
        return transactions
                .filter { it.player == player }
                .first().amount
    }
}

data class Transaction(val player: Player, val amount: Int)