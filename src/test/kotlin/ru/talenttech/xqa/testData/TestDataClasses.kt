package ru.talenttech.xqa.testData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class JsonTestModel(
    @SerialName("insert-key-here") val insertKeyHere: String,
    @SerialName("key") val key: String
)

@Serializable
data class JsonTestModelWithNullKey(
    @SerialName("insert-key-here") val insertKeyHere: String,
    @SerialName("key") val key: String,
    @SerialName("value") val value: String? = null
)

@Serializable
data class NestedJsonTestModel(
    @SerialName("insert-key-here") val insertKeyHere: String,
    @SerialName("key") val key: String
)

@Serializable
data class User(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val age: Int
)

@Serializable
data class UsersList(
    val users: List<User>,
    val count: Int = users.size
)
