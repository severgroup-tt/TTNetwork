package ru.talenttech.xqa.validators.formats

object Formats {
    enum class Country(val code: String, val format: String) {
        USA("us", "m/d/yyyy"),
        GERMANY("de", "dd.mm.yyyy"),
        UNITED_KINGDOM("uk", "dd/mm/yyyy"),
        VIETNAM("vn", "dd/mm/yyyy"),
        INDONESIA("id", "dd/mm/yyyy"),
        SPAIN("es", "dd/mm/yyyy"),
        ITALY("it", "dd/mm/yyyy"),
        FRANCE("fr", "dd/mm/yyyy"),
        BRAZIL("br", "d/m/yyyy"),
        GREECE("gr", "d/m/yyyy"),
        THAILAND("th", "d/m/yyyy"),
        BULGARIA("bg", "dd.mm.yyyy"),
        NORWAY("no", "dd.mm.yyyy"),
        ROMANIA("ro", "dd.mm.yyyy"),
        RUSSIA("ru", "dd.mm.yyyy"),
        SLOVENIA("si", "dd.mm.yyyy"),
        TURKEY("tr", "dd.mm.yyyy"),
        UKRAINE("ua", "dd.mm.yyyy"),
        LATVIA("lv", "dd.mm.yyyy"),
        DENMARK("dk", "dd-mm-yyyy"),
        PORTUGAL("pt", "dd-mm-yyyy"),
        HONG_KONG("hk", "yyyy/m/d"),
        TAIWAN("tw", "yyyy/m/d"),
        CHINA("cn", "yyyy-m-d"),
        IRAN("ir", "yyyy/mm/dd"),
        JAPAN("jp", "yyyy/mm/dd"),
        CANADA("ca", "yyyy-mm-dd"),
        POLAND("pl", "yyyy-mm-dd"),
        SWEDEN("se", "yyyy-mm-dd"),
        LITHUANIA("lt", "yyyy-mm-dd"),
        HUNGARY("hu", "yyyy.mm.dd"),
        ISO_8601("iso8601", "ISO-8061")
    }

    enum class Ip(val version: String) {
        V4("v4"),
        V6("v6"),
        V4_WITH_PORT("v4:port")
    }

    enum class Password(val rule: String) {
        SIX_CHARACTERS("six characters")
    }
}
