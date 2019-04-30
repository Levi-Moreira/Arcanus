package com.levimoreira.arcanus

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Arcanus.init(this, "Alias", "Company", "Organization")
    }
}