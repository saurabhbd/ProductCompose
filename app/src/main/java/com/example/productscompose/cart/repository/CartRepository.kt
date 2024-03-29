package com.example.productscompose.cart.repository

import com.example.productscompose.productlist.data.model.Product

interface CartRepository {

    suspend fun getAllProducts() : List<Product>
}