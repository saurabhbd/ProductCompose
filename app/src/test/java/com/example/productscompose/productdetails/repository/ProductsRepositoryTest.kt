package com.example.productscompose.productdetails.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.productscompose.database.ProductDao
import com.example.productscompose.network.APIService
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.data.model.ProductsResponse
import com.example.productscompose.productlist.repository.ProductsRepository
import com.example.productscompose.productlist.repository.ProductsRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class ProductsRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: APIService

    private lateinit var productDao: ProductDao

    private lateinit var productsRepository: ProductsRepository

    @Before
    fun setUp() {
        apiService = Mockito.mock(APIService::class.java)
        productDao = Mockito.mock(ProductDao::class.java)
        productsRepository = ProductsRepositoryImpl(apiService, productDao)
    }

    @Test
    fun getProductsTest() = runBlocking {
        whenever(apiService.getProducts(1, 10)).thenReturn(
            ProductsResponse(
                listOf(
                    Product(
                        id = 1,
                        title = "Nothing Phone 2",
                        price = 45999,
                    ),
                    Product(
                        id = 2,
                        title = "iPhone 14",
                        price = 45999,
                    )
                )
            )
        )

        val data = productsRepository.getProducts(1)

        assertThat(data).isEqualTo(ProductsResponse(
            listOf(
                Product(
                    id = 1,
                    title = "Nothing Phone 2",
                    price = 45999,
                ),
                Product(
                    id = 2,
                    title = "iPhone 14",
                    price = 45999,
                )
            )
        ))
    }

    @Test
    fun insertProductsTest() = runBlocking {
        whenever(productDao.insertProduct(Product(1,"Samsung"))).thenReturn(1)

        val data = productDao.insertProduct(Product(1,"Samsung"))

        assertThat(data).isEqualTo(1)

    }
}