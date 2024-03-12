package ru.maryone.dummyproducts.data.storage

import io.reactivex.rxjava3.core.Single
import ru.maryone.dummyproducts.domain.model.ProductItem

interface ProductListStorage {
    fun get(skip: Int, limit: Int): Single<List<ProductItem>>
}