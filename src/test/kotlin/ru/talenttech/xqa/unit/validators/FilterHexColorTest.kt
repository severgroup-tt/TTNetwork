package ru.talenttech.xqa.unit.validators

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import ru.talenttech.xqa.validators.filters.ValidationFilters
import ru.talenttech.xqa.validators.validate

class FilterHexColorTest {
    @Test
    fun testValidHexColor() {
        val color = "#00ccff"
        assertTrue(
            color.validate { ValidationFilters.hexColor(it) },
            "valid email: $color should return 'true'"
        )
    }

    @Test
    fun testInvalidHexColor() {
        val color = "#ff000"
        assertFalse(
            color.validate { ValidationFilters.hexColor(it) },
            "invalid color: $color should return 'false'"
        )
    }
}
