package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_input.view_model

import com.bautoidem.jetpackcomposemvvm.data.model.base.BaseState
import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.UserInput

data class UserInputState(
    val isLoading: Boolean? = false,
    val errorMessage: String = "",
    val userInputs: MutableList<UserInput> = mutableListOf(),
    override var timeUpdate: Long? = System.currentTimeMillis()
) : BaseState()

sealed class UserInputEvent {
    data class ShowLoading(val isLoading: Boolean) : UserInputEvent()
    data class UpdateErrorMessage(val errorMessage: String) : UserInputEvent()
    data class UpdateUserInput(val userInput: UserInput) : UserInputEvent()
    data class UpdateAllUserInputs(val userInputs: List<UserInput>) : UserInputEvent()
}