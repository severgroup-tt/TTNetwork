package ru.talenttech.xqa.request

import java.io.File

open class MultipartBody {
    val parts: MutableList<FormDataPart> = mutableListOf()

    fun addFormDataPart(name: String, file: File, fileName: String = file.name): MultipartBody {
        parts.add(FormDataPart(name, file, fileName))
        return this
    }

    fun addFormDataPart(name: String, value: String): MultipartBody {
        parts.add(FormDataPart(name, value))
        return this
    }

    data class FormDataPart(var name: String, var file: File?, var fileName: String? = file?.name) : MultipartBody() {

        var value: String? = null

        constructor(name: String, value: String) : this(name, null, null) {
            this.value = value
        }
    }
}
