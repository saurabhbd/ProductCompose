package com.example.productscompose.cart.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.productscompose.R
import com.example.productscompose.cart.viewmodel.CartViewModel
import com.example.productscompose.productlist.presentation.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel) {

    val productList = viewModel.productList

    Scaffold(topBar = { CartAppBar(navController = navController) }) {
        Column(Modifier.padding(it)) {
            LazyColumn(modifier = Modifier.padding(dimensionResource(id = R.dimen.size16))) {
                items(items = productList) { product ->
                    ProductCard(product = product, onClick = {})
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartAppBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.text_cart)) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, stringResource(R.string.cart_cntDesc))
            }
        }
    )
}