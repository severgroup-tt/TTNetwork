package ru.talenttech.xqa

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import okio.ByteString.Companion.encode

object Authentication {

    fun basic(
        username: String,
        password: String,
        charset: Charset = StandardCharsets.ISO_8859_1
    ): String {
        val usernameAndPassword = "$username:$password"
        val encoded = usernameAndPassword.encode(charset).base64()
        return "Basic $encoded"
    }
}
