package ru.talenttech.xqa.okhttp.request

import java.io.File
import java.nio.file.Files
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.request.MultipartBody
import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.request.RequestMapper

class OkHttpRequestMapper : RequestMapper {
    override lateinit var request: Any

    override fun map(method: String, request: Request.PreparedRequest) = this.apply {
        val contentType = request.headers[HttpHeaders.CONTENT_TYPE]?.toMediaTypeOrNull()
        val body = when (request.body) {
            is String -> (request.body as String).toRequestBody(contentType)
            is ByteArray -> (request.body as ByteArray).toRequestBody(contentType)
            is File -> (request.body as File).asRequestBody(contentType)
            is MultipartBody -> {
                val multipartBody = request.body as MultipartBody
                okhttp3.MultipartBody.Builder().apply {
                    setType(okhttp3.MultipartBody.FORM)
                    multipartBody.parts.forEach {
                        if (it.file != null && it.value == null) {
                            addFormDataPart(
                                it.name,
                                it.fileName!!,
                                it.file!!.asRequestBody(Files.probeContentType(it.file!!.toPath()).toMediaTypeOrNull())
                            )
                        } else if (it.value != null && it.file == null) {
                            addFormDataPart(it.name, it.value!!)
                        }
                    }
                }.build()
            }

            else -> null
        }

        this.request = okhttp3.Request.Builder()
            .url(request.url)
            .method(method, body)
            .apply {
                request.headers.forEach { (k, v) ->
                    addHeader(k, v)
                }
            }
            .build()
    }
}
