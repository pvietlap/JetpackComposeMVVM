package com.bautoidem.jetpackcomposemvvm.domain.model.user_input

import com.bautoidem.jetpackcomposemvvm.data.model.user_input.UserInputEntity

data class UserInput ( val id : Long,val description : String)


fun UserInput.toUserInputEntity() = UserInputEntity(id = id, description = description)