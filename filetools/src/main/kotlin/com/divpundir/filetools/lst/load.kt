package com.divpundir.filetools.lst

import java.io.File
import java.io.FileNotFoundException

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

    val initial = file.readLines()
        .map { it.trim() }
        .filter(filter)

    return LstFileImpl(file, initial)
}
