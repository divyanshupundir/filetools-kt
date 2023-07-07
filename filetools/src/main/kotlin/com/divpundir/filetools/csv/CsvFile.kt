package com.divpundir.filetools.csv

public interface CsvFile<T> {

    public val entries: List<T>

    public fun append(value: T)

    public fun update(values: List<T>)

    public fun write()

    public interface Serializer<T> {

        public fun fromRow(text: String): T

        public fun toRow(value: T): String
    }
}
