package com.compose.mvvm

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.mvvm.ui.auth.LoginScreen
import com.compose.mvvm.ui.home.DashboardScreen
import com.compose.mvvm.ui.navigation.Actions
import com.compose.mvvm.ui.navigation.Destination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeApp() {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    androidx.compose.material.MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.Login) {

            composable(Destination.Login) {
                LoginScreen(actions.openDashboard)
            }

            composable(Destination.DashBoard) {
                DashboardScreen()
            }
        }
    }
}