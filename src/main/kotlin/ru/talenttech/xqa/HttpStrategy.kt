package ru.talenttech.xqa

import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.response.Response

interface HttpStrategy {
    val config: HttpConfig
    fun sendRequest(method: String, request: Request): Response
}
