package com.compose.mvvm.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.mvvm.R
import com.compose.mvvm.theme.colorPrimary
import com.compose.mvvm.theme.ghost_white
import com.compose.mvvm.theme.white_color



@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen() {
    val selectedIndex = remember { mutableIntStateOf(0) }
    Surface(modifier = Modifier.fillMaxSize().background(white_color)) {
        Scaffold(
            topBar = {
                CustomTopAppBar()
            },
            content = {
                Surface(modifier = Modifier.fillMaxSize().padding(top = 30.dp), color = colorPrimary) {
                    Card(
                        colors = CardDefaults.cardColors(contentColor = ghost_white),
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
                    ) {
                        Box(modifier = Modifier.padding(bottom = 0.dp)) {
                            when (selectedIndex.intValue) {
                                0 -> {
                                    HomeScreen()
                                }
                                1 -> {
                                }
                                2 -> {
                                }
                                3 -> {
                                }
                            }
                        } //bodyContent()

                    }
                }
            },
            bottomBar = {
                CustomBottomBar(selectedIndex = selectedIndex)
            },

            )

    }
}

@ExperimentalMaterial3Api
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().height(40.dp),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "MvvmCompose",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily(Font((R.font.josefin_sans_semibold_italic))),
                        fontSize = 22.sp
                    )
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "dashboard_search"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorPrimary,
           /* scrolledContainerColor = colorPrimary,
            navigationIconContentColor = colorPrimary,
            titleContentColor = colorPrimary,
            actionIconContentColor = colorPrimary*/
        ),
    )
}

@Composable
fun CustomBottomBar(selectedIndex: MutableState<Int>) {

    val listItems = listOf("Home", "Location", "Cart", "Profile")

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        BottomNavigation(backgroundColor = Color.White) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_home),
                                    isSelected
                                )
                            }
                            1 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_location),
                                    isSelected
                                )
                            }
                            2 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_cart),
                                    isSelected
                                )
                            }
                            3 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_profile),
                                    isSelected
                                )
                            }
                        }
                    },
                    selected = isSelected,
                    onClick = { selectedIndex.value = index },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

@Composable
fun TabIcons(icon: ImageBitmap, isTintColor: Boolean) {
    if (isTintColor) {
        Image(
            modifier = Modifier.wrapContentSize(),
            bitmap = icon,
            colorFilter = ColorFilter.tint(colorPrimary),
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_if"
        )
    } else {
        Image(
            modifier = Modifier.wrapContentSize(),
            bitmap = icon,
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_else"
        )
    }
}



