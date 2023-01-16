package ru.talenttech.xqa.unit

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Test
import ru.talenttech.xqa.HttpHeaders
import ru.talenttech.xqa.config.HttpConfig
import ru.talenttech.xqa.request.MultipartBody
import ru.talenttech.xqa.request.Request
import ru.talenttech.xqa.testData.User
import test_data.protobuf.UserProto

class PreparedRequestTest {

    @Test
    fun `Not apply baseUrl if it null`() {
        val request = Request(url = "https://example.com").prepare(HttpConfig.Builder().baseUrl(null).build())
        assertEquals("https://example.com/", request.url)
    }

    @Test
    fun `Apply baseUrl if requestUrl is null`() {
        val request = Request(url = null).prepare(HttpConfig.Builder().baseUrl("https://example.com").build())
        assertEquals("https://example.com/", request.url)
    }

    @Test
    fun `Not apply baseUrl if requestUrl with schema and domain`() {
        val request = Request(url = "https://example.com").prepare(HttpConfig.Builder().baseUrl("https://example.com").build())
        assertEquals("https://example.com/", request.url)
    }

    @Test
    fun `Apply baseUrl if requestUrl without schema and domain`() {
        val request = Request(url = "/test/endpoint").prepare(HttpConfig.Builder().baseUrl("https://example.com").build())
        assertEquals("https://example.com/test/endpoint", request.url)
    }

    @Test
    fun `Apply baseUrl if it ends with slash and path start without slash`() {
        val request = Request(url = "test/endpoint").prepare(HttpConfig.Builder().baseUrl("https://example.com/").build())
        assertEquals("https://example.com/test/endpoint", request.url)
    }

    @Test
    fun `Apply baseUrl if it ends without slash and path start with slash`() {
        val request = Request(url = "/test/endpoint").prepare(HttpConfig.Builder().baseUrl("https://example.com").build())
        assertEquals("https://example.com/test/endpoint", request.url)
    }

    @Test
    fun `Apply baseUrl if it ends with slash and path start with slash`() {
        val request = Request(url = "/test/endpoint").prepare(HttpConfig.Builder().baseUrl("https://example.com/").build())
        assertEquals("https://example.com/test/endpoint", request.url)
    }

    @Test
    fun `Apply baseUrl if it ends without slash and path start without slash`() {
        val request = Request(url = "test/endpoint").prepare(HttpConfig.Builder().baseUrl("https://example.com").build())
        assertEquals("https://example.com/test/endpoint", request.url)
    }

    @Test
    fun `Apply query parameters`() {
        val request = Request(
            url = "https://example.com/endpoint/",
            queryParams = mutableMapOf("query_str" to "value_str", "query_arr[]" to listOf("arr_1", "arr_2"))
        ).prepare(HttpConfig.Builder().build())
        assertEquals("https://example.com/endpoint/?query_str=value_str&query_arr[]=arr_1&query_arr[]=arr_2", request.url)
    }

    @Test
    fun `Replace pathParams in url`() {
        val request = Request(
            url = "https://example.com/company/{companyId}/user/{userId}",
            urlParams = mutableListOf("123", "54321")
        ).prepare(HttpConfig.Builder().build())
        assertEquals("https://example.com/company/123/user/54321", request.url)
    }

    @Test
    fun `Apply body format for String`() {
        val request = Request(url = "https://example.com", body = "string body").prepare(HttpConfig.Builder().build())
        assertTrue(request.body is String)
        assertEquals("string body", request.body)
    }

    @Test
    fun `Apply body format for OkHttp RequestBody`() {
        val body = String().toRequestBody(null)
        val request = Request(url = "https://example.com", body = body).prepare(HttpConfig.Builder().build())
        assertTrue(request.body is RequestBody)
        assertEquals(body, request.body)
    }

    @Test
    fun `Apply body format for MultipartBody`() {
        val body = MultipartBody().addFormDataPart("name", "value")
        val request = Request(url = "https://example.com", body = body).prepare(HttpConfig.Builder().build())
        assertTrue(request.body is MultipartBody)
        assertEquals(body, request.body)
    }

    @Test
    fun `Apply body format for protobuf Message`() {
        val body = UserProto.CreateRequest.newBuilder().setName("John").build()
        val request = Request(url = "https://example.com", body = body).prepare(HttpConfig.Builder().build())
        assertTrue(request.body is ByteArray)
        assertTrue((request.body as ByteArray?).contentEquals(body.toByteArray()))
    }

    @Test
    fun `Apply body format for POJO`() {
        val body = User("John", "Doe", 27)
        val request = Request(url = "https://example.com", body = body).prepare(HttpConfig.Builder().build())
        assertTrue(request.body is String)
        assertEquals("""{"first_name":"John","last_name":"Doe","age":27}""", request.body)
    }

    @Test
    fun `Apply permanent headers from config`() {
        val config = HttpConfig.Builder().headers(mapOf("test_header" to "test_value")).build()
        val request = Request("https://example.com").prepare(config)
        assertEquals(request.headers["test_header"], "test_value")
    }

    @Test
    fun `Apply basic authentication  from config`() {
        val config = HttpConfig.Builder().basicAuthentication("login" to "password").build()
        val request = Request("https://example.com").prepare(config)
        assertEquals(request.headers[HttpHeaders.AUTHORIZATION], "Basic bG9naW46cGFzc3dvcmQ=")
    }
}
