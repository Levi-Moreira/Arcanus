package com.levimoreira.arcanus

import android.content.Context
import android.security.KeyPairGeneratorSpec
import com.levimoreira.arcanus.Constants.KEYSTORE_TIMOUT_YEARS
import com.levimoreira.arcanus.Constants.KEY_STORE
import com.levimoreira.arcanus.Constants.RSA
import timber.log.Timber
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.util.*
import javax.security.auth.x500.X500Principal


class KeystoreKeeper(private val context: Context) {
    private val keyStore = KeyStore.getInstance(KEY_STORE)
    fun addKeyForAlias(alias: String, companyName: String, organization: String) {
        keyStore.load(null)
        try {
            // Create new key if needed
            if (!keyStore.containsAlias(alias)) {
                val start = Calendar.getInstance()
                val end = Calendar.getInstance()
                end.add(Calendar.YEAR, KEYSTORE_TIMOUT_YEARS)
                val spec = KeyPairGeneratorSpec.Builder(context)
                        .setAlias(alias)
                        .setSubject(X500Principal("CN=$companyName, O=$organization"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.time)
                        .setEndDate(end.time)
                        .build()
                val generator = KeyPairGenerator.getInstance(RSA, KEY_STORE)
                generator.initialize(spec)
                generator.generateKeyPair()
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun encryptText(text: String, alias: String): String? {
        return StoreHelper.addString(keyStore, alias, text)
    }

    fun decryptText(alias: String, encodedText: String): String? {
        return StoreHelper.retrieveString(keyStore, alias, encodedText)
    }
}