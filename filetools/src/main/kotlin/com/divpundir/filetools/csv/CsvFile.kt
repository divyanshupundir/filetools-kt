package com.divpundir.filetools.csv

/**
 * Representation of a CSV file. Here, [T] is the type of the entries in the file, where each entry is a row.
 */
public interface CsvFile<T> {

    /**
     * The in-memory list of all the entries in the file.
     */
    public val entries: List<T>

    /**
     * Appends a new entry to the in-memory list of entries. Does not write to the file.
     */
    public fun append(value: T)

    /**
     * Swaps the in-memory list of entries with the provided [values]. Does not write to the file.
     */
    public fun update(values: List<T>)

    /**
     * Writes the in-memory list of entries to the file.
     */
    public fun write()

    /**
     * Serializer for the entries in the file.
     */
    public interface Serializer<T> {

        /**
         * Deserializes a row of text into an entry. Throws [com.divpundir.filetools.InvalidDataException] if the data
         * is invalid.
         */
        public fun fromRow(text: String): T

        /**
         * Serializes an entry into a row of text.
         */
        public fun toRow(value: T): String
    }
}
