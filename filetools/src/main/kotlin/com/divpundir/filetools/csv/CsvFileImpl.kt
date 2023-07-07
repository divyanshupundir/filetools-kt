package com.divpundir.filetools.csv

import java.io.File

internal class CsvFileImpl<T>(
    private val file: File,
    initial: List<T>,
    private val serializer: CsvFile.Serializer<T>
) : CsvFile<T> {

    private val _entries: MutableList<T> = initial.toMutableList()
    override val entries: List<T> get() = _entries.toList()

    override fun append(value: T): Unit = synchronized(this) {
        _entries.add(value)
    }

    override fun update(values: List<T>): Unit = synchronized(this) {
        _entries.clear()
        _entries.addAll(values)
    }

    override fun write() {
        val content = entries.joinToString("\n") { serializer.toRow(it) }
        synchronized(this) {
            file.writeText(content)
        }
    }
}
