package ru.talenttech.xqa.validators

inline fun <T> T.validate(block: (T) -> Boolean): Boolean {
    return block(this)
}
