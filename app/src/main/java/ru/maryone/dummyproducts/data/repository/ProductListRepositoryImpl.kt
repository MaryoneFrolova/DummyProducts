package ru.maryone.dummyproducts.data.repository

import io.reactivex.rxjava3.core.Single
import ru.maryone.dummyproducts.data.storage.ProductListStorage
import ru.maryone.dummyproducts.domain.ProductListRepository
import ru.maryone.dummyproducts.domain.model.ProductItem

class ProductListRepositoryImpl (private val productListStorage: ProductListStorage) :
    ProductListRepository {
    override fun getProductList(skip: Int, limit: Int): Single<List<ProductItem>> {
        return productListStorage.get(skip, limit)
    }
}