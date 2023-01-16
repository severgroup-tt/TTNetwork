package ru.talenttech.xqa.unit

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.response.mapToTtNetworkResponse
import ru.talenttech.xqa.response.Response

class ResponseMapperTest {
    @Test
    fun sendGetRequestAndGetJsonResponse() {
        Response(
            "h2",
            200,
            "https://example.com",
            mapOf(HttpHeaders.CONTENT_TYPE to listOf(ContentType.TEXT_XML)),
            body = """<?xml version="1.0" encoding="UTF-8"?>
                      <root>
                        <count>2</count>
                        <users>
                          <age>27</age>
                          <first_name>John</first_name>
                          <last_name>Doe</last_name>
                        </users>
                        <users>
                          <age>35</age>
                          <first_name>Vasya</first_name>
                          <last_name>Pupkin</last_name>
                        </users>
                      </root>""",
            HttpConfig.Builder().build()
        ).body<String>("$.users[0].first_name").apply {
            assertEquals("John", this)
        }
    }

    @Test
    fun mapBodyAsString() {
        val request = Request.Builder().url("http://example.com").build()
        okhttp3.Response.Builder()
            .code(200)
            .message("OK")
            .request(request)
            .protocol(Protocol.HTTP_2)
            .body("test".toResponseBody(ContentType.APPLICATION_JSON.toMediaTypeOrNull()))
            .build()
            .mapToTtNetworkResponse(HttpConfig.Builder().build()).apply {
                assertTrue(body is String)
            }
    }

    @Test
    fun mapBodyAsByteArray() {
        val request = Request.Builder().url("http://example.com").build()
        okhttp3.Response.Builder()
            .code(200)
            .message("OK")
            .request(request)
            .protocol(Protocol.HTTP_2)
            .body("test".toResponseBody(ContentType.APPLICATION_PROTOBUF.toMediaTypeOrNull()))
            .build()
            .mapToTtNetworkResponse(HttpConfig.Builder().build()).apply {
                assertTrue(body is ByteArray)
            }
    }

    @Test
    fun mapIfBodyNull() {
        val request = Request.Builder().url("http://example.com").build()
        okhttp3.Response.Builder()
            .code(200)
            .message("OK")
            .request(request)
            .protocol(Protocol.HTTP_2)
            .body(null)
            .build()
            .mapToTtNetworkResponse(HttpConfig.Builder().build()).apply {
                assertEquals(null, body)
            }
    }
}
