package ru.maryone.dummyproducts.di

import dagger.Module
import dagger.Provides
import ru.maryone.dummyproducts.data.repository.ProductListRepositoryImpl
import ru.maryone.dummyproducts.data.storage.ProductListStorage
import ru.maryone.dummyproducts.data.storage.api.ProductListApiStorage
import ru.maryone.dummyproducts.domain.ProductListRepository

@Module
class DataModule {

    @Provides
    fun provideProductListStorage(): ProductListStorage {
        return ProductListApiStorage()
    }

    @Provides
    fun provideProductListRepository(productListStorage: ProductListStorage): ProductListRepository {
        return ProductListRepositoryImpl(productListStorage = productListStorage)
    }
}