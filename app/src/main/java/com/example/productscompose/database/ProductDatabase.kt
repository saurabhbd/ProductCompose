package com.example.productscompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productscompose.productlist.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao() : ProductDao
}