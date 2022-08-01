package com.example.task04network

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    private val key = "PREFS"
    private val sharedPref: SharedPreferences =
    context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }
}