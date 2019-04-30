package com.levimoreira.arcanus

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val senstiveData = "SensitiveData"

        Arcanus.addString(this, SECRET_KEY, senstiveData)
        val retrievedData = Arcanus.getString(this, SECRET_KEY)

        assert(senstiveData == retrievedData)
    }

    companion object {
        const val SECRET_KEY = "SECRET_KEY"
    }
}
