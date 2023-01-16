package ru.talenttech.xqa.unit.validators

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import ru.talenttech.xqa.validators.filters.ValidationFilters
import ru.talenttech.xqa.validators.validate

class FilterUrlTest {
    @Test
    fun testValidUrl() {
        var url = "http://test.com/test.aspx"
        assertTrue(
            url.validate { ValidationFilters.url(it) },
            "valid url: $url should return 'true'"
        )

        url = "https://test.com/test.aspx"
        assertTrue(
            url.validate { ValidationFilters.url(it) },
            "valid url: $url should return 'true'"
        )
    }

    @Test
    fun testInvalidUrl() {
        val url = "www.test.com/test.aspx"
        assertFalse(
            url.validate { ValidationFilters.url(it) },
            "invalid url: $url should return 'false'"
        )
    }
}
