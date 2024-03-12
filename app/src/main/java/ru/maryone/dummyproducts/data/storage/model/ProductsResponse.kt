package ru.maryone.dummyproducts.data.storage.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

    @SerializedName("products")
    private val products: List<ProductItemStorage>
) {
    fun getProducts(): List<ProductItemStorage> {
        return products
    }
}
