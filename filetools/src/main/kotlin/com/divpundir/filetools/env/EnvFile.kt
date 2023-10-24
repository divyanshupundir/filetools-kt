package com.divpundir.filetools.env

/**
 * Representation of an environment file. Each entry is a key-value pair, where the key and its corresponding value are
 * strings.
 */
public interface EnvFile {

    /**
     * The in-memory map of all the entries in the file.
     */
    public val entries: Map<String, String>

    /**
     * Returns the value corresponding to the given [key] by reading the in-memory map, or null if the key does not
     * exist.
     */
    public operator fun get(key: String): String?

    /**
     * Sets the [value] corresponding to the given [key] in the in-memory map. Does not write to the file.
     */
    public operator fun set(key: String, value: String)

    /**
     * Writes the in-memory map of entries to the file.
     */
    public fun write()
}
