package com.example.productscompose.navigation

import android.net.Uri

import androidx.compose.runtime.Composable

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavBackStackEntry

import androidx.navigation.NavController

import androidx.navigation.NavHostController

import androidx.navigation.NavType

import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable

import androidx.navigation.navArgument

import com.example.productscompose.cart.presentation.CartScreen

import com.example.productscompose.cart.viewmodel.CartViewModel

import com.example.productscompose.productlist.data.model.Product

import com.example.productscompose.productlist.presentation.ProductListScreen

import com.example.productscompose.productdetails.presentation.ProductsDetailsScreen

import com.example.productscompose.utils.AppConstants

import com.example.productscompose.utils.AppConstants.Companion.PRODUCT_DETAIL_VALUE

import com.example.productscompose.productdetails.viewmodel.ProductDetailsViewModel
import com.example.productscompose.productlist.viewmodel.ProductListViewModel

import com.google.gson.Gson

@Composable

fun ScreenNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Screen.ProductListScreen.route) {

        composable(route = Screen.ProductListScreen.route) {
            NavigateToProductListScreen(navController = navHostController)
        }

        composable(route = Screen.ProductDetailsScreen.route.plus(AppConstants.PRODUCT_DETAIL_ARG + "{${PRODUCT_DETAIL_VALUE}}"),
            arguments = listOf(
                navArgument(PRODUCT_DETAIL_VALUE) {
                    type = NavType.StringType
                }
            )

        ) {
            NavigateToProductDetailsScreen(navController = navHostController, entry = it)
        }

        composable(route = Screen.CartScreen.route) {
            NavigateToCartScreen(navController = navHostController)
        }
    }
}

@Composable
fun NavigateToProductListScreen(navController: NavController) {
    val viewModel = hiltViewModel<ProductListViewModel>()
    ProductListScreen(navController = navController, viewModel = viewModel)
}

@Composable
fun NavigateToProductDetailsScreen(navController: NavController, entry: NavBackStackEntry) {
    val productDetailArg = entry.arguments?.getString(PRODUCT_DETAIL_VALUE)
    val productDetail = Gson().fromJson(Uri.decode(productDetailArg), Product::class.java)
    val viewModel = hiltViewModel<ProductDetailsViewModel>()
    ProductsDetailsScreen(
        navController = navController,
        viewModel = viewModel,
        product = productDetail
    )
}

@Composable
fun NavigateToCartScreen(navController: NavController) {
    val viewModel = hiltViewModel<CartViewModel>()
    CartScreen(navController = navController, viewModel = viewModel)
}

