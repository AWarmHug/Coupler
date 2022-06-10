package com.bingo.spadedemo.ui

import android.util.Log

class UserUtils(val id: String) {


    fun logout() {
        Log.d("UserUtils", "登出")
    }

    fun login(id: String) {
        Log.d("UserUtils", "登录")
    }

}