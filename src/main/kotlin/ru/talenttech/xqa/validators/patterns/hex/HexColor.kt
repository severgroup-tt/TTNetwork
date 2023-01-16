package ru.talenttech.xqa.validators.patterns.hex

object HexColor {
    fun apply(email: String) = (
        "^#?([a-f]|[A-F]|[0-9]){3}(([a-f]|[A-F]|[0-9]){3})?\$"
        )
        .toRegex().matches(email)
}
