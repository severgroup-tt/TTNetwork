package ru.talenttech.xqa.integration.request

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.TTNetwork
import ru.talenttech.xqa.config.HttpConfig

class JsonTest {
    private val url = "https://google.com"

    private val client = TTNetwork().getHttpClient(
        HttpConfig
            .Builder()
            .followRedirects(true)
            .logging(true)
            .baseUrl(url)
            .build()
    )

    @Test
    fun sendGetRequest() {
        client
            .get(url).apply {
                assertEquals(200, this.code)
                assertEquals(url, this.url)
                assertEquals("h2", this.protocol)
                assertEquals(true, this.headers.isNotEmpty())
            }
    }

    @Test
    fun sendGetRequestWithoutRequestObject() {
        client
            .get(url).apply {
                assertEquals(200, this.code)
                assertEquals(url, this.url)
                assertEquals("h2", this.protocol)
                assertEquals(true, this.headers.isNotEmpty())
            }
    }
}
