package org.d3if0107.assesment2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0107.assesment2.ui.ui.Screen.DetailScreen
import org.d3if0107.assesment2.ui.ui.Screen.KEY_ID_PESANAN
import org.d3if0107.assesment2.ui.ui.Screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_PESANAN) { type = NavType.LongType }
            )
        ) {nacBackStackEnty ->
            val id = nacBackStackEnty.arguments?.getLong(KEY_ID_PESANAN)
            DetailScreen(navController, id)
        }
    }
}