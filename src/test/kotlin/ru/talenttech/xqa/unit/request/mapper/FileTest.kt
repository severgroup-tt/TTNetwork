package ru.talenttech.xqa.unit.request.mapper

import java.io.File
import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.request.OkHttpRequestMapper
import ru.talenttech.xqa.request.Request

class FileTest {

    @Test
    fun checkContentType() {
        val file = File("")
        OkHttpRequestMapper().map(
            "POST",
            Request(
                url = "http://example.com",
                body = file,
                headers = mutableMapOf(HttpHeaders.CONTENT_TYPE to "image/jpeg")
            ).prepare(HttpConfig.Builder().build())
        ).apply {
            assertEquals("image/jpeg", (request as okhttp3.Request).body!!.contentType().toString())
        }
    }
}
