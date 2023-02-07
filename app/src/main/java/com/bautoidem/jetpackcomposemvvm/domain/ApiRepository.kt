package com.bautoidem.jetpackcomposemvvm.domain

import com.bautoidem.jetpackcomposemvvm.core.BaseResult
import com.bautoidem.jetpackcomposemvvm.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getSearchUser(name : String) : Flow<BaseResult<List<User>>>
}