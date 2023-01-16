package ru.talenttech.xqa.actions

import ru.talenttech.xqa.response.Response

fun Response.shouldBe(vararg condition: Condition): Response {
    condition.forEach {
        it.apply(this)
    }
    return this
}
