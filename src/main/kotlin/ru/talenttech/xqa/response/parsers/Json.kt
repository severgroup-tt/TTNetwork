package ru.talenttech.xqa.response.parsers

import com.google.protobuf.Message
import com.jayway.jsonpath.JsonPath
import java.io.File
import java.io.InputStream
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.RequestBody
import okio.ByteString
import org.json.JSONObject
import ru.talenttech.xqa.request.MultipartBody
import ru.talenttech.xqa.response.Parser

object Json : Parser {

    private val prettyJson = Json { prettyPrint = true; encodeDefaults = true }
    val json = Json {
        encodeDefaults = true
    }

    override fun <T> getValueByPath(body: String, path: String): T = JsonPath.read(body, path)

    inline fun <reified T> deserializeByPath(json: String, jsonPath: String, type: Class<T> = T::class.java): T = deserialize(
        JSONObject.valueToString(getValueByPath(json, jsonPath))
    )

    inline fun <reified T> deserialize(json: String): T = Json.decodeFromString(json)

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T : Any> serialize(obj: T): String = when (T::class.simpleName) {
        "Any" -> {
            when (obj::class.simpleName) {
                "String" -> json.encodeToString(serializer(obj::class.java.nestHost), obj).replace("\"", "")
                else -> json.encodeToString(serializer(obj::class.java.nestHost), obj)
            }
        }
        else -> json.encodeToString(obj)
    }

    fun toJsonElement(json: String) = Json.parseToJsonElement(json)

    fun prettyPrint(json: String): String = prettyJson.decodeFromString(json)

    fun isSerializableCandidate(obj: Any?): Boolean = !(
        obj == null || obj is ByteArray || obj is ByteString || obj is InputStream || obj is File ||
            obj is String || obj is Message || obj is RequestBody || obj is MultipartBody
        )
}
