package ru.talenttech.xqa.validators.filters

import ru.talenttech.xqa.validators.patterns.url.Url

class FilterUrl(private val url: String) {
    fun apply() = Url.apply(url)
}
