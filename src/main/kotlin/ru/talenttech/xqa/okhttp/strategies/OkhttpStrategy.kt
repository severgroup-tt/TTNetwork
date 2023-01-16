package ru.talenttech.xqa.okhttp.strategies

import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import ru.talenttech.xqa.HttpStrategy
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.interceptors.LoggingInterceptor
import ru.talenttech.xqa.okhttp.request.OkHttpRequestMapper
import ru.talenttech.xqa.okhttp.response.mapToTtNetworkResponse
import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.request.RequestMapper

class OkhttpStrategy(
    override val config: HttpConfig,
    private val requestMapper: RequestMapper = OkHttpRequestMapper()
) : HttpStrategy {
    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        if (config.logging) addNetworkInterceptor(LoggingInterceptor())
        readTimeout(config.timeOut.toLong(), TimeUnit.SECONDS)
        followRedirects(config.followRedirects)
        followSslRedirects(config.followProtocolRedirects)
    }.build()

    override fun sendRequest(method: String, request: Request) =
        client.newCall(
            requestMapper.map(method, request.prepare(config)).request as okhttp3.Request
        ).execute().mapToTtNetworkResponse(config)
}
