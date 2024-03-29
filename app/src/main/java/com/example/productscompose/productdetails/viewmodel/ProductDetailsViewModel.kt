package com.example.productscompose.productdetails.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    private val _productInserted = mutableStateOf(false)
    val productInserted: State<Boolean> = _productInserted

    fun insertProductToCart(product: Product) {
        viewModelScope.launch {
            val id = repository.insertProducts(product = product)

            if (id > 0) {
                _productInserted.value = true
            }
        }
    }

    fun clearProductState() {
        _productInserted.value = false
    }
}