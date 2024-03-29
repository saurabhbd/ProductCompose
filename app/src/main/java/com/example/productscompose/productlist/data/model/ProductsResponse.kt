package com.example.productscompose.productlist.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponse(
    @SerializedName("products") var productsResponse: List<Product>
) : Parcelable
