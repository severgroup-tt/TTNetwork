package ru.talenttech.xqa.validators.filters

import ru.talenttech.xqa.validators.patterns.email.Email

class FilterEmail(private val email: String) : Filter {
    fun apply() = Email.apply(email)
}
