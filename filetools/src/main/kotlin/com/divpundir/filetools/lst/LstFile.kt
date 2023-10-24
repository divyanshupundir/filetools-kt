package com.divpundir.filetools.lst

/**
 * Representation of an LST file. Each entry is a string.
 */
public interface LstFile {

    /**
     * The in-memory list of all the entries in the file.
     */
    public val entries: List<String>

    /**
     * Swaps the in-memory list of entries with the provided [values]. Does not write to the file.
     */
    public fun update(values: List<String>)

    /**
     * Writes the in-memory list of entries to the file.
     */
    public fun write()
}
