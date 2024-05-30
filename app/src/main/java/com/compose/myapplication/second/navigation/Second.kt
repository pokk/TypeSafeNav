package com.compose.myapplication.second.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Second(
    @Serializable(with = BookSerializer::class)
    val book: Book,
)

@Serializable
enum class Book {
    A,
    B,
    C,
}

object BookSerializer : KSerializer<Book> {
    override val descriptor: SerialDescriptor
        get() = Book.serializer().descriptor

    override fun deserialize(decoder: Decoder): Book {
        return decoder.decodeSerializableValue(Book.serializer())
    }

    override fun serialize(
        encoder: Encoder,
        value: Book,
    ) {
        encoder.encodeSerializableValue(Book.serializer(), value)
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
