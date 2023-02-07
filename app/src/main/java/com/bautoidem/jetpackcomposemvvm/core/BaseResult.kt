package com.bautoidem.jetpackcomposemvvm.core

sealed class BaseResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : BaseResult<T>(data)
    class Error<T>(message: String, data: T? = null) : BaseResult<T>(data, message)
    class Loading<T>(data: T? = null) : BaseResult<T>(data)

    fun onResultReceived(
        onLoading: (() -> Unit)? = null,
        onSuccess: ((result: T?) -> Unit)? = null,
        onError: ((message: String?) -> Unit)? = null
    ){
        when(this){
            is Success -> {
                onSuccess?.invoke(this.data)
            }
            is Loading -> {
                onLoading?.invoke()
            }
            is Error -> {
                onError?.invoke(message)
            }
        }
    }
}