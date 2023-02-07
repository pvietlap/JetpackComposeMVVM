package com.bautoidem.jetpackcomposemvvm.naviation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.HomeScreen

@Composable
fun NavHostController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenName.HomeScreen.route){
        composable(route = ScreenName.HomeScreen.route){
                HomeScreen(navController = navController)
        }
    }
}