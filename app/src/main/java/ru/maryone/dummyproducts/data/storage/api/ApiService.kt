package ru.maryone.dummyproducts.data.storage.api

import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.maryone.dummyproducts.data.storage.model.ProductsResponse

interface ApiService {

    @GET("/products")
    fun loadProducts(@Query("skip") skip: Int, @Query("limit") limit: Int = 20): Single<ProductsResponse>

    companion object Factory {

        private const val BASE_URL = "https://dummyjson.com/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                        it.level = HttpLoggingInterceptor.Level.BODY
                    }).build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}