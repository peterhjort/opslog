package com.example.counterops

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyApp: Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        lateinit var appContext: Context
    }
}