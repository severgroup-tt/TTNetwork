package ru.talenttech.xqa.actions.commands

import org.assertj.core.api.Assertions
import ru.talenttech.xqa.actions.Condition
import ru.talenttech.xqa.response.Response

class HeaderAvailable(private val key: String) : Condition() {
    override fun apply(response: Response) = response.also {
        Assertions
            .assertThat(it.headers.keys)
            .`as`("Header should be available: $key")
            .contains(key)
    }
}
