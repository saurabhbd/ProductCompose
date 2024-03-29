package com.example.productscompose.di

import android.content.Context

import androidx.room.Room

import com.example.productscompose.cart.repository.CartRepository

import com.example.productscompose.cart.repository.CartRepositoryImpl

import com.example.productscompose.database.ProductDao

import com.example.productscompose.database.ProductDatabase

import com.example.productscompose.network.APIService

import com.example.productscompose.productlist.repository.ProductsRepository

import com.example.productscompose.productlist.repository.ProductsRepositoryImpl

import com.example.productscompose.utils.AppConstants

import dagger.Module

import dagger.Provides

import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesProductsAPI(): APIService = APIService()

    @Provides
    fun providesProductsRepository(
        apiService: APIService,
        productDao: ProductDao
    ): ProductsRepository =
        ProductsRepositoryImpl(apiService, productDao)

    @Provides

    fun providesCartRepository(
        productDao: ProductDao
    ): CartRepository = CartRepositoryImpl(productDao)

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): ProductDao =
        Room.databaseBuilder(context, ProductDatabase::class.java, AppConstants.DATABASE_NAME)
            .build()
            .getProductDao()

}