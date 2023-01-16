package ru.talenttech.xqa.unit

import kotlin.test.assertEquals
import org.junit.Test
import ru.talenttech.xqa.Authentication

class AuthenticationTest {

    @Test
    fun basic() {
        assertEquals("Basic bG9naW46cGFzc3dvcmQ=", Authentication.basic("login", "password"))
    }
}
