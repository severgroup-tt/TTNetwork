package ru.talenttech.xqa.validators.filters

import ru.talenttech.xqa.validators.formats.Formats
import ru.talenttech.xqa.validators.patterns.password.SixCharacters

class FilterPassword(
    private val password: String,
    private val format: Formats.Password
) : Filter {
    fun apply() =
        when (format.rule) {
            Formats.Password.SIX_CHARACTERS.rule -> SixCharacters.apply(password)
            else -> SixCharacters.apply(password)
        }
}
