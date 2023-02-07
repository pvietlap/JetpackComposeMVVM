package com.bautoidem.jetpackcomposemvvm.domain.use_case

import com.bautoidem.jetpackcomposemvvm.domain.LocalRepository
import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.UserInput
import javax.inject.Inject

class UserInputUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend fun getAllUseInput() = repository.getAllInputUsers()

    suspend fun insertUserInput(
        userInput: UserInput,
        onSuccess: (Boolean) -> Unit,
        onMessage: ((String) -> Unit)? = null
    ) = repository.saveUserInput(
        userInput = userInput,
        onMessage = onMessage,
        onSuccess = onSuccess
    )
}