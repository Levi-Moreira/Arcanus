package com.levimoreira.arcanusapp

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.levimoreira.arcanus.Arcanus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sensitiveData = "SensitiveData"

        Arcanus.addString(this, SECRET_KEY, sensitiveData)
        val retrievedData = Arcanus.getString(this, SECRET_KEY)

        assert(sensitiveData == retrievedData)
    }

    companion object {
        const val SECRET_KEY = "SECRET_KEY"
    }
}
