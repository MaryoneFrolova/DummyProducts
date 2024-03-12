package ru.maryone.dummyproducts.di

import dagger.Module
import dagger.Provides
import ru.maryone.dummyproducts.domain.usecase.GetProductListUseCase
import ru.maryone.dummyproducts.presentation.MainViewModelFactory


@Module
class AppModule {

    @Provides
    fun provideMainViewModelFactory(getProductListUseCase: GetProductListUseCase): MainViewModelFactory {
        return MainViewModelFactory(getProductListUseCase)
    }
}