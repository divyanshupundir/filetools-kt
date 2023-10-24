package com.divpundir.filetools

/**
 * Thrown when the data in a file is invalid. Generally thrown during deserialization.
 */
public class InvalidDataException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(
    message,
    cause
)
