package com.example.productscompose.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.productscompose.productlist.data.model.Product
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductsDatabaseTest {

    private lateinit var databse: ProductDatabase
    private lateinit var dao: ProductDao

    @Before
    fun setUp() {
        databse = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductDatabase::class.java
        ).build()

        dao = databse.getProductDao()
    }

    @Test
    fun insertProductsTest() {
        runBlocking {
            val product = Product(
                id = 1,
                title = "Nothing Phone 2",
                price = 45999,
                description = "Nothing Phone 2, 12GB RAM, 256 GB Memory",
                rating = 4.5,
                category = "Phone"
            )

            val databaseId = dao.insertProduct(product)
            assertThat(databaseId).isGreaterThan(0L)
        }
    }

    @Test
    fun getProductsTest() {
        runBlocking {
            val products = listOf(
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

            dao.insertAllProducts(products)

            val productsFromDb = dao.getAllProducts()

            assertThat(productsFromDb).isNotEmpty()
        }
    }
}