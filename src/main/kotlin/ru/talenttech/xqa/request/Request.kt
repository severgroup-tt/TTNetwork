package ru.talenttech.xqa.request

import com.google.protobuf.Message
import java.io.File
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody
import ru.talenttech.xqa.Authentication
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.interceptors.RequestInterceptor
import ru.talenttech.xqa.interceptors.internal.UserAgentInterceptor
import ru.talenttech.xqa.response.parsers.Json
import ru.talenttech.xqa.response.parsers.Json.isSerializableCandidate

class Request(
    var url: String?,
    var urlParams: MutableList<String> = mutableListOf(),
    var queryParams: MutableMap<String, Any> = mutableMapOf(),
    var headers: MutableMap<String, String> = mutableMapOf(),
    var body: Any? = null
) {
    fun setUrl(url: String) = this.apply {
        this.url = url
    }

    fun setUrlParams(vararg params: String) = this.apply {
        urlParams.addAll(params)
    }

    fun addQueryParam(key: String, value: String) = this.apply {
        queryParams[key] = value
    }

    fun setQueryParams(vararg queryParams: Pair<String, String>) = this.apply {
        queryParams.forEach {
            this.queryParams[it.first] = it.second
        }
    }

    fun addHeader(key: String, value: String) = this.apply {
        headers[key] = value
    }

    fun setHeaders(vararg headers: Pair<String, String>) = this.apply {
        headers.forEach {
            this.headers[it.first] = it.second
        }
    }

    fun prepare(config: HttpConfig) = PreparedRequest(config)

    inner class PreparedRequest internal constructor(val config: HttpConfig) {
        var request: Request
        var url: String
        var headers: MutableMap<String, String>
        var body: Any?

        init {
            request = this@Request
            applyInternalInterceptors()
            applyInterceptors()

            url = this@Request.url ?: ""
            headers = this@Request.headers
            body = this@Request.body

            applyBaseUrl()
            replacePathParamsInUrl()
            applyQueryParams()
            applyPermanentHeaders()
            applyBasicAuthentication()
            applyBodyFormat()
        }

        private fun applyInternalInterceptors() {
            listOf(
                UserAgentInterceptor()
            ).forEach {
                request = it.interceptRequest(request)
            }
        }

        private fun applyInterceptors() {
            config.interceptors.forEach {
                if (it is RequestInterceptor) {
                    request = it.interceptRequest(request)
                }
            }
        }

        private fun applyBaseUrl() {
            config.baseUrl?.let {
                if (url.isBlank()) {
                    url = it.toHttpUrl().toString()
                } else if (!"""^(https?://)[-a-zA-Z0-9@:%._+~#={}]{2,256}\.[a-z{}]{2,6}/?""".toRegex().containsMatchIn(url)) {
                    var baseUrl = it
                    if (baseUrl.endsWith("/")) baseUrl = baseUrl.dropLast(1)
                    if (!url.startsWith("/")) url = "/$url"
                    url = (baseUrl + url).toHttpUrl().toString()
                }
            }
        }

        private fun applyQueryParams() {
            url = url.toHttpUrl().newBuilder().apply {
                this@Request.queryParams.forEach {
                    if (it.value is Iterable<*>) {
                        (it.value as Iterable<*>).forEach { v -> addEncodedQueryParameter(it.key, v.toString()) }
                    } else {
                        addEncodedQueryParameter(it.key, it.value.toString())
                    }
                }
            }
                .build()
                .toString()
        }

        private fun replacePathParamsInUrl() {
            this@Request.urlParams.forEach {
                url = Regex("""(\{|%7B)\w*(}|%7D)""").replaceFirst(url, it)
            }
        }

        private fun applyPermanentHeaders() {
            headers.putAll(config.headers)
        }

        private fun applyBasicAuthentication() {
            config.basicAuthenticationCredentials?.let {
                headers.put(HttpHeaders.AUTHORIZATION, Authentication.basic(it.first, it.second))
            }
        }

        private fun applyBodyFormat() {
            body = when {
                body is String || body is RequestBody || body is MultipartBody || body is ByteArray || body is File -> body
                body is Message -> (body as Message).toByteArray()
                isSerializableCandidate(body) -> Json.serialize(body!!)
                else -> null
            }
        }
    }
}
