package ru.talenttech.xqa.unit.actions

import org.junit.Test
import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.actions.Condition
import ru.talenttech.xqa.actions.shouldBe
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.response.Response

class MultiConditionTest {
    private val response = Response(
        "h2",
        200,
        "https://example.com",
        mapOf(HttpHeaders.CONTENT_TYPE to listOf(ContentType.APPLICATION_JSON)),
        null,
        HttpConfig.Builder().build()
    )

    @Test
    fun checkMultiCondition() {
        response.shouldBe(
            Condition.headerValueEquals(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON),
            Condition.headerAvailable(HttpHeaders.CONTENT_TYPE),
            Condition.codeEquals(200)
        )
    }
}
