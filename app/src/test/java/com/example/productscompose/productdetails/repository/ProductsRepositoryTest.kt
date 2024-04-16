package com.example.productscompose.productdetails.repository

import com.example.productscompose.database.ProductDao
import com.example.productscompose.network.APIService
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.data.model.ProductsResponse
import com.example.productscompose.productlist.repository.ProductsRepository
import com.example.productscompose.productlist.repository.ProductsRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductsRepositoryTest {

    private lateinit var productsRepository: ProductsRepository

    private lateinit var apiService: APIService

    private lateinit var productDao: ProductDao

    @Before
    fun setUp() {
        productDao = mockk()
        apiService = mockk()
        productsRepository = ProductsRepositoryImpl(apiService, productDao)
    }

    @Test
    fun getProductsTest() = runBlocking {
        coEvery {
            apiService.getProducts(1, 10)
        } returns ProductsResponse(listOf(Product(1, "Samsung"), Product(2, "Apple")))

        val data = productsRepository.getProducts(1)

        assertThat(data).isEqualTo(
            ProductsResponse(
                listOf(
                    Product(1, "Samsung"),
                    Product(2, "Apple")
                )
            )
        )
    }

    @Test
    fun insertProductsTest() = runBlocking {
        coEvery { productDao.insertProduct(Product(1, "Samsung")) } returns 1

        val data = productsRepository.insertProducts(Product(1, "Samsung"))

        assertThat(data).isEqualTo(1)
    }
}