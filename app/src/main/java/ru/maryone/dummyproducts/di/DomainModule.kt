package ru.maryone.dummyproducts.di

import dagger.Module
import dagger.Provides
import ru.maryone.dummyproducts.domain.ProductListRepository
import ru.maryone.dummyproducts.domain.usecase.GetProductListUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetProductListUseCase(productListRepository: ProductListRepository): GetProductListUseCase {
        return GetProductListUseCase(productListRepository)
    }
}