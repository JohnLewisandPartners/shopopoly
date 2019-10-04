package com.shopopoly.game.model

object FreeParking

object Go {
    const val passthroughPayout = 100
}

class Factory(val name: String) {
    val purchasePrice = 100
    val rent = 20
}

enum class ShopType {
    MINISTORE,
    SUPERMARKET,
    MEGASTORE,
}

sealed class DevelopmentStatus
object Undeveloped: DevelopmentStatus()
class Developed(val shopType: ShopType): DevelopmentStatus()

typealias BuildingCosts = (ShopType) -> Int
typealias RentPolicy = (DevelopmentStatus) -> Int

data class RetailSite(
        val name: String,
        val purchasePrice: Int,
        val buildingCosts: BuildingCosts,
        val rentPolicy: RentPolicy
)