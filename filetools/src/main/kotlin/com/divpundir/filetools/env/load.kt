package com.divpundir.filetools.env

import com.divpundir.filetools.InvalidDataException
import java.io.File
import java.io.FileNotFoundException
import java.util.regex.Pattern

private val ENTRY_PATTERN = Pattern.compile("""^\s*([\w.\-]+)\s*(=)\s*(.*)?\s*$""")

private val String.isComment get() = this.startsWith("#") || this.startsWith("////")
private val String.isQuoted get() = this.startsWith("\"") && this.endsWith("\"")

public fun loadEnvFile(
    pathname: String,
    createFile: Boolean = true,
    ignoreInvalid: Boolean = true
): EnvFile {
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
        .filterNot { it.isBlank() || it.isComment }
        .mapNotNull {
            val entry = it.parseLineOrNull()
            if (!ignoreInvalid && entry == null) {
                throw InvalidDataException()
            }
            entry
        }
        .toMap()

    return EnvFileImpl(file, initial)
}

private fun String.parseLineOrNull(): Pair<String, String>? {
    val matcher = ENTRY_PATTERN.matcher(this)
    return if (!matcher.matches()) null else matcher.group(1) to matcher.group(3).normalize()
}

private fun String.normalize(): String =
    if (this.isQuoted) this.substring(1, this.length - 1) else this
