package com.compose.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val SplashWaitTime: Long = 2000

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadSplashScreen{
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    /*@Composable
    fun LoadSplashScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LaunchedEffect(key1 = true) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(SplashWaitTime)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }*/
    @Composable
    fun LoadSplashScreen(onTimeout: () -> Unit) {
        LaunchedEffect(Unit) {
            delay(3000) // Splash duration (3 seconds)
            onTimeout()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.ic_bg_splash), // Replace with your background image
                contentDescription = "Splash Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Centered Logo
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo), // Replace with your logo
                    contentDescription = "Splash Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}