package com.bautoidem.jetpackcomposemvvm.data.repository

import android.content.Context
import com.bautoidem.jetpackcomposemvvm.data.model.user_input.UserInputDAO
import com.bautoidem.jetpackcomposemvvm.data.model.user_input.toUserInput
import com.bautoidem.jetpackcomposemvvm.domain.LocalRepository
import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.UserInput
import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.toUserInputEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val context: Context,
    private val userInputDAO: UserInputDAO
) : LocalRepository {
    override suspend fun getAllInputUsers(): List<UserInput> {
        return userInputDAO.getAllUserInputs().map { it.toUserInput() }
    }

    override suspend fun saveUserInput(
        userInput: UserInput,
        onSuccess: (Boolean) -> Unit,
        onMessage: ((String) -> Unit)?
    ) {
        if (!userInputDAO.checkInputExs(userInput.description)){
            userInputDAO.insertUserInput(userInput.toUserInputEntity())
            onSuccess.invoke(true)
        }else{
            onSuccess.invoke(false)
            onMessage?.invoke("Error exists!")
        }
    }
}