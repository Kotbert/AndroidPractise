package com.example.m11_timer_data_storage

import android.content.SharedPreferences

class Repository(var preferences: SharedPreferences) {

    var localSharedPreferences: String? = null
    fun getDataFromSharedPreference() =
        preferences.getString(KEY_STRING_NAME, "Там ничего нет").toString()

    fun getDataFromLocalVariable(): String {
        return localSharedPreferences ?: "Переменная пустая"
    }

    fun saveText(text: String) {
        if (text != "") {
            preferences.edit().putString(KEY_STRING_NAME, text).apply()
            localSharedPreferences = "$text <- переменная"
        }
    }

    fun clearText() {
        preferences.edit().clear().apply()
        localSharedPreferences = null
    }

    fun getText() = preferences.getString(KEY_STRING_NAME, "Там ничего нет").toString()

    companion object {
        const val KEY_STRING_NAME = "KEY_STRING"
    }
}