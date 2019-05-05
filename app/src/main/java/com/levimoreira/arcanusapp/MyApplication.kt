package com.levimoreira.arcanusapp

import android.app.Application
import com.levimoreira.arcanus.Arcanus

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Arcanus.init(this, "Alias", "Company", "Organization")
    }
}