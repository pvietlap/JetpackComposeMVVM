package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_input.view_model

import androidx.lifecycle.viewModelScope
import com.bautoidem.jetpackcomposemvvm.core.viewmodel.BaseViewModel
import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.UserInput
import com.bautoidem.jetpackcomposemvvm.domain.use_case.UserInfoUseCase
import com.bautoidem.jetpackcomposemvvm.domain.use_case.UserInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(private val userInputUseCase: UserInputUseCase) :
    BaseViewModel<UserInputState, UserInputEvent>() {
    override fun initState(): UserInputState {
        return UserInputState()
    }

    override fun onTriggeredEvent(event: UserInputEvent) {
        when (event) {
            is UserInputEvent.ShowLoading -> {
                setState(
                    uiState.value.copy(
                        isLoading = event.isLoading
                    )
                )
            }
            is UserInputEvent.UpdateErrorMessage -> {
                setState(
                    uiState.value.copy(
                        errorMessage = event.errorMessage
                    )
                )
            }
            is UserInputEvent.UpdateUserInput -> {
                setState(
                    uiState.value.copy(
                        userInputs = uiState.value.userInputs.apply {
                            add(event.userInput)
                        }, timeUpdate = System.currentTimeMillis()
                    )
                )
            }
            is UserInputEvent.UpdateAllUserInputs -> {
                setState(
                    uiState.value.copy(
                        userInputs = uiState.value.userInputs.apply {
                            addAll(event.userInputs)
                        }, timeUpdate = System.currentTimeMillis()
                    )
                )
            }
        }
    }

    init {
        getAllUserInput()
    }

    private fun getAllUserInput() {
        viewModelScope.launch {
            triggerStateEvent(UserInputEvent.UpdateAllUserInputs(userInputs = userInputUseCase.getAllUseInput()))
        }
    }

    fun addUserInput(description: String) {
        if (description.isNotBlank()) {
            val userInput = UserInput(id = System.currentTimeMillis(), description = description)
            viewModelScope.launch {
                userInputUseCase.insertUserInput(userInput = userInput, onSuccess = {
                    if (it) {
                        triggerStateEvent(UserInputEvent.UpdateUserInput(userInput = userInput))
                        triggerStateEvent(UserInputEvent.UpdateErrorMessage(""))
                    }
                }, onMessage = {
                    triggerStateEvent(UserInputEvent.UpdateErrorMessage(it))
                })
            }
        } else {
            triggerStateEvent(UserInputEvent.UpdateErrorMessage("Error is Not Empty"))

        }
    }
}