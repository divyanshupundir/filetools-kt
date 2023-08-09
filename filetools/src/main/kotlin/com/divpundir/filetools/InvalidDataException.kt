package com.divpundir.filetools

public class InvalidDataException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(
    message,
    cause
)
