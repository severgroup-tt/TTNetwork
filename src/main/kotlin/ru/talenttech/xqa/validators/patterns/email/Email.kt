package ru.talenttech.xqa.validators.patterns.email

object Email {
    fun apply(email: String) = (
        "(?:[a-z\\d!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z\\d!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c" +
            "\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:" +
            "[a-z\\d](?:[a-z\\d-]*[a-z\\d])?\\.)+[a-z\\d](?:[a-z\\d-]*[a-z\\d])?|\\[(?:(?:25[0-5]|2[0-4" +
            "]\\d|[01]?\\d\\d?)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?|[a-z\\d-]*[a-z\\d]:(?:[\\x01-\\" +
            "x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
        )
        .toRegex().matches(email)
}
