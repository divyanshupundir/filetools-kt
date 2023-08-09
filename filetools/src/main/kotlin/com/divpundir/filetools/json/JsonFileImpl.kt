package com.divpundir.filetools.json

import java.io.File

internal class JsonFileImpl<T>(
    private val file: File,
    initial: T,
    private val serializer: JsonFile.Serializer<T>,
) : JsonFile<T> {

    private var _value = initial
    override val value get() = _value

    override fun update(value: T): Unit = synchronized(this) {
        _value = value
    }

    override fun write() {
        val content = synchronized(this) {
            serializer.toJson(value)
        }
        file.writeText(content)
    }
}
