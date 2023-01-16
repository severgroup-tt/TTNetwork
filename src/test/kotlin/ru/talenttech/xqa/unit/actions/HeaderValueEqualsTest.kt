package ru.talenttech.xqa.unit.actions

import kotlin.test.assertFails
import org.junit.Test
import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.actions.Condition
import ru.talenttech.xqa.actions.shouldBe
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.response.Response

class HeaderValueEqualsTest {
    private val response = Response(
        "h2",
        200,
        "https://example.com",
        mapOf(HttpHeaders.CONTENT_TYPE to listOf(ContentType.APPLICATION_JSON)),
        null,
        HttpConfig.Builder().build()
    )

    @Test
    fun checkHeaderValueEquals() {
        response.shouldBe(Condition.headerValueEquals(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON))
    }

    @Test
    fun checkNegativeHeaderValueEquals() {
        assertFails { response.shouldBe(Condition.headerValueEquals(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_PROTOBUF)) }
    }
}
