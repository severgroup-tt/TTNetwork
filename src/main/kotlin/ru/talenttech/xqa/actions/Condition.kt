package ru.talenttech.xqa.actions

import ru.talenttech.xqa.actions.commands.CodeEquals
import ru.talenttech.xqa.actions.commands.CodeNotEquals
import ru.talenttech.xqa.actions.commands.HeaderAvailable
import ru.talenttech.xqa.actions.commands.HeaderValueEquals
import ru.talenttech.xqa.response.Response

abstract class Condition {
    companion object Items {
        fun codeEquals(code: Int): CodeEquals = CodeEquals(code)
        fun codeNotEquals(code: Int): CodeNotEquals = CodeNotEquals(code)
        fun headerAvailable(header: String): HeaderAvailable = HeaderAvailable(header)
        fun headerValueEquals(key: String, value: String): HeaderValueEquals = HeaderValueEquals(key, value)
    }

    abstract fun apply(response: Response): Response
}
