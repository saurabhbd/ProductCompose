package com.example.productscompose.productdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.productscompose.productlist.viewmodel.ProductListViewModel
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.data.model.ProductsResponse
import com.example.productscompose.productlist.repository.ProductsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ProductListViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductListViewModel
    private lateinit var productsRepository: ProductsRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        productsRepository = mock(ProductsRepository::class.java)
        viewModel = ProductListViewModel(productsRepository)
    }

    @Test
    fun getProductListTest() {
        runBlocking {
            val data = ProductsResponse(
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

            whenever(productsRepository.getProducts(10))
                .thenReturn(data)

            val paginatedData = viewModel.getAllProducts()

            assertThat(paginatedData).isNotNull()
        }
    }
}