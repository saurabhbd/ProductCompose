package com.example.productscompose.productdetails.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productscompose.navigation.Screen
import com.example.productscompose.productlist.data.model.Product
import com.example.productscompose.productdetails.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsDetailsScreen(
    navController: NavController,
    viewModel: ProductDetailsViewModel,
    product: Product
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { ProductDetailsAppBar(product.title, navController = navController) },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }) {
        ProductsState(
            productInserted = viewModel.productInserted.value,
            onProductInserted = {
                navController.navigate(Screen.CartScreen.route) {
                    popUpTo(Screen.ProductListScreen.route)
                }
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(
                        message = "Product inserted.",
                        actionLabel = "DISMISS",
                        duration = SnackbarDuration.Short
                    )
                }
                viewModel.clearProductState()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.title,
                modifier = Modifier.padding(10.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = product.description, modifier = Modifier.padding(10.dp))
            Text(
                text = "$ ${product.price}", modifier = Modifier.padding(10.dp), fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                onClick = { viewModel.insertProductToCart(product) }) {
                Text(text = "Add to cart")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsAppBar(productName: String, navController: NavController) {
    TopAppBar(
        title = { Text(text = productName) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, "backProductDetail")
            }
        }
    )
}

@Composable
fun ProductsState(
    productInserted: Boolean,
    onProductInserted: () -> Unit
) {
    if (productInserted) {
        onProductInserted()
    }
}