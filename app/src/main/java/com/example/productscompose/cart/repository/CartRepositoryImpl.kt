package com.example.productscompose.cart.repository

import com.example.productscompose.database.ProductDao
import com.example.productscompose.productlist.data.model.Product
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val productDao: ProductDao) : CartRepository {
    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }
}