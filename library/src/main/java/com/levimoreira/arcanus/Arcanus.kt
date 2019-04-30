package com.levimoreira.arcanus

import android.content.Context
import com.levimoreira.arcanus.Constants.DATA_NOT_FOUND

object Arcanus {

    private var alias: String = ""
    fun init(context: Context, alias: String, company: String, organization: String) {
        this.alias = alias
        KeystoreKeeper(context).addKeyForAlias(alias, company, organization)
    }


    fun addString(context: Context, key: String, data: String) {
        if (alias.isEmpty()) throw IllegalStateException("You must call init before adding data to the keystore")

        val prefs = context.getSharedPreferences(
                Constants.SECURE_PREFS,
                Context.MODE_PRIVATE
        )

        val safeData = KeystoreKeeper(context).encryptText(data, alias)
        prefs.edit().putString(key, safeData).apply()
    }

    fun getString(context: Context, key: String): String {
        if (alias.isEmpty()) throw IllegalStateException("You must call init before adding data to the keystore")

        val prefs = context.getSharedPreferences(
                Constants.SECURE_PREFS,
                Context.MODE_PRIVATE
        )

        val encryptedData = prefs.getString(key, "") as String

        if (encryptedData.isEmpty()) {
            return DATA_NOT_FOUND
        }
        return KeystoreKeeper(context).decryptText(alias, encryptedData) ?: DATA_NOT_FOUND
    }
}
