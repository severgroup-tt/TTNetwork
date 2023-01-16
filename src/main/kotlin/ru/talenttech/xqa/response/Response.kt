package ru.talenttech.xqa.response

import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.interceptors.ResponseInterceptor
import ru.talenttech.xqa.response.parsers.Html
import ru.talenttech.xqa.response.parsers.Json
import ru.talenttech.xqa.response.parsers.Xml

class Response(
    val protocol: String,
    val code: Int,
    val url: String,
    val headers: Map<String, List<String>>,
    val body: Any? = null,
    val config: HttpConfig
) {

    init {
        applyInterceptors(config, this)
    }
    inline fun <reified T> body(): T = Json.deserialize(body as String)

    inline fun <reified T> body(path: String, type: Class<T>): T = Json.deserializeByPath(body as String, path, type)

    inline fun <reified T> body(
        path: String,
        contentType: String = this.headers[HttpHeaders.CONTENT_TYPE].toString().lowercase()
    ): T = when {
        contentType.contains(ContentType.TEXT_XML) -> Xml.getValueByPath(body as String, path)
        contentType.contains(ContentType.TEXT_HTML) -> Html.getValueByPath(body as String, path)
        else -> Json.deserializeByPath(body as String, path)
    }

    fun bodyAsJsonElement() = Json.toJsonElement(body as String)

    override fun toString() = "Response{protocol=$protocol, code=$code, url=$url, headers=$headers, body=$body}"

    private fun applyInterceptors(config: HttpConfig, response: Response) = config.interceptors.forEach {
        (it as? ResponseInterceptor)?.interceptResponse(response)
    }
}
