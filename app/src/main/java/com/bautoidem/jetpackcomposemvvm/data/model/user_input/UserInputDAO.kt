package com.bautoidem.jetpackcomposemvvm.data.model.user_input

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInputDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserInput(userInputEntity: UserInputEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInputs(subCategories: List<UserInputEntity?>)

    @Query("select * from user_input_table")
    suspend fun getAllUserInputs(): List<UserInputEntity>

    @Query("delete from user_input_table")
    fun deleteAllUserInputs()

    @Query("select * from user_input_table where description= :des")
    fun checkInputExs(des: String) : Boolean

    @Delete
    fun deleteUserInput(subCategoryEntity: UserInputEntity);

}