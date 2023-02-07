package com.bautoidem.jetpackcomposemvvm.domain.use_case

import com.bautoidem.jetpackcomposemvvm.domain.ApiRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend fun getUserInfo(name : String) = apiRepository.getSearchUser(name)
}