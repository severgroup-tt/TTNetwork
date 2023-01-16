package ru.talenttech.xqa.unit.request.mapper

import java.io.File
import kotlin.test.assertTrue
import okhttp3.Request
import org.junit.Test
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.request.OkHttpRequestMapper
import ru.talenttech.xqa.request.MultipartBody

class MultipartBodyTest {

    @Test
    fun checkContentType() {
        val file = File.createTempFile("test", ".jpg")
        val multipartBody = MultipartBody()
            .addFormDataPart("test_name", "test_value")
            .addFormDataPart("test_name_2", file)
        OkHttpRequestMapper().map(
            "POST",
            ru.talenttech.xqa.request.Request(
                url = "http://example.com",
                body = multipartBody,
                headers = mutableMapOf(HttpHeaders.CONTENT_TYPE to "image/jpeg")
            ).prepare(HttpConfig.Builder().build())
        ).apply {
            assertTrue((request as Request).body!!.contentType().toString().startsWith("multipart/form-data; boundary="))
        }
    }
}
