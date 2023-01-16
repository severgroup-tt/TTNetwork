package ru.talenttech.xqa.unit.interceptors

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.post
import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.testData.TestInterceptor

class InterceptorsTest {

    @Test
    fun `Apply request interceptor`() {
        val config = HttpConfig.Builder().interceptors(TestInterceptor()).build()
        val request = Request("https://example.com").prepare(config)
        assertEquals("https://example.com/testInterceptor/testUrlParam?test_query=query_value", request.url)
        assertEquals(request.headers["test_header"], "header_value")
        assertEquals(request.body, "test_body")
    }

    @Test
    fun `Apply response interceptor`() {
        val config = HttpConfig.Builder().interceptors(TestInterceptor()).build()
        Request("https://example.com").post(config)
    }
}
