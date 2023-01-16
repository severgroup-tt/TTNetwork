package ru.talenttech.xqa

import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.strategies.OkhttpStrategy
import ru.talenttech.xqa.request.Request

fun Request.get(
    config: HttpConfig = HttpConfig.Builder().build(),
    httpStrategy: HttpStrategy = OkhttpStrategy(config)
) = httpStrategy.sendRequest(method = "GET", request = this)

fun Request.post(
    config: HttpConfig = HttpConfig.Builder().build(),
    httpStrategy: HttpStrategy = OkhttpStrategy(config)
) = httpStrategy.sendRequest(method = "POST", request = this)

fun Request.put(
    config: HttpConfig = HttpConfig.Builder().build(),
    httpStrategy: HttpStrategy = OkhttpStrategy(config)
) = httpStrategy.sendRequest(method = "PUT", request = this)

fun Request.delete(
    config: HttpConfig = HttpConfig.Builder().build(),
    httpStrategy: HttpStrategy = OkhttpStrategy(config)
) = httpStrategy.sendRequest(method = "DELETE", request = this)

fun Request.patch(
    config: HttpConfig = HttpConfig.Builder().build(),
    httpStrategy: HttpStrategy = OkhttpStrategy(config)
) = httpStrategy.sendRequest(method = "PATCH", request = this)
