package ru.maryone.dummyproducts.di

import dagger.Component
import ru.maryone.dummyproducts.presentation.MainActivity

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}