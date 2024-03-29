package com.example.productscompose.network

import com.example.productscompose.productlist.data.model.ProductsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    companion object {
        const val BASE_URL = "https://dummyjson.com/"

        operator fun invoke(): APIService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }

        private fun getRetrofitClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().build())
                }.also {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    it.addInterceptor(logging)
                }.build()
        }
    }

    @GET("products")
    suspend fun getProducts(
        @Query("skip") page: Int,
        @Query("limit") pageSize: Int
    ): ProductsResponse
}