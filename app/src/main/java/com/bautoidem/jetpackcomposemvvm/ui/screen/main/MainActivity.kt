package com.bautoidem.jetpackcomposemvvm.ui.screen.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bautoidem.jetpackcomposemvvm.naviation.NavHostController
import com.bautoidem.jetpackcomposemvvm.ui.theme.JetpackComposeMVVMTheme
import com.bautoidem.jetpackcomposemvvm.ui.theme.SystemUiController
import com.bautoidem.jetpackcomposemvvm.ui.theme.White
import com.bautoidem.jetpackcomposemvvm.util.BackPressHandler
import com.bautoidem.jetpackcomposemvvm.util.LocalBackPressedDispatcher
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.systemBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUiController = remember { SystemUiController(window) }
            systemUiController.setStatusBarColor(color = White)
            ProvideWindowInsets {
                JetpackComposeMVVMTheme {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                 Scaffold(
                        modifier = Modifier
                            .fillMaxWidth()
                            .systemBarsPadding()
                            .navigationBarsWithImePadding(),
                        content = {
                            CompositionLocalProvider(
                                LocalBackPressedDispatcher provides this@MainActivity.onBackPressedDispatcher
                            ) {
                                if (drawerState.isOpen) {
                                    BackPressHandler {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                }
                                Box(modifier = Modifier.padding()) {
                                    NavHostController(
                                    )
                                }
                            }
                        }
                    )
                }


            }
        }
    }
}