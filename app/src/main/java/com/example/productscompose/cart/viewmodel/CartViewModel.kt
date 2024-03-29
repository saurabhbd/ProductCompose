package com.example.productscompose.cart.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productscompose.cart.repository.CartRepository
import com.example.productscompose.productlist.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    private val _productList = mutableStateListOf<Product>()
    val productList: List<Product> = _productList

    init {
        getProductsFromDB()
    }

    private fun getProductsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = cartRepository.getAllProducts()

            withContext(Dispatchers.Main) {
                if (products.isNotEmpty()) {
                    _productList.addAll(products)
                }
            }
        }
    }
}