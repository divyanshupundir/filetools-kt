package com.divpundir.filetools.env

public interface EnvFile {

    public val entries: Map<String, String>

    public fun write()

    public operator fun get(key: String): String?

    public operator fun set(key: String, value: String)
}
