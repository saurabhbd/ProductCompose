package com.example.productscompose.productdetails.viewmodel

import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.data.model.ProductsResponse
import com.example.productscompose.productlist.repository.ProductsRepository
import com.example.productscompose.productlist.viewmodel.ProductListViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class ProductListViewModelTest {

    private lateinit var viewModel: ProductListViewModel
    private lateinit var productsRepository: ProductsRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        productsRepository = mockk()
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

            coEvery { productsRepository.getProducts(10) } returns data

            val paginatedData = viewModel.getAllProducts()

            assertThat(paginatedData).isNotNull()
        }
    }
}