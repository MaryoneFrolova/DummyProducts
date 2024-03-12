package ru.maryone.dummyproducts.domain

import io.reactivex.rxjava3.core.Single
import ru.maryone.dummyproducts.domain.model.ProductItem

interface ProductListRepository {
    fun getProductList(skip: Int): Single<List<ProductItem>>
}