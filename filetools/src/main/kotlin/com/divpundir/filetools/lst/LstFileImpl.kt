package com.divpundir.filetools.lst

import java.io.File

internal class LstFileImpl(
    private val file: File,
    initial: List<String>
) : LstFile {

    private val _entries: MutableList<String> = initial.toMutableList()
    override val entries: List<String> get() = _entries.toList()

    override fun update(values: List<String>): Unit = synchronized(this) {
        _entries.clear()
        _entries.addAll(values)
    }

    override fun write() {
        val content = entries.joinToString("\n")
        synchronized(this) {
            file.writeText(content)
        }
    }
}
