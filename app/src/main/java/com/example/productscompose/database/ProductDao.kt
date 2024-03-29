package com.example.productscompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.utils.AppConstants

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(list: List<Product>)

    @Query("SELECT * FROM ${AppConstants.TABLE_PRODUCT}")
    suspend fun getAllProducts() : List<Product>
}