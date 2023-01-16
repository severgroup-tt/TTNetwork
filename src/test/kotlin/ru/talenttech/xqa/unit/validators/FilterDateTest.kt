package ru.talenttech.xqa.unit.validators

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.Test
import ru.talenttech.xqa.validators.filters.ValidationFilters
import ru.talenttech.xqa.validators.formats.Formats
import ru.talenttech.xqa.validators.validate

class FilterDateTest {
    @Test
    fun testValidDateWithUsFormat() {
        val date = "3/23/2023"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.USA) },
            "valid date: $date should return 'true' for US format"
        )
    }

    @Test
    fun testInvalidDateWithUsFormat() {
        val date = "2023/23/3"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.USA) },
            "invalid date: $date should return 'false' for US format"
        )
    }

    @Test
    fun testValidDateWithUkFormat() {
        val date = "30/10/1967"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.UNITED_KINGDOM) },
            "valid date: $date should return 'true' for UK format"
        )
    }

    @Test
    fun testInvalidDateWithUkFormat() {
        val date = "1967/10/30"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.UNITED_KINGDOM) },
            "invalid date: $date should return 'false' for UK format"
        )
    }

    @Test
    fun testValidDateWithDeFormat() {
        val date = "20.11.2009"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.GERMANY) },
            "valid date: $date should return 'true' for DE format"
        )
    }

    @Test
    fun testInvalidDateWithDeFormat() {
        val date = "11.20.2009"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.GERMANY) },
            "invalid date: $date should return 'false' for DE format"
        )
    }

    @Test
    fun testValidDateWithDkFormat() {
        val date = "29-02-2004"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.DENMARK) },
            "valid date: $date should return 'true' for DK format"
        )
    }

    @Test
    fun testInvalidDateWithDkFormat() {
        val date = "02-29-2004"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.DENMARK) },
            "invalid date: $date should return 'false' for DK format"
        )
    }

    @Test
    fun testValidDateWithHkFormat() {
        val date = "2006/5/30"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.HONG_KONG) },
            "valid date: $date should return 'true' for HK format"
        )
    }

    @Test
    fun testInvalidDateWithHkFormat() {
        val date = "2006/30/5"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.HONG_KONG) },
            "invalid date: $date should return 'false' for HK format"
        )
    }

    @Test
    fun testValidDateWithCnFormat() {
        val date = "2006-5-30"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.HONG_KONG) },
            "valid date: $date should return 'true' for CN format"
        )
    }

    @Test
    fun testInvalidDateWithCnFormat() {
        val date = "2006-30-5"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.HONG_KONG) },
            "invalid date: $date should return 'false' for CN format"
        )
    }

    @Test
    fun testValidDateWithCaFormat() {
        val date = "2004-04-30"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.CANADA) },
            "valid date: $date should return 'true' for CA format"
        )
    }

    @Test
    fun testInvalidDateWithCaFormat() {
        val date = "2004-30-04"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.CANADA) },
            "invalid date: $date should return 'false' for CA format"
        )
    }

    @Test
    fun testValidDateWithHuFormat() {
        val date = "2004.04.30"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.CANADA) },
            "valid date: $date should return 'true' for HU format"
        )
    }

    @Test
    fun testInvalidDateWithHuFormat() {
        val date = "2004.30.04"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.CANADA) },
            "invalid date: $date should return 'false' for HU format"
        )
    }

    @Test
    fun testValidDateWithIso8601Format() {
        val date = "2004-07-12 14:25:59"
        assertTrue(
            date.validate { ValidationFilters.date(it, Formats.Country.ISO_8601) },
            "valid date: $date should return 'true' for ISO 8601 format"
        )
    }

    @Test
    fun testInvalidDateWithIso8601Format() {
        val date = "04-07-12 14:25:59"
        assertFalse(
            date.validate { ValidationFilters.date(it, Formats.Country.ISO_8601) },
            "invalid date: $date should return 'false' for ISO 8601 format"
        )
    }
}
