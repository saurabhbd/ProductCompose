package com.example.productscompose.navigation

sealed class Screen(val route : String) {
    object ProductListScreen : Screen("product_list_screen")
    object ProductDetailsScreen : Screen("product_details_screen")
    object CartScreen : Screen("cart_screen")
}