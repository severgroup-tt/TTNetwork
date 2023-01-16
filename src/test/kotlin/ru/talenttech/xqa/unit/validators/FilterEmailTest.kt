package ru.talenttech.xqa.unit.validators

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import ru.talenttech.xqa.validators.filters.ValidationFilters
import ru.talenttech.xqa.validators.validate

class FilterEmailTest {
    @Test
    fun testValidEmailAddresses() {
        val email = "kotlin-validators@alzakharov.com"
        assertTrue(
            email.validate { ValidationFilters.email(it) },
            "valid email: $email should return 'true'"
        )
    }

    @Test
    fun testInvalidEmailAddresses() {
        var email = "kotlin-validators@xqa"
        assertFalse(
            email.validate { ValidationFilters.email(it) },
            "invalid email should return 'false'"
        )

        email = "@xqa.com"
        assertFalse(
            email.validate { ValidationFilters.email(it) },
            "invalid email should return 'false'"
        )
    }
}
