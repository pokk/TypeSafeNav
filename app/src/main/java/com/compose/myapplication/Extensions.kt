package com.compose.myapplication

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(
        bundle: Bundle,
        key: String,
    ) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle.getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(
        bundle: Bundle,
        key: String,
        value: T,
    ) = bundle.putParcelable(key, value)
}

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(
        bundle: Bundle,
        key: String,
    ): T? {
        println("11111111111111111111111111111")
        return bundle.getString(key)?.let<String, T>(json::decodeFromString)
    }

    override fun parseValue(value: String): T {
        println("22222222222222222222222222222")
        return json.decodeFromString<T>(value).also(::println)
    }

    override fun serializeAsValue(value: T): String {
        println("33333333333333333333333333333")
        println(T::class)
        return json.encodeToString(value).also(::println)
    }

    override fun put(
        bundle: Bundle,
        key: String,
        value: T,
    ) {
        println("44444444444444444444444444444")
        println(value)
        bundle.putString(key, json.encodeToString(value))
    }
}
