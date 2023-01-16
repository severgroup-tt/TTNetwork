package ru.talenttech.xqa.properties

private const val FILE_NAME = "libraryInfo.properties"

object LibraryInfo : PropertiesProviderAdapter(FILE_NAME) {
    fun name(): String = properties.getProperty("library_name")
    fun version(): String = properties.getProperty("library_version")
}
