package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_input.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bautoidem.jetpackcomposemvvm.ui.theme.MontserratFont
import com.bautoidem.jetpackcomposemvvm.ui.theme.Neutral_Gray_5
import com.bautoidem.jetpackcomposemvvm.ui.theme.Neutral_Gray_9
import com.bautoidem.jetpackcomposemvvm.ui.theme.White

@Composable
fun BaseEditTextInput(
    modifier: Modifier = Modifier,
    hint: String?,
    contentDefault: MutableState<String>,
    onTextChanged: (text: String) -> Unit,
    lastInput: Boolean = false,
    padding : Dp = 0.dp,
    isMiniHeight: Boolean?= true,
    isShowTitle: Boolean?= false,
    title: String?= "",
    colorBackground: Color?= White,
    onKeyboardDropFunc: (() -> Unit)?=null){
    Column(modifier = Modifier
        .padding(padding)
        .background(colorBackground ?: White)
        .border(width = 1.dp, shape = RoundedCornerShape(2.dp), color = Neutral_Gray_5)

    ){

        if(isShowTitle == true){
            androidx.compose.material.Text(text = title.orEmpty(), style = MaterialTheme.typography.subtitle2, modifier = Modifier.padding(top = 10.dp,start = 10.dp))
        }

        val focusManager = LocalFocusManager.current
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .onFocusEvent {
                    if (!it.isFocused) {
                        onKeyboardDropFunc?.invoke()
                    }
                }){
            var text by remember {
                mutableStateOf(contentDefault)
            }
            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    onTextChanged(text.value)
                },
                singleLine = isMiniHeight == true,
                textStyle = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal,
                    fontFamily = MontserratFont,
                    color = Neutral_Gray_9
                ),
                placeholder = {
                    Text(
                        text = hint.orEmpty(),
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontWeight = FontWeight.Medium,
                            fontFamily = MontserratFont,
                            color = Neutral_Gray_5
                        )
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Neutral_Gray_9,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        handleMoveAction(lastInput, focusManager, onKeyboardDropFunc)
                    },
                    onNext = {
                        handleMoveAction(lastInput, focusManager, onKeyboardDropFunc)
                    },
                    onGo = {
                        handleMoveAction(lastInput, focusManager, onKeyboardDropFunc)
                    }
                ),
            )
        }
    }
}

private fun handleMoveAction(
    lastInput: Boolean,
    focusManager: FocusManager,
    onKeyboardDropFunc: (() -> Unit)?
) {
    if (lastInput) {
        focusManager.clearFocus()
    } else {
        focusManager.moveFocus(FocusDirection.Down)
    }
    onKeyboardDropFunc?.invoke()
}