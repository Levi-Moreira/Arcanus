package com.levimoreira.arcanus

import android.util.Base64
import timber.log.Timber
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream

object StoreHelper {
    fun addString(keyStore: KeyStore, alias: String, text: String): String? {
        keyStore.load(null)
        try {
            val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            val publicKey = privateKeyEntry.certificate.publicKey

            CipherProvider.provideCipher()?.let {
                it.init(Cipher.ENCRYPT_MODE, publicKey)

                val outputStream = ByteArrayOutputStream()
                val cipherOutputStream = CipherOutputStream(
                        outputStream, it
                )
                cipherOutputStream.write(text.toByteArray(Charsets.UTF_8))
                cipherOutputStream.close()

                return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }

    fun retrieveString(keyStore: KeyStore, alias: String, cipherText: String): String? {
        keyStore.load(null)
        try {

            val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            val privateKey = privateKeyEntry.privateKey

            CipherProvider.provideCipher()?.let {
                it.init(Cipher.DECRYPT_MODE, privateKey)

                val cipherInputStream = CipherInputStream(
                        ByteArrayInputStream(Base64.decode(cipherText, Base64.NO_WRAP)), it
                )
                val values = arrayListOf<Byte>()
                var nextByte = cipherInputStream.read()
                while ((nextByte) != -1) {
                    values.add(nextByte.toByte())
                    nextByte = cipherInputStream.read()
                }

                val bytes = ByteArray(values.size)
                for (i in bytes.indices) {
                    bytes[i] = values.get(i)
                }

                return String(bytes, Constants.INITIAL_OFFSET, bytes.size, Charsets.UTF_8)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }
}