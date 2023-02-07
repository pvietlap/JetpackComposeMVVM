package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_input.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_input.view_model.InputViewModel
import com.bautoidem.jetpackcomposemvvm.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserInputScreen(navController: NavController, viewModel: InputViewModel = hiltViewModel()) {

    val scope = rememberCoroutineScope()
    val context = LocalContext

    val uiState = viewModel.uiState.collectAsState()

    val scrollState = rememberLazyListState()


    var textInput = remember {
        mutableStateOf("")
    }
    val error = remember {
        mutableStateOf("")
    }

    LaunchedEffect(uiState.value.errorMessage) {
        if (uiState.value.errorMessage.isNotBlank()) {
            error.value = uiState.value.errorMessage
        }
    }

    if (error.value.isNotBlank()) {
        Toast.makeText(context.current, error.value, Toast.LENGTH_SHORT).show()
        error.value=""
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BaseEditTextInput(
            hint = "Input text", contentDefault = textInput,
            onTextChanged = {
                textInput.value = it
            },
            padding = 10.dp,
        )

        Button(onClick = {
            viewModel.addUserInput(textInput.value)
            textInput.value = ""
        }, modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = "Add", style = MaterialTheme.typography.subtitle1.copy(
                    color = White
                ), modifier = Modifier
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
        ) {

            if (uiState.value.userInputs.isNotEmpty()) {
                Text(
                    text = "History", style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 16.sp
                    )
                )

                LazyColumn(state = scrollState, modifier = Modifier) {
                    items(count = uiState.value.userInputs.size) { index ->
                        val item = uiState.value.userInputs[index]

                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontSize = 14.sp,
                                color = Neutral_Gray_9
                            )
                        )
                    }
                }
            }
        }
    }
}

