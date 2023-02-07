package com.bautoidem.jetpackcomposemvvm.ui.screen.home.tab_user.view_model

import androidx.lifecycle.viewModelScope
import com.bautoidem.jetpackcomposemvvm.core.BaseResult
import com.bautoidem.jetpackcomposemvvm.core.viewmodel.BaseViewModel
import com.bautoidem.jetpackcomposemvvm.domain.ApiRepository
import com.bautoidem.jetpackcomposemvvm.domain.use_case.UserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(val userInfoUseCase: UserInfoUseCase) :
    BaseViewModel<UserInfoState, UserInfoEvent>() {
    override fun initState(): UserInfoState {
        return UserInfoState()
    }

    override fun onTriggeredEvent(event: UserInfoEvent) {
        when (event) {
            is UserInfoEvent.ShowLoading -> {
                setState(uiState.value.copy(isLoading = event.isLoading))
            }
            is UserInfoEvent.UpdateUserInfo -> {
                setState(uiState.value.copy(userInfo = event.userInfo))
            }
        }
    }


    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val name = "pvietlap"
            userInfoUseCase.getUserInfo(name).collectLatest { result ->
                when (result) {
                    is BaseResult.Success -> {
                        if (result.data != null && result.data.isNotEmpty()) {
                            result.data.get(0).let { user ->
                                triggerStateEvent(UserInfoEvent.UpdateUserInfo(userInfo = user))
                            }
                        }
                    }
                    is BaseResult.Error -> {
                        triggerStateEvent(UserInfoEvent.ShowLoading(false))
                    }
                    is BaseResult.Loading -> {
                        triggerStateEvent(UserInfoEvent.ShowLoading(true))
                    }
                }
            }
        }
    }

}