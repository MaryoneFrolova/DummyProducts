package ru.maryone.dummyproducts.domain.usecase

import io.reactivex.rxjava3.core.Single
import ru.maryone.dummyproducts.domain.ProductListRepository
import ru.maryone.dummyproducts.domain.model.ProductItem

class GetProductListUseCase (private val productListRepository: ProductListRepository) {
    fun getProductList(skip: Int, limit: Int): Single<List<ProductItem>> {
        return productListRepository.getProductList(skip, limit)
    }
}