package ru.maryone.dummyproducts.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.maryone.dummyproducts.domain.model.ProductItem
import ru.maryone.dummyproducts.domain.usecase.GetProductListUseCase

class MainViewModel (private val getProductListUseCase: GetProductListUseCase): ViewModel() {

    private val products: MutableLiveData<List<ProductItem>> = MutableLiveData<List<ProductItem>>()
    private val isLoading = MutableLiveData(false)
    private var page = 0
    private val compositeDisposable = CompositeDisposable()

    init {
        getProducts()
    }

    fun getProductList(): LiveData<List<ProductItem>> {
        return products
    }
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getProducts() {
        val loading: Boolean = isLoading.value ?: false
        if (loading) {
            return
        }
        Log.d("MainViewModel", "getProducts")
        val disposable: Disposable = getProductListUseCase.getProductList(page, ITEMS_ON_THE_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true) }
            .doAfterTerminate { isLoading.postValue(false) }
            .subscribe(
                { productsResponse ->
                    Log.d("MainViewModel", productsResponse.toString())
                    val loadedMovies: MutableList<ProductItem> = products.value?.toMutableList() ?: mutableListOf()
                    loadedMovies.addAll(productsResponse)
                    products.value = loadedMovies
                    page += ITEMS_ON_THE_PAGE
                },
                { throwable -> Log.d("MainViewModel", throwable.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        const val ITEMS_ON_THE_PAGE = 20
    }
}