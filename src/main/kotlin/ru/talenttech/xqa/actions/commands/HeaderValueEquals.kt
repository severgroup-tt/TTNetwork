package ru.talenttech.xqa.actions.commands

import org.assertj.core.api.Assertions
import ru.talenttech.xqa.actions.Condition
import ru.talenttech.xqa.response.Response

class HeaderValueEquals(private val key: String, private val value: String? = null) : Condition() {
    override fun apply(response: Response) = response.also {
        Assertions
            .assertThat(it.headers.getValue(key))
            .`as`("Value of $key header should be equals: $value")
            .contains(value)
    }
}
