package ru.maryone.dummyproducts.data.mapper

import ru.maryone.dummyproducts.data.storage.model.ProductsResponse
import ru.maryone.dummyproducts.domain.model.ProductItem

object ProductItemMapping {
    fun from(item: ProductsResponse) = item.getProducts().map {
        ProductItem(it.id, it.title, it.description, it.thumbnail)
    }
}