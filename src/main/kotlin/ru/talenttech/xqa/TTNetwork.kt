package ru.talenttech.xqa

import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.okhttp.strategies.OkhttpStrategy

class TTNetwork {
    fun getHttpClient(
        config: HttpConfig = HttpConfig.Builder().build(),
        httpStrategy: HttpStrategy = OkhttpStrategy(config)
    ) = Http(config, httpStrategy)
}
