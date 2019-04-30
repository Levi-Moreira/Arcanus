package com.levimoreira.arcanus

import android.os.Build
import timber.log.Timber
import javax.crypto.Cipher

object CipherProvider {
    fun provideCipher(): Cipher? {
        try {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // below android m
                Cipher.getInstance(
                        Constants.RSA_CONFIG,
                        Constants.OPEN_SSL_PROVIDER
                )
            } else {
                Cipher.getInstance(
                        Constants.RSA_CONFIG,
                        Constants.BC_WORKAROUND_PROVIDER
                )
            }
        } catch (exception: Exception) {
            Timber.d(exception)
        }
        return null
    }
}