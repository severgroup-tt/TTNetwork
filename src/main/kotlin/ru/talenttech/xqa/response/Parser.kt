package ru.talenttech.xqa.response

interface Parser {
    fun <T> getValueByPath(body: String, path: String): T
}
