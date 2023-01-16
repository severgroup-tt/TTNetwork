package ru.talenttech.xqa.validators.patterns.date

object Iso8601 {
    fun apply(date: String) = (
        "^(19[0-9]{2}|[2-9][0-9]{3})-((0(1|3|5|7|8)|10|12)-(0[1-9]|1[0-9]|2[0-9]|3[0-1])|" +
            "(0(4|6|9)|11)-(0[1-9]|1[0-9]|2[0-9]|30)|(02)-(0[1-9]|1[0-9]|2[0-9]))\\x20" +
            "(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}\$"
        ).toRegex().matches(date)
}
