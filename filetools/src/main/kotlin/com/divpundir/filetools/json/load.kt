package com.divpundir.filetools.json

import com.divpundir.filetools.InvalidDataException
import java.io.File
import java.io.FileNotFoundException

public fun <T> loadJsonFile(
    pathname: String,
    serializer: JsonFile.Serializer<T>,
    createFile: Boolean = true,
    fallback: (() -> T)? = null
): JsonFile<T> {
    val file = File(pathname)
    if (!file.exists()) {
        if (!createFile) {
            throw FileNotFoundException("File $pathname does not exist")
        }
        file.parentFile.mkdirs()
        file.createNewFile()
    }

    val initial = try {
        serializer.fromJson(file.readText())
    } catch (e: InvalidDataException) {
        if (fallback != null) fallback.invoke() else throw e
    }

    return JsonFileImpl(file, initial, serializer)
}
