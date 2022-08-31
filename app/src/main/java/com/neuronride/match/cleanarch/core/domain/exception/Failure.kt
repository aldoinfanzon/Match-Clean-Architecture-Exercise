package com.neuronride.match.cleanarch.core.domain.exception

open class Failure(
    throwable: Throwable? = null,
    message: String? = null
) : Throwable(
    cause = throwable,
    message = message
)