package ru.talenttech.xqa.properties

import java.io.FileNotFoundException
import java.util.Properties

open class PropertiesProviderAdapter(
    private val fileName: String,
    val properties: Properties = Properties()
) {
    init {
        loadProperties()
    }

    private fun loadProperties() {
        try {
            properties.load(ClassLoader.getSystemResource(fileName).openStream())
        } catch (e: FileNotFoundException) {
            throw FileNotFoundException("Properties file $fileName not found")
        }
    }
}
