package com.divpundir.filetools.lst

import java.io.File
import java.io.FileNotFoundException

/**
 * Loads an LST file from the given [pathname] and returns an [LstFile] instance.
 *
 * If [createFile] is true, the file is created if it does not exist.
 *
 * The file is read and parsed into a list of entries. Only entries that pass the [filter] are included in the list.
 */
public fun loadLstFile(
    pathname: String,
    createFile: Boolean = true,
    filter: (String) -> Boolean = { true },
): LstFile {
    val file = File(pathname)
    if (!file.exists()) {
        if (!createFile) {
            throw FileNotFoundException("File $pathname does not exist")
        }
        file.parentFile.mkdirs()
        file.createNewFile()
    }

    val initial = file
        .readLines()
        .map { it.trim() }
        .filter(filter)

    return LstFileImpl(file, initial)
}
