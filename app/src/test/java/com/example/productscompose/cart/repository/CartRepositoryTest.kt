package com.example.productscompose.cart.repository

import com.example.productscompose.database.ProductDao
import com.example.productscompose.productlist.data.model.Product
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CartRepositoryTest {

    private lateinit var productDao: ProductDao

    private lateinit var cartRepository: CartRepository

    @Before
    fun setUp() {
        productDao = mockk()
        cartRepository = CartRepositoryImpl(productDao)
    }

    @Test
    fun getAllProductsTest() = runBlocking {
        coEvery { (productDao.getAllProducts()) } returns (listOf(Product(1, "Samsung")))

        val data = cartRepository.getAllProducts()

        assertThat(data).isEqualTo(listOf(Product(1, "Samsung")))
    }
}