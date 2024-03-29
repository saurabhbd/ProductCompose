package com.example.productscompose.productlist.repository

import com.example.productscompose.database.ProductDao
import com.example.productscompose.network.APIService
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.data.model.ProductsResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val productDao: ProductDao
) : ProductsRepository {

    override suspend fun getProducts(page: Int): ProductsResponse {
        delay(1500L)
        return apiService.getProducts(page = page, pageSize = 10)
    }

    override suspend fun insertProducts(product: Product): Long {
        return productDao.insertProduct(product)
    }
}