package ru.talenttech.xqa.unit.interceptors

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.properties.LibraryInfo
import ru.talenttech.xqa.request.Request

class InternalInterceptorsTest {

    @Test
    fun `UserAgent internal interceptor`() {
        val request = Request("https://example.com").prepare(HttpConfig.Builder().build())
        assertEquals(request.headers[HttpHeaders.USER_AGENT], "${LibraryInfo.name()}/${LibraryInfo.version()}")
    }
}
