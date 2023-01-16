package ru.talenttech.xqa.unit.request.mapper

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.TTNetwork
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.request.OkHttpRequestMapper
import ru.talenttech.xqa.request.Request
import test_data.protobuf.UserProto

class ProtobufTest {
    private val url = "http://example.com/api"

    private val client = TTNetwork().getHttpClient(
        HttpConfig
            .Builder()
            .baseUrl(url)
            .build()
    )

    @Test
    fun checkContentType() {
        val body = UserProto.CreateRequest.newBuilder().setName("John").build().toByteArray()
        OkHttpRequestMapper().map(
            "POST",
            Request(
                url = url,
                body = body,
                headers = mutableMapOf(HttpHeaders.CONTENT_TYPE to ContentType.APPLICATION_PROTOBUF)
            ).prepare(HttpConfig.Builder().build())
        ).apply {
            assertEquals(ContentType.APPLICATION_PROTOBUF, (request as okhttp3.Request).body!!.contentType().toString())
        }
    }
}
