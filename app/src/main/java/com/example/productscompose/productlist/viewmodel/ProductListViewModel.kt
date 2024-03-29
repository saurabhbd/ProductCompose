package com.example.productscompose.productlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.productscompose.productlist.data.ProductsDataSource
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    private val _productsPaginated: MutableStateFlow<PagingData<Product>> = MutableStateFlow(
        PagingData.empty()
    )
    val productsPaginated = _productsPaginated.asStateFlow()

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                ProductsDataSource(repository)
            }.flow.cachedIn(viewModelScope).collect {
                _productsPaginated.value = it
            }
        }
    }
}