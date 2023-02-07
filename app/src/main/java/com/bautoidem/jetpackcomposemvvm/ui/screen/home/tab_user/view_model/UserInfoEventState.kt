package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_user.view_model

import com.bautoidem.jetpackcomposemvvm.domain.model.User

data class UserInfoState (
    val isLoading: Boolean = false,
    val userInfo : User?=null
)

sealed class UserInfoEvent {
    data class ShowLoading(val isLoading: Boolean = false) : UserInfoEvent()

    data class UpdateUserInfo(val userInfo: User? = null) : UserInfoEvent()
}