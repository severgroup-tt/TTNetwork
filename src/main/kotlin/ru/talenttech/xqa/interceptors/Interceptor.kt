package ru.talenttech.xqa.interceptors

import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.response.Response

interface Interceptor

interface RequestInterceptor : Interceptor {
    fun interceptRequest(request: Request): Request
}

interface ResponseInterceptor : Interceptor {
    fun interceptResponse(response: Response)
}
