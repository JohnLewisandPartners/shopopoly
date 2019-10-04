package com.shopopoly.game.model

import com.shopopoly.game.model.ShopType.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ModelsTest {
    @Test
    fun `can construct Free Parking`() {
        val location = FreeParking
    }

    @Test
    fun `can construct Go`() {
        val location = Go
        assertThat(location.passthroughPayout).isEqualTo(100)
    }

    @Test
    fun `can construct Factory`() {
        val factory = Factory(name = "Jam Factory")
        assertThat(factory.rent).isEqualTo(20)
        assertThat(factory.purchasePrice).isEqualTo(100)
    }

    @Test
    fun `can construct Retail Site`() {
        val rentPolicy: RentPolicy = { status ->
            when (status) {
                is Undeveloped -> 20
                is Developed -> when (status.shopType) {
                    MINISTORE -> 30
                    SUPERMARKET -> 40
                    MEGASTORE -> 50
                }
            }
        }

        val constantBuildingCost: BuildingCosts = { type ->
            when (type) {
                MINISTORE -> 200
                SUPERMARKET -> 400
                MEGASTORE -> 1_000_000
            }
        }

        val site = RetailSite(
                name = "Westfield",
                purchasePrice = 500,
                buildingCosts = constantBuildingCost,
                rentPolicy = rentPolicy)

        assertThat(site.buildingCosts(MINISTORE)).isEqualTo(200)
    }
}
