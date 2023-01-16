package ru.talenttech.xqa.config

import ru.talenttech.xqa.interceptors.Interceptor

open class HttpConfig(private val config: Builder) : Config {
    val baseUrl: String? = config.baseUrl
    val headers: Map<String, String> = config.headers
    val basicAuthenticationCredentials: Pair<String, String>? = config.basicAuthenticationCredentials
    val timeOut: Int = config.timeOut
    val logging: Boolean = config.logging
    val followRedirects: Boolean = config.followRedirects
    val followProtocolRedirects: Boolean = config.followProtocolRedirects
    val interceptors: List<Interceptor> = config.interceptors

    class Builder {
        internal var baseUrl: String? = null
        internal var headers: Map<String, String> = mutableMapOf()
        internal var basicAuthenticationCredentials: Pair<String, String>? = null
        internal var timeOut: Int = 5
        internal var logging: Boolean = false
        internal var followRedirects: Boolean = false
        internal var followProtocolRedirects: Boolean = false
        internal var interceptors: MutableList<Interceptor> = mutableListOf()

        fun baseUrl(value: String?) = apply { baseUrl = value }
        fun headers(value: Map<String, String>) = apply { headers = value }
        fun basicAuthentication(value: Pair<String, String>?) = apply { basicAuthenticationCredentials = value }
        fun timeOut(value: Int) = apply { timeOut = value }
        fun logging(value: Boolean) = apply { logging = value }
        fun followRedirects(value: Boolean) = apply { followRedirects = value }
        fun followProtocolRedirects(value: Boolean) = apply { followProtocolRedirects = value }
        fun interceptors(vararg interceptor: Interceptor) = apply { interceptors.addAll(interceptor) }

        fun build() = HttpConfig(this)
    }
}
