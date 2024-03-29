package com.example.productscompose.productlist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productlist.repository.ProductsRepository

class ProductsDataSource(private val productsRepository: ProductsRepository) :
    PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 0
            val response = productsRepository.getProducts(page = page)
            LoadResult.Page(
                data = response.productsResponse,
                prevKey = if (page == 0) null else page.minus(10),
                nextKey = if (response.productsResponse.isEmpty()) null else page.plus(10),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}