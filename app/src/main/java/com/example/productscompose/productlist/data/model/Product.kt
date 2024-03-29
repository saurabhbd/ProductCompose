package com.example.productscompose.productlist.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.productscompose.utils.AppConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = AppConstants.TABLE_PRODUCT)
@Parcelize
data class Product(
    @PrimaryKey
    @SerializedName("id") var id: Int = 0,

    @SerializedName("title") var title: String = "",

    @SerializedName("description") var description: String = "",

    @SerializedName("price") var price: Int = 0,

    @SerializedName("discountPercentage") var discountPercentage: Double? = null,

    @SerializedName("rating") var rating: Double? = null,

    @SerializedName("stock") var stock: Int? = null,

    @SerializedName("brand") var brand: String? = null,

    @SerializedName("category") var category: String? = null,

    @SerializedName("thumbnail") var thumbnail: String? = null,

    @Ignore
    @SerializedName("images") var images: ArrayList<String> = arrayListOf()

) : Parcelable