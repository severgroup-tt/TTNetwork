package ru.talenttech.xqa.integration.config

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.TTNetwork
import ru.talenttech.xqa.config.HttpConfig

class ConfigTest {
    @Test
    fun initializationConfig() {
        val config = HttpConfig.Builder().baseUrl("https://google.com").build()
        assertEquals("https://google.com", config.baseUrl)
    }

    @Test
    fun getConfigFromClient() {
        val config = HttpConfig.Builder().baseUrl("https://google.com").build()
        val client = TTNetwork().getHttpClient(config = config)
        assertEquals("https://google.com", client.config.baseUrl)
    }
}
