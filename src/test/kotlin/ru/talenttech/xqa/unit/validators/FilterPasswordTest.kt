package ru.talenttech.xqa.unit.validators

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import ru.talenttech.xqa.validators.filters.ValidationFilters
import ru.talenttech.xqa.validators.formats.Formats
import ru.talenttech.xqa.validators.validate

class FilterPasswordTest {
    @Test
    fun testValidSixCharactersPassword() {
        var password = "a1b2c3"
        assertTrue(
            password.validate { ValidationFilters.password(it, Formats.Password.SIX_CHARACTERS) },
            "valid password: $password should return 'true'"
        )

        password = "abcdefg123"
        assertTrue(
            password.validate { ValidationFilters.password(it, Formats.Password.SIX_CHARACTERS) },
            "valid password: $password should return 'true'"
        )
    }

    @Test
    fun testInvalidSixCharactersPassword() {
        var password = "abcdefghij"
        assertFalse(
            password.validate { ValidationFilters.password(it, Formats.Password.SIX_CHARACTERS) },
            "invalid password: $password should return 'false'"
        )

        password = "1234567890"
        assertFalse(
            password.validate { ValidationFilters.password(it, Formats.Password.SIX_CHARACTERS) },
            "invalid password: $password should return 'false'"
        )
    }
}
