package ru.talenttech.xqa.validators.filters

import ru.talenttech.xqa.validators.patterns.hex.HexColor

class FilterHexColor(private val color: String) : Filter {
    fun apply() = HexColor.apply(color)
}
