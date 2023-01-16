package ru.talenttech.xqa.actions.commands

import org.assertj.core.api.Assertions
import ru.talenttech.xqa.actions.Condition
import ru.talenttech.xqa.response.Response

class CodeEquals(private val code: Int) : Condition() {
    override fun apply(response: Response) = response.also {
        Assertions
            .assertThat(it.code)
            .`as`("Code should be equals: $code")
            .isEqualTo(code)
    }
}
