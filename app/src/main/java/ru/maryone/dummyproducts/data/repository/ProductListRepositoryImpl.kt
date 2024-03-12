package ru.maryone.dummyproducts.data.repository

import io.reactivex.rxjava3.core.Single
import ru.maryone.dummyproducts.data.mapper.ProductItemMapping
import ru.maryone.dummyproducts.data.storage.api.ApiService
import ru.maryone.dummyproducts.domain.ProductListRepository
import ru.maryone.dummyproducts.domain.model.ProductItem

object ProductListRepositoryImpl: ProductListRepository {
    override fun getProductList(skip: Int): Single<List<ProductItem>> {
        return ApiService.create().loadProducts(skip).map { ProductItemMapping.from(it) }
    }
}