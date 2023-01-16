package ru.talenttech.xqa.validators.patterns.ip

object V4WithPort {
    fun apply(ip: String) = (
        "^((?:2[0-5]{2}|1\\d{2}|[1-9]\\d|[1-9])\\.(?:(?:2[0-5]{2}|1\\d{2}|[1-9]" +
            "\\d|\\d)\\.){2}(?:2[0-5]{2}|1\\d{2}|[1-9]\\d|\\d)):(\\d|[1-9]\\d|[" +
            "1-9]\\d{2,3}|[1-5]\\d{4}|6[0-4]\\d{3}|654\\d{2}|655[0-2]\\d|6553[0" +
            "-5])\$"
        ).toRegex().matches(ip)
}
