package ru.talenttech.xqa.request

interface RequestMapper {
    var request: Any
    fun map(method: String, request: Request.PreparedRequest): RequestMapper
}
