package ru.maryone.dummyproducts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maryone.dummyproducts.data.repository.ProductListRepositoryImpl
import ru.maryone.dummyproducts.data.storage.api.ProductListApiStorage
import ru.maryone.dummyproducts.domain.usecase.GetProductListUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {

    private val productListRepository by lazy(LazyThreadSafetyMode.NONE) {
        ProductListRepositoryImpl(ProductListApiStorage())
    }
    private val getProductListUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetProductListUseCase(
            productListRepository
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getProductListUseCase) as T
    }
}