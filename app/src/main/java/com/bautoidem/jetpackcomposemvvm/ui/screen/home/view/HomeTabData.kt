package com.bautoidem.jetpackcomposemvvm.ui.screen.home.view

data class HomeTabData(
    val type: TabType,
    val nameResource: Int ?= null
)

enum class TabType(val tabName: String) {
    UserInfo("USER_INFO"),
    UserInput("USER_INPUT")
}
