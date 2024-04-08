package com.example.productscompose.cart.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.productscompose.database.ProductDao
import com.example.productscompose.network.APIService
import com.example.productscompose.productlist.data.model.Product
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class CartRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: APIService

    private lateinit var productDao: ProductDao

    private lateinit var cartRepository: CartRepository

    @Before
    fun setUp() {
        apiService = Mockito.mock(APIService::class.java)
        productDao = Mockito.mock(ProductDao::class.java)
        cartRepository = CartRepositoryImpl(productDao)
    }

    @Test
    fun getAllProductsTest() = runBlocking {
        whenever(productDao.getAllProducts()).thenReturn(
            listOf(
                Product(
                    id = 1,
                    title = "Nothing Phone 2",
                    price = 45999,
                )
            )
        )

        val data = productDao.getAllProducts()

        assertThat(data).isEqualTo(listOf(
            Product(
                id = 1,
                title = "Nothing Phone 2",
                price = 45999,
            )
        ))
    }
}