package ru.talenttech.xqa.interceptors.internal

import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.interceptors.RequestInterceptor
import ru.talenttech.xqa.properties.LibraryInfo
import ru.talenttech.xqa.request.Request

class UserAgentInterceptor : RequestInterceptor {
    override fun interceptRequest(request: Request): Request {
        return request.addHeader(HttpHeaders.USER_AGENT, "${LibraryInfo.name()}/${LibraryInfo.version()}")
    }
}
