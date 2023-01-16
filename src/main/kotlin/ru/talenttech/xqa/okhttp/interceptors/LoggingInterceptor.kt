package ru.talenttech.xqa.okhttp.interceptors

import mu.KotlinLogging
import okhttp3.Interceptor
import okhttp3.Response
import ru.talenttech.xqa.utils.LoggingConverter

private val loggerRequest = KotlinLogging.logger("REQUEST")
private val loggerResponse = KotlinLogging.logger("RESPONSE")
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        LoggingConverter.getRequestParametersForLogging(request).also {
            loggerRequest.info("\n$it")
        }
        val response = chain.proceed(request)
        LoggingConverter.getResponseParametersForLogging(response).also {
            loggerResponse.info("\n$it\n==================================================")
        }
        return response
    }
}
