package ru.talenttech.xqa.validators.patterns.ip

object V4 {
    fun apply(ip: String) = (
        "^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})(\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})){3}\$"
        ).toRegex().matches(ip)
}
