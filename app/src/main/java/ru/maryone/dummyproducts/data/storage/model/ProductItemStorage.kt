package ru.maryone.dummyproducts.data.storage.model

import com.google.gson.annotations.SerializedName

data class ProductItemStorage(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
