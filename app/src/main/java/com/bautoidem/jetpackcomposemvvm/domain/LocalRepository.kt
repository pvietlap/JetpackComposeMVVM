package com.bautoidem.jetpackcomposemvvm.domain

import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.UserInput

interface LocalRepository {

    suspend fun getAllInputUsers() : List<UserInput>

    suspend fun saveUserInput(userInput: UserInput, onSuccess : (Boolean)->Unit, onMessage :((String)->Unit)?=null)
}