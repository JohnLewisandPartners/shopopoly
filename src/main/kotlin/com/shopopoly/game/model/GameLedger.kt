package com.shopopoly.game.model

class GameLedger {
    private val transactions: MutableList<Transaction> = mutableListOf()

    fun payStartingBalance(player: Player, startingBalance: Int) {
        transactions.add(Transaction(null, player, startingBalance))
    }

    fun calculateBalance(player: Player): Int {
        val credits = transactions
                .filter { it.creditTo == player }
                .sumBy { it.amount }

        val debits = transactions
                .filter { it.debitFrom == player }
                .sumBy { it.amount }

        return credits - debits
    }

    fun payRent(from: Player, to: Player, amount: Int) {
        transactions.add(Transaction(from, to, amount))
    }

    fun purchase(location: Factory, player: Player) {
        transactions.add(
                Transaction(
                        debitFrom = player,
                        creditTo = null,
                        amount = location.purchasePrice
                )
        )
    }

    fun whoOwns(location: Factory): Player? {
        return null
    }
}

data class Transaction(val debitFrom: Player?, val creditTo: Player?, val amount: Int)