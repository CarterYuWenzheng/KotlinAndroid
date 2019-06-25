package com.carter.androiddemo.modules.login.bean

data class LoginData(var userName: String, var passWord: String, var icon: String, var type: Int, var collectIds: MutableList<Int>) {

}