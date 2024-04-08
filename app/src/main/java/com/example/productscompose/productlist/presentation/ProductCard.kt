package com.example.productscompose.productlist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.productscompose.R
import com.example.productscompose.productlist.data.model.Product

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            product.thumbnail?.let {
                ProductThumbnail(thumbnail = it)
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                ProductTitle(title = product.title)
                ProductPrice(price = product.price.toString())
                product.rating?.let {
                    ProductRating(rating = it)
                }
            }
        }
    }

    Divider()
}

@Composable
fun ProductThumbnail(thumbnail: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(thumbnail).build(),
        contentDescription = "productThumbnail",
        contentScale = ContentScale.Inside,
        modifier = Modifier.size(dimensionResource(id = R.dimen.size100))
    )
}

@Composable
fun ProductTitle(title: String) {
    Text(
        text = title,
        fontSize = with(LocalDensity.current) {
            dimensionResource(id = R.dimen.size16).toSp()
        },
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.size10),
            end = dimensionResource(id = R.dimen.size10),
            top = dimensionResource(id = R.dimen.size10),
            bottom = dimensionResource(id = R.dimen.size10)
        )
    )
}

@Composable
fun ProductPrice(price: String) {
    Text(
        text = "$ $price",
        fontSize = with(LocalDensity.current) {
            dimensionResource(id = R.dimen.size18).toSp()
        },
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.size10), end = dimensionResource(id = R.dimen.size10), top = dimensionResource(id = R.dimen.size10), bottom = dimensionResource(id = R.dimen.size10))
    )
}


@Composable
fun ProductRating(rating: Double = 0.0) {
    StarRatingBar(rating = rating, onRatingChanged = {})
}

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Double,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = Modifier
            .selectableGroup()
            .padding(start = dimensionResource(id = R.dimen.size10), top = dimensionResource(id = R.dimen.size5)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20FFFFFF)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .width(dimensionResource(id = R.dimen.size20))
                    .height(dimensionResource(id = R.dimen.size20))
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}