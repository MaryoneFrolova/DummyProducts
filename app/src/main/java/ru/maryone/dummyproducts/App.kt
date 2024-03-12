package ru.maryone.dummyproducts

import android.app.Application
import ru.maryone.dummyproducts.di.AppComponent
import ru.maryone.dummyproducts.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate(){
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}