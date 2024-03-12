package ru.maryone.dummyproducts.data.storage.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

    @SerializedName("products")
    private val products: List<ProductItemApi>
) {
    fun getProducts(): List<ProductItemApi> {
        return products
    }
}
