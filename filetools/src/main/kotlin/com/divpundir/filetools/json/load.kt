package com.divpundir.filetools.json

import com.divpundir.filetools.InvalidDataException
import java.io.File
import java.io.FileNotFoundException

/**
 * Loads a JSON file from the given [pathname] and returns a [JsonFile] instance.
 *
 * If [createFile] is true, the file is created if it does not exist.
 *
 * The file is read and parsed into a value using the provided [serializer]. If the data is invalid, [fallback] is used
 * to create a value.
 */
public fun <T> loadJsonFile(
    pathname: String,
    serializer: JsonFile.Serializer<T>,
    createFile: Boolean = true,
    fallback: (() -> T)? = null,
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
