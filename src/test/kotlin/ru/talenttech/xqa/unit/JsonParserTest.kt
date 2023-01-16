package ru.talenttech.xqa.unit

import kotlin.test.assertEquals
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Test
import ru.talenttech.xqa.response.parsers.Json
import ru.talenttech.xqa.testData.User
import ru.talenttech.xqa.testData.UsersList

internal class JsonParserTest {

    companion object {
        const val JSON = """{"users":[{"first_name":"John","last_name":"Doe","age":27},{"first_name":"Vasya","last_name":"Pupkin","age":35}],"count":2}""" // ktlint-disable max-line-length
        val usersListPojo: UsersList = UsersList(listOf(User("John", "Doe", 27), User("Vasya", "Pupkin", 35)))
    }

    @Test
    fun getValueByPath() {
        assertEquals("John", Json.getValueByPath(JSON, "users[0].first_name"))
    }

    @Test
    fun deserializeObjectByPath() {
        assertEquals(User("John", "Doe", 27), Json.deserializeByPath(JSON, "$.users[0]"))
    }

    @Test
    fun deserializeArrayByPath() {
        assertEquals(usersListPojo.users, Json.deserializeByPath(JSON, "users"))
    }

    @Test
    fun deserialize() {
        assertEquals(usersListPojo, Json.deserialize(JSON))
    }

    @Test
    fun serialize() {
        assertEquals(JSON, Json.serialize(usersListPojo))
    }

    @Test
    fun serializeAny() {
        val anyString: Any = "string"
        assertEquals("string", Json.serialize(anyString))
        val anyInt: Any = 5
        assertEquals("""5""", Json.serialize(anyInt))
        val anyLong: Any = 5L
        assertEquals("""5""", Json.serialize(anyLong))
        val anyFloat: Any = 5.toFloat()
        assertEquals("""5.0""", Json.serialize(anyFloat))
        val anyDouble: Any = 5.toDouble()
        assertEquals("""5.0""", Json.serialize(anyDouble))
        val anyShort: Any = 5.toShort()
        assertEquals("""5""", Json.serialize(anyShort))
        val anyBoolean: Any = true
        assertEquals("""true""", Json.serialize(anyBoolean))
        val anySerializable: Any = usersListPojo
        assertEquals(JSON, Json.serialize(anySerializable))
    }

    @Test
    fun toJsonElement() {
        val jsonElement = Json.toJsonElement(JSON)
        assertEquals(jsonElement.jsonObject.getValue("users").jsonArray[0].jsonObject.getValue("first_name").jsonPrimitive.content, "John")
    }
}
