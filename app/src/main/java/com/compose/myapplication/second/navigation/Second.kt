package com.compose.myapplication.second.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class Second(
    val book: Book,
    @Serializable(ZonedDateTimeSerializer::class)
    val time: ZonedDateTime,
)

enum class Book {
    A,
    B,
    C,
}

object ZonedDateTimeSerializer : KSerializer<ZonedDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(ZonedDateTimeSerializer::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: ZonedDateTime,
    ) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): ZonedDateTime {
        val string = decoder.decodeString()
        return ZonedDateTime.parse(string)
    }
}

val bookType =
    object : NavType<Book>(isNullableAllowed = false) {
        override fun get(
            bundle: Bundle,
            key: String,
        ): Book? {
            return bundle.getSerializable(key) as? Book
        }

        override fun parseValue(value: String): Book {
            return Book.valueOf(value)
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: Book,
        ) {
            bundle.putSerializable(key, value)
        }
    }

val zonedDateTimeType =
    object : NavType<ZonedDateTime>(isNullableAllowed = true) {
        override fun put(
            bundle: Bundle,
            key: String,
            value: ZonedDateTime,
        ) {
            bundle.putString(key, value.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
        }

        override fun get(
            bundle: Bundle,
            key: String,
        ): ZonedDateTime? {
            return bundle.getString(key)?.let { ZonedDateTime.parse(it, DateTimeFormatter.ISO_ZONED_DATE_TIME) }
        }

        override fun parseValue(value: String): ZonedDateTime {
            return ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        }
    }
