package com.divpundir.filetools.json

/**
 * Representation of a JSON file. The value is of type [T].
 */
public interface JsonFile<T> {

    /**
     * The in-memory value of the file.
     */
    public val value: T

    /**
     * Updates the in-memory value of the file. Does not write to the file.
     */
    public fun update(value: T)

    /**
     * Writes the in-memory value to the file.
     */
    public fun write()

    /**
     * Serializer for the value in the file.
     */
    public interface Serializer<T> {

        /**
         * Deserializes the given [content] into a value. Throws [com.divpundir.filetools.InvalidDataException] if the
         * data is invalid.
         */
        public fun fromJson(content: String): T

        /**
         * Serializes the given [value] into a string.
         */
        public fun toJson(value: T): String
    }
}
