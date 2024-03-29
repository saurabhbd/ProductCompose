package com.example.productscompose.productlist.repository

import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.data.model.ProductsResponse

interface ProductsRepository {

    suspend fun getProducts(page:Int) : ProductsResponse

    suspend fun insertProducts(product: Product) : Long
}