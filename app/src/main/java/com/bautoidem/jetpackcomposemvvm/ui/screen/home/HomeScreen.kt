package com.bautoidem.jetpackcomposemvvm.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bautoidem.jetpackcomposemvvm.R
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_input.view.UserInputScreen
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_user.view.UserInfoScreen
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.view.HomeTab
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.view.HomeTabData
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.view.TabType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    val pageStage = rememberPagerState(
        initialPage = 0
    )

    val tabData = remember {
        mutableStateOf(generateTabs())
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        HomeTab(modifier = Modifier, tabs = tabData.value, onTabClick = {

        }, pagerState = pageStage)
    }) {
        HorizontalPager(
            count = 2, userScrollEnabled = true, state = pageStage,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            when (index) {
                0 -> {
                    UserInfoScreen(navController = navController)
                }
                1 -> {
                    UserInputScreen(navController = navController)
                }
            }
        }
    }
}

private fun generateTabs(): List<HomeTabData> {
    return listOf(
        HomeTabData(type = TabType.UserInfo, nameResource = R.string.user_info),
        HomeTabData(type = TabType.UserInput, nameResource = R.string.user_input)
    )
}