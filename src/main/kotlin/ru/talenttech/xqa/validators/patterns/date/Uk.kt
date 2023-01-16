package ru.talenttech.xqa.validators.patterns.date

object Uk {
    fun apply(date: String) =
        ("^(([0-9])|([0-2][0-9])|(3[0-1]))\\/(([1-9])|(0[1-9])|(1[0-2]))\\/(([0-9][0-9])|([1-2][0,9][0-9][0-9]))\$")
            .toRegex().matches(date)
}
