package ru.talenttech.xqa.integration

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.delete
import ru.talenttech.xqa.get
import ru.talenttech.xqa.patch
import ru.talenttech.xqa.post
import ru.talenttech.xqa.put
import ru.talenttech.xqa.request.Request

class RequestMethodsTest {
    private val url = "https://google.com"

    @Test
    fun methodGet() {
        Request(url).get().apply {
            assertEquals(301, code)
        }
    }

    @Test
    fun methodGetWithConfig() {
        Request(url).get(HttpConfig.Builder().followRedirects(true).build()).apply {
            assertEquals(200, code)
        }
    }

    @Test
    fun methodPost() {
        Request(url = url, body = "").post().apply {
            assertEquals(405, code)
        }
    }

    @Test
    fun methodPut() {
        Request(url = url, body = "").put().apply {
            assertEquals(405, code)
        }
    }

    @Test
    fun methodDelete() {
        Request(url = url).delete().apply {
            assertEquals(405, code)
        }
    }

    @Test
    fun methodPatch() {
        Request(url = url, body = "").patch().apply {
            assertEquals(405, code)
        }
    }
}
