package ru.maryone.dummyproducts.data.storage.api

import io.reactivex.rxjava3.core.Single
import ru.maryone.dummyproducts.data.mapper.ProductItemMapping
import ru.maryone.dummyproducts.data.storage.ProductListStorage
import ru.maryone.dummyproducts.domain.model.ProductItem

class ProductListApiStorage : ProductListStorage {

    override fun get(skip: Int, limit: Int): Single<List<ProductItem>> {
        return ApiService.create().loadProducts(skip, limit).map { ProductItemMapping.from(it) }
    }
}