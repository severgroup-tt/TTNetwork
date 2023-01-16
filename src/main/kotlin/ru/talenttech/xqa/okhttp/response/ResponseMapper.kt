package ru.talenttech.xqa.okhttp.response

import ru.talenttech.xqa.ContentType
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.response.Response

fun okhttp3.Response.mapToTtNetworkResponse(config: HttpConfig) = Response(
    protocol = this.protocol.toString(),
    code = this.code,
    url = this.request.url.toString(),
    headers = this.headers.toMultimap(),
    body = this.body?.let {
        if (it.contentType().toString().contains(ContentType.APPLICATION_PROTOBUF)) {
            it.bytes()
        } else {
            it.string()
        }
    },
    config = config
)
