package com.bautoidem.jetpackcomposemvvm.naviation

sealed class ScreenName (val route: String){
    object HomeScreen : ScreenName("home_screen")
}