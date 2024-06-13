package com.compose.myapplication.navigation.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object ZonedDateTimeSerializer : KSerializer<ZonedDateTime> {
    private val formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssXXX")

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(ZonedDateTimeSerializer::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: ZonedDateTime,
    ) {
        encoder.encodeString(value.format(formatter))
    }

    override fun deserialize(decoder: Decoder): ZonedDateTime = ZonedDateTime.parse(decoder.decodeString(), formatter)
}
