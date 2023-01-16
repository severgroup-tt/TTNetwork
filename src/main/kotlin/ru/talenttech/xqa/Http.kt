package ru.talenttech.xqa

import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.request.Request

class Http(val config: HttpConfig, private val httpStrategy: HttpStrategy) : Client {
    fun get(request: Request) = httpStrategy.sendRequest(method = "GET", request = request)
    fun post(request: Request) = httpStrategy.sendRequest(method = "POST", request = request)
    fun put(request: Request) = httpStrategy.sendRequest(method = "PUT", request = request)
    fun delete(request: Request) = httpStrategy.sendRequest(method = "DELETE", request = request)
    fun patch(request: Request) = httpStrategy.sendRequest(method = "PATCH", request = request)

    fun get(
        url: String,
        urlParams: MutableList<String> = mutableListOf(),
        queryParams: MutableMap<String, Any> = mutableMapOf(),
        headers: MutableMap<String, String> = mutableMapOf(),
        body: Any? = null
    ) = httpStrategy.sendRequest(
        method = "GET",
        request = Request(
            url,
            urlParams,
            queryParams,
            headers,
            body
        )
    )

    fun post(
        url: String,
        urlParams: MutableList<String> = mutableListOf(),
        queryParams: MutableMap<String, Any> = mutableMapOf(),
        headers: MutableMap<String, String> = mutableMapOf(),
        body: Any? = null
    ) = httpStrategy.sendRequest(
        method = "POST",
        request = Request(
            url,
            urlParams,
            queryParams,
            headers,
            body
        )
    )

    fun put(
        url: String,
        urlParams: MutableList<String> = mutableListOf(),
        queryParams: MutableMap<String, Any> = mutableMapOf(),
        headers: MutableMap<String, String> = mutableMapOf(),
        body: Any? = null
    ) = httpStrategy.sendRequest(
        method = "PUT",
        request = Request(
            url,
            urlParams,
            queryParams,
            headers,
            body
        )
    )

    fun delete(
        url: String,
        urlParams: MutableList<String> = mutableListOf(),
        queryParams: MutableMap<String, Any> = mutableMapOf(),
        headers: MutableMap<String, String> = mutableMapOf(),
        body: Any? = null
    ) = httpStrategy.sendRequest(
        method = "DELETE",
        request = Request(
            url,
            urlParams,
            queryParams,
            headers,
            body
        )
    )

    fun patch(
        url: String,
        urlParams: MutableList<String> = mutableListOf(),
        queryParams: MutableMap<String, Any> = mutableMapOf(),
        headers: MutableMap<String, String> = mutableMapOf(),
        body: Any? = null
    ) = httpStrategy.sendRequest(
        method = "PATCH",
        request = Request(
            url,
            urlParams,
            queryParams,
            headers,
            body
        )
    )
}
