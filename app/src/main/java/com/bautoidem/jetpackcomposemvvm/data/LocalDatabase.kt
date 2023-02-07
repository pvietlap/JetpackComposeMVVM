package com.bautoidem.jetpackcomposemvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bautoidem.jetpackcomposemvvm.data.model.user_input.UserInputDAO
import com.bautoidem.jetpackcomposemvvm.data.model.user_input.UserInputEntity

@Database(entities = [UserInputEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userInputDAO(): UserInputDAO
}