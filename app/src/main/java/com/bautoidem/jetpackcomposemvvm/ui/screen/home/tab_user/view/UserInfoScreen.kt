package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_user.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_user.view_model.UserInfoViewModel
import com.bautoidem.jetpackcomposemvvm.ui.theme.White
import com.bautoidem.jetpackcomposemvvm.widget.RoundedAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserInfoScreen(navController: NavController, viewModel: UserInfoViewModel = hiltViewModel()) {

    val scope = rememberCoroutineScope()

    val uiState = viewModel.uiState.collectAsState()

    val userInfo = uiState.value.userInfo

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RoundedAsyncImage(
                imageUrl = userInfo?.avatarUrl ?: "",
                shape = CircleShape,
                modifier = Modifier.size(120.dp)
            )
            
            Text(text = userInfo?.login?:"", style = MaterialTheme.typography.subtitle2.copy(
                fontSize = 20.sp
            ), modifier = Modifier.padding(top = 5.dp))

            Text(text = userInfo?.url?:"", style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 15.sp
            ), modifier = Modifier.padding(top = 5.dp))

        }

    }
}