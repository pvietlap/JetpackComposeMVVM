package com.bautoidem.jetpackcomposemvvm.data.model.user_input

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bautoidem.jetpackcomposemvvm.domain.model.user_input.UserInput

@Entity(tableName = "user_input_table")
data class UserInputEntity(
    @PrimaryKey
    val id: Long,
    val description: String
)

fun UserInputEntity.toUserInput() = UserInput(id = id, description = description)