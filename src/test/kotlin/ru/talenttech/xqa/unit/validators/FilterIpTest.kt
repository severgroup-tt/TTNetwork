package ru.talenttech.xqa.unit.validators

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import ru.talenttech.xqa.validators.filters.ValidationFilters
import ru.talenttech.xqa.validators.formats.Formats
import ru.talenttech.xqa.validators.validate

class FilterIpTest {
    @Test
    fun testValidIpV4() {
        val ipv4 = "97.67.44.20"
        assertTrue(
            ipv4.validate { ValidationFilters.ip(it, Formats.Ip.V4) },
            "valid ipv4: $ipv4 should return 'true'"
        )
    }

    @Test
    fun testInvalidIpV4() {
        val ipv4 = "63.125.94.287"
        assertFalse(
            ipv4.validate { ValidationFilters.ip(it, Formats.Ip.V4) },
            "invalid ipv4: $ipv4 should return 'false'"
        )
    }

    @Test
    fun testValidIpV4WithPort() {
        val ipv4WithPort = "97.67.44.20:8090"
        assertTrue(
            ipv4WithPort.validate { ValidationFilters.ip(it, Formats.Ip.V4_WITH_PORT) },
            "valid ipv4 with port: $ipv4WithPort should return 'true'"
        )
    }

    @Test
    fun testInvalidIpV4WithPort() {
        val ipv4WithPort = "63.125.94.20"
        assertFalse(
            ipv4WithPort.validate { ValidationFilters.ip(it, Formats.Ip.V4_WITH_PORT) },
            "invalid ipv4 with port: $ipv4WithPort should return 'false'"
        )
    }

    @Test
    fun testValidIpV6() {
        val ipv6 = "FEDC:BA98:7654:3210:FEDC:BA98:7654:3210"
        assertTrue(
            ipv6.validate { ValidationFilters.ip(it, Formats.Ip.V6) },
            "valid ipv6: $ipv6 should return 'true'"
        )
    }

    @Test
    fun testInvalidIpV6() {
        val ipv6 = "63.125.94.20"
        assertFalse(
            ipv6.validate { ValidationFilters.ip(it, Formats.Ip.V6) },
            "invalid ipv6: $ipv6 should return 'false'"
        )
    }
}
