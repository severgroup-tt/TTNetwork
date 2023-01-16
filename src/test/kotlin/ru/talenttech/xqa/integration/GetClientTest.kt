package ru.talenttech.xqa.integration

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.TTNetwork
import ru.talenttech.xqa.config.HttpConfig

class GetClientTest {
    @Test
    fun getHttpClient() {
        val config = HttpConfig.Builder().build()
        val client = TTNetwork().getHttpClient(config)
        assertEquals("class ru.talenttech.xqa.Http", client::class.toString())
    }

    @Test
    fun getHttpClientWithNullableConfig() {
        val client = TTNetwork().getHttpClient()
        assertEquals("class ru.talenttech.xqa.Http", client::class.toString())
    }

    @Test
    fun getDefaultHttpClient() {
        val config = HttpConfig.Builder().build()
        val client = TTNetwork().getHttpClient(config = config)
        assertEquals("class ru.talenttech.xqa.Http", client::class.toString())
    }

    @Test
    fun getDefaultHttpClientWithNullableConfig() {
        val client = TTNetwork().getHttpClient()
        assertEquals("class ru.talenttech.xqa.Http", client::class.toString())
    }
}
