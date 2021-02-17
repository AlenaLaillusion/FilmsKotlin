package com.example.fundamentalskotlin

import android.app.Application
import android.content.Context

class MoviesApp: Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    companion object {
        private var context: Context? = null
        fun context(): Context = context ?: throw IllegalStateException()
    }
}