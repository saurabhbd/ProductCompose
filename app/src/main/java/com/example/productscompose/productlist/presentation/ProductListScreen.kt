package com.example.productscompose.productlist.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.productscompose.navigation.Screen
import com.example.productscompose.productlist.viewmodel.ProductListViewModel
import com.example.productscompose.utils.AppConstants
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductListViewModel) {
    val products = viewModel.productsPaginated.collectAsLazyPagingItems()

    Scaffold(topBar = { ProductsAppBar(navController = navController) }) {
        Box(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                items(
                    count = products.itemCount
                ) { index ->
                    val product = products[index]
                    product?.let {
                        ProductCard(
                            product = product,
                            onClick = {
                                val productJson = Uri.encode(Gson().toJson(product))
                                navController.navigate(
                                    Screen.ProductDetailsScreen.route.plus(
                                        "${AppConstants.PRODUCT_DETAIL_ARG}${
                                            Uri.encode(
                                                productJson
                                            )
                                        }"
                                    )
                                )
                            })
                    }
                }

                // Handle loading when the next 10 items are loading.
                when (products.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    is LoadState.Loading -> {
                        item {
                            LoadingItem()
                        }
                    }

                    is LoadState.Error -> {

                    }
                }

                //Handle loading initially
                when (products.loadState.refresh) {
                    is LoadState.NotLoading -> Unit
                    is LoadState.Loading -> {
                        item {
                            LoadingItem()
                        }
                    }

                    is LoadState.Error -> {

                    }
                }
            }
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 2.dp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsAppBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "Products") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray),
        actions = {
            IconButton(onClick = { navController.navigate(Screen.CartScreen.route) }) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "shoppingCart")
            }
        }
    )
}