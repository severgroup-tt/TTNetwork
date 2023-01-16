package ru.talenttech.xqa.validators.patterns.password

object SixCharacters {
    fun apply(password: String) = (
        "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}\$"
        ).toRegex().matches(password)
}
