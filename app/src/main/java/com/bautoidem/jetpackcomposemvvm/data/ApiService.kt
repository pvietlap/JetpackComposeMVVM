package com.bautoidem.jetpackcomposemvvm.data

import com.bautoidem.jetpackcomposemvvm.data.model.UserDTO
import com.bautoidem.jetpackcomposemvvm.data.model.base.BaseListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getSearchUsers(@Query("q")name : String) : Response<BaseListResponse<UserDTO>>
}