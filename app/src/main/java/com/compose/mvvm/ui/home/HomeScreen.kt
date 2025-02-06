package com.compose.mvvm.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.mvvm.data.model.Products
import com.compose.mvvm.data.model.ProductsData
import com.compose.mvvm.theme.colorPrimary
import com.compose.mvvm.theme.ghost_white
import com.compose.mvvm.theme.gray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.compose.mvvm.R

@Preview
@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ghost_white)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SlidingBanner()
        }
        item {
            CategoryView()
        }
        item {
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular Items",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.helvetica_neue_bold)),
                        color = colorPrimary
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.titleMedium.copy(color = colorPrimary)
                )
            }
        }
        item {
            PopularProductsList()
            PopularProductsList()
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SlidingBanner() {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
//            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    HorizontalPager(
        count = 3,
        state = pagerState,
        itemSpacing = 30.dp,
    ) { page ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_sale_banner),
            contentScale = ContentScale.FillWidth,
            contentDescription = "sliding_banner_image"
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

@Composable
private fun CategoryView() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(20.dp)) {
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_chinese_plum_product
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_flat_flower
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_giftbox
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_wedding_arch
        )
    }
}

@Composable
fun RoundedCornerIconButton(modifier: Modifier, icon: Int) {
    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(
            onClick = { }, modifier = Modifier
                .align(Alignment.Center)
                .padding(14.dp)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = icon),
                contentDescription = "rounded_corner_icon_button"
            )
        }
    }
}

@Composable
private fun PopularProductsList() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(ghost_white)
    ) {
        items(ProductsData.list.size) {
            ProductCard(ProductsData.list[it])
        }
    }
}

@Composable
private fun ProductCard(product: Products) {
    Card(
        colors = CardDefaults.cardColors(contentColor = Color.White),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
            .background(ghost_white)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(ghost_white),
        ) {

            Image(
                modifier = Modifier.size(140.dp),
                bitmap = ImageBitmap.imageResource(id = product.image),
                contentDescription = "product_card"
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.name,
                        style = TextStyle(
                            color = gray,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                        )
                    )
                    Text(
                        text = product.price,
                        style = TextStyle(
                            color = colorPrimary,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = colorPrimary,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        Icons.Default.Add,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = "product_card_icon"
                    )
                }
            }
        }
    }
}