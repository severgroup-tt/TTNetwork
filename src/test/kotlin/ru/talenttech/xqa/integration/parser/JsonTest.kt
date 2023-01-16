package ru.talenttech.xqa.integration.parser

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.TTNetwork
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.testData.JsonTestModel
import ru.talenttech.xqa.testData.JsonTestModelWithNullKey

class JsonTest {
    private val url = "http://echo.jsontest.com/insert-key-here/insert-value-here/key/value"

    private val client = TTNetwork().getHttpClient(
        HttpConfig
            .Builder()
            .baseUrl(url)
            .logging(true)
            .build()
    )

    @Test
    fun sendGetRequestAndGetJsonResponseWithJsonPath() {
        client.get(url).body<String>("insert-key-here").apply {
            assertEquals("insert-value-here", this)
        }
    }

    @Test
    fun sendGetRequestAndGetJsonResponseWithDeserialize() {
        client.get(url).body<JsonTestModel>().apply {
            assertEquals("insert-value-here", insertKeyHere)
            assertEquals("value", key)
        }
    }

    @Test
    fun sendGetRequestAndGetJsonResponseWithDeserializeByJsonPath() {
        client.get(url).body<JsonTestModelWithNullKey>("$").apply {
            assertEquals("insert-value-here", insertKeyHere)
            assertEquals("value", key)
            assertEquals(null, value)
        }
    }

    @Test
    fun sendGetRequestAndGetResponseAsJsonElement() {
        client.get(url).bodyAsJsonElement().let {
            assertEquals("""{"insert-key-here":"insert-value-here","key":"value"}""", it.toString())
        }
    }
}
