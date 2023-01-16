package ru.talenttech.xqa.utils

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import okhttp3.Headers
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.GzipSource
import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.response.parsers.Json

object LoggingConverter {

    fun getRequestParametersForLogging(request: Request) = StringBuilder()
        .apply {
            appendLine("${request.method}: ${request.url}")
            appendLine("Headers:")
            request.headers.toLoggingMap().forEach { appendLine("    ${it.key}: ${it.value}") }
            request.body?.let {
                it.toLoggingString(request.headers).apply { if (isNotBlank()) appendLine("Body:\n$it") }
            }
            appendLine()
            // appendLine(getCurl(request))
        }
        .toString()

    fun getResponseParametersForLogging(response: Response) = StringBuilder()
        .apply {
            with(response.receivedResponseAtMillis - response.sentRequestAtMillis) {
                appendLine("Time: ${if (this > 999) "${this / 1000.0} s" else "$this ms"}")
            }
            appendLine("Protocol: ${response.protocol}")
            appendLine("Code: ${response.code}")
            if (response.message.isNotBlank()) appendLine("message: ${response.message}")
            appendLine("Headers:")
            response.headers.toLoggingMap().forEach { appendLine("    ${it.key}: ${it.value}") }
            response.body?.let { it.toLoggingString(response.headers).apply { if (isNotBlank()) appendLine("Body:\n$this") } }
        }
        .toString()

    private fun RequestBody.toLoggingString(headers: Headers, prettyPrint: Boolean = true): String {
        var requestBodyString = Buffer().also { this.writeTo(it) }.readUtf8()
        headers[HttpHeaders.CONTENT_TYPE]?.let {
            if (it.contains(ContentType.APPLICATION_JSON, true) && prettyPrint) requestBodyString = Json.prettyPrint(requestBodyString)
        }
        return requestBodyString
    }

    private fun ResponseBody.toLoggingString(headers: Headers): String {
        val charset: Charset = this.contentType()?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        val source = this.source()
        source.request(Long.MAX_VALUE)
        var responseBodyBuffer = source.buffer
        if ("gzip".equals(headers["Content-Encoding"], ignoreCase = true)) {
            GzipSource(responseBodyBuffer.clone()).use { gzippedResponseBody ->
                responseBodyBuffer = Buffer()
                responseBodyBuffer.writeAll(gzippedResponseBody)
            }
        }
        return responseBodyBuffer.clone().readString(charset)
    }

    private fun Headers.toLoggingMap(): Map<String, String> = mutableMapOf<String, String>().also {
        this.toMultimap().forEach { (key, value) ->
            it[key] = value.joinToString(separator = "; ")
        }
    }

    // private fun getCurl(request: Request) = StringBuilder("curl --compressed -v").apply {
    //     append(" -X ${request.method}")
    //     append(" '${request.url}'")
    //     request.headers.toLoggingMap().forEach { (key, value) -> append(" -H '$key: $value'") }
    //     request.body?.let { it.toLoggingString(request.headers, false).apply { if (isNotBlank()) append(" -d '$this'") } }
    // }.toString()

    // private fun appendCookie(builder: StringBuilder, key: String, value: String) = builder.append(" -b '$key=$value'")
}
