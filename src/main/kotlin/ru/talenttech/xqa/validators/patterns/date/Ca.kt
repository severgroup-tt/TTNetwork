package ru.talenttech.xqa.validators.patterns.date

object Ca {
    fun apply(date: String) = (
        "^((((19|20)(([02468][048])|([13579][26])).02.29))|((20[0-9][0-9])|(19[" +
            "0-9][0-9])).((((0[1-9])|(1[0-2])).((0[1-9])|(1[0-9])|(2[0-8])))" +
            "|((((0[13578])|(1[02])).31)|(((0[1,3-9])|(1[0-2])).(29|30)))))\$"
        ).toRegex().matches(date)
}
