package com.divpundir.filetools.csv

import com.divpundir.filetools.InvalidDataException
import java.io.File
import java.io.FileNotFoundException

/**
 * Loads a CSV file from the given [pathname] and returns a [CsvFile] instance.
 *
 * If [createFile] is true, the file is created if it does not exist.
 *
 * The file is read and parsed into a list of entries using the provided [serializer]. If [ignoreInvalid] is true,
 * invalid entries are ignored during deserialization, otherwise an [InvalidDataException] is thrown.
 */
public fun <T> loadCsvFile(
    pathname: String,
    serializer: CsvFile.Serializer<T>,
    createFile: Boolean = true,
    ignoreInvalid: Boolean = true,
): CsvFile<T> {
    val file = File(pathname)
    if (!file.exists()) {
        if (!createFile) {
            throw FileNotFoundException("File $pathname does not exist")
        }
        file.parentFile.mkdirs()
        file.createNewFile()
    }

    val initial: List<T> = file
        .readLines()
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .mapNotNull {
            try {
                serializer.fromRow(it)
            } catch (e: InvalidDataException) {
                if (ignoreInvalid) null else throw e
            }
        }

    return CsvFileImpl(file, initial, serializer)
}
