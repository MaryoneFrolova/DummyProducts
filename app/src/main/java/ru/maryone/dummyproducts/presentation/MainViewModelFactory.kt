package ru.maryone.dummyproducts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maryone.dummyproducts.domain.usecase.GetProductListUseCase

class MainViewModelFactory (
    private val getProductListUseCase: GetProductListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getProductListUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}