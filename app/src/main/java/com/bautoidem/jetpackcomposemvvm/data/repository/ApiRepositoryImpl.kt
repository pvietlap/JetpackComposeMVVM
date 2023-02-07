package com.bautoidem.jetpackcomposemvvm.data.repository

import android.content.Context
import com.bautoidem.jetpackcomposemvvm.core.BaseResult
import com.bautoidem.jetpackcomposemvvm.data.ApiService
import com.bautoidem.jetpackcomposemvvm.data.model.base.ErrorResponseDTO
import com.bautoidem.jetpackcomposemvvm.data.model.toUser
import com.bautoidem.jetpackcomposemvvm.domain.ApiRepository
import com.bautoidem.jetpackcomposemvvm.domain.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val context: Context,
    private val apiService: ApiService
) : ApiRepository {
    override suspend fun getSearchUser(name: String): Flow<BaseResult<List<User>>> =
        flow {
            emit(BaseResult.Loading())
            kotlin.runCatching { apiService.getSearchUsers(name) }.onSuccess {
                if (it.isSuccessful) {
                    it.body()?.let { res ->
                        emit(BaseResult.Success(res.items.map { userDTO -> userDTO.toUser() }))
                    }
                } else {
                    val type : Type = object : TypeToken<ErrorResponseDTO?>() {}.type
                    val errorResponse: ErrorResponseDTO =
                        Gson().fromJson(it.errorBody()?.charStream(), type)
                    emit(BaseResult.Error(errorResponse.message))
                }
            }.onFailure {
                emit(
                    BaseResult.Error(it.message ?: "Error")
                )
            }
        }
}