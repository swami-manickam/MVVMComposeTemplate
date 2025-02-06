package com.compose.mvvm.ui.navigation

import androidx.navigation.NavHostController
import com.compose.mvvm.ui.navigation.Destination.DashBoard


/**
 * Models the screens in the app and any arguments they require.
 */
object Destination {
    const val Splash = "Splash"
    const val Login = "Login"
    const val DashBoard = "DashBoard"
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navController: NavHostController) {

    val openDashboard: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val addTask: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val upPress: () -> Unit = {
        navController.popBackStack()
    }
}
