package com.example.hashgenerator.com.example.hashgenerator

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import java.security.MessageDigest

class HomeViewModel(application: Application): AndroidViewModel(application)  {

    fun getHash(plainText: String, algorithmName: String): String{
        val bytes = MessageDigest.getInstance(algorithmName).digest(plainText.toByteArray())
        return toHex(byteArray = bytes)
    }

    fun toHex(byteArray: ByteArray): String{
        Log.d("HexCode", byteArray.joinToString("") {
            "%x".format(it) })

        return byteArray.joinToString("") {
            "%02x".format(it) }
    }
}