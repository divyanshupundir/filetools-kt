package com.divpundir.filetools.json

public interface JsonFile<T> {

    public val value: T

    public fun update(value: T)

    public fun write()

    public interface Serializer<T> {

        public fun fromJson(content: String): T

        public fun toJson(value: T): String
    }
}
