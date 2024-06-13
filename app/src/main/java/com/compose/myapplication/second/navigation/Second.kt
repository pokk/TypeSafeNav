package com.compose.myapplication.second.navigation

import android.os.Parcelable
import com.compose.myapplication.navigation.serializer.ZonedDateTimeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class Second(
    val book: Book,
    val ownTime: OwnZonedDateTime,
)

@JvmInline
@Parcelize
@Serializable
value class OwnZonedDateTime(
    @Serializable(ZonedDateTimeSerializer::class)
    val value: ZonedDateTime,
) : Parcelable

enum class Book(
    val title: String,
    val weight: Double,
) {
    A("num A", 1234.321),
    B("num B", 188.22),
    C("num C", 136.18),
}
