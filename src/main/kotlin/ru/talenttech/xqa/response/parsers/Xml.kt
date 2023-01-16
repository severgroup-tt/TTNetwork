package ru.talenttech.xqa.response.parsers

import io.restassured.path.xml.XmlPath
import ru.talenttech.xqa.response.Parser

object Xml : Parser {
    override fun <T> getValueByPath(body: String, path: String): T = XmlPath(body).get(path)
}
