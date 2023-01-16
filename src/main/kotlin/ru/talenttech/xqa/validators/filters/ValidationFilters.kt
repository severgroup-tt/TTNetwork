package ru.talenttech.xqa.validators.filters

import ru.talenttech.xqa.validators.formats.Formats

object ValidationFilters {
    /**
     * Email validator
     */
    fun email(email: String) =
        FilterEmail(email).apply()

    /**
     * Date validator
     */
    fun date(date: String, dateFormat: Formats.Country = Formats.Country.GERMANY) =
        FilterDate(date, dateFormat).apply()

    /**
     * IP validator
     */
    fun ip(ip: String, ipFormat: Formats.Ip = Formats.Ip.V4) =
        FilterIp(ip, ipFormat).apply()

    /**
     * Url validator
     */
    fun url(url: String) =
        FilterUrl(url).apply()

    /**
     * Hex color validator
     */
    fun hexColor(color: String) =
        FilterHexColor(color).apply()

    /**
     * Password validator
     */
    fun password(password: String, passwordFormat: Formats.Password = Formats.Password.SIX_CHARACTERS) =
        FilterPassword(password, passwordFormat).apply()
}
