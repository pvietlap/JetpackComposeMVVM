package com.bautoidem.jetpackcomposemvvm.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<T>
)