package com.divpundir.filetools.env

import java.io.File

internal class EnvFileImpl(
    private val file: File,
    initial: Map<String, String>,
) : EnvFile {

    private val _entries: MutableMap<String, String> = initial.toMutableMap()
    override val entries: Map<String, String> get() = _entries.toMap()

    override fun get(key: String): String? = entries[key]

    override fun set(key: String, value: String): Unit = synchronized(this) {
        _entries[key] = value
    }

    override fun write() {
        val content = synchronized(this) {
            entries.map { "${it.key.quoted()}=${it.value.quoted()}" }.joinToString(separator = "\n")
        }
        file.writeText(content)
    }

    private fun String.quoted() = "\"$this\""
}
