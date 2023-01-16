package ru.talenttech.xqa.testData

import kotlin.test.assertEquals
import ru.talenttech.xqa.interceptors.RequestInterceptor
import ru.talenttech.xqa.interceptors.ResponseInterceptor
import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.response.Response

class TestInterceptor : RequestInterceptor, ResponseInterceptor {
    override fun interceptRequest(request: Request): Request {
        return request
            .setUrl(request.url + "/testInterceptor/{param}")
            .setUrlParams("testUrlParam")
            .setQueryParams("test_query" to "query_value")
            .addHeader("test_header", "header_value")
            .apply { body = "test_body" }
    }

    override fun interceptResponse(response: Response) {
        assertEquals("https://example.com/testInterceptor/testUrlParam?test_query=query_value", response.url)
    }
}
