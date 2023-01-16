package ru.talenttech.xqa.integration

import org.junit.Test
import ru.talenttech.xqa.Http
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.strategies.OkhttpStrategy
import ru.talenttech.xqa.request.Request

class HttpTest {

    private val config = HttpConfig.Builder().build()
    private val http = Http(config, OkhttpStrategy(config))
    private val request = Request("http://example.com/api")

    @Test
    fun getWitRequest() {
        http.get(request)
    }

    @Test
    fun postWithRequest() {
        http.post(request.apply { body = "" })
    }

    @Test
    fun putWithRequest() {
        http.put(request.apply { body = "" })
    }

    @Test
    fun deleteWithRequest() {
        http.delete(request)
    }

    @Test
    fun patchWithRequest() {
        http.patch(request.apply { body = "" })
    }

    @Test
    fun getWithParams() {
        http.get(url = "http://example.com/api")
    }

    @Test
    fun postWithParams() {
        http.post(url = "http://example.com/api", body = "test")
    }

    @Test
    fun putWithParams() {
        http.put(url = "http://example.com/api", body = "test")
    }

    @Test
    fun deleteWithParams() {
        http.delete(url = "http://example.com/api")
    }

    @Test
    fun patchWithParams() {
        http.patch(url = "http://example.com/api", body = "test")
    }
}
