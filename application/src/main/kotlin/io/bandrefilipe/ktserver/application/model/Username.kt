package io.bandrefilipe.ktserver.application.model

import io.bandrefilipe.ktserver.commons.extensions.toRootUpperCase

class Username(username: String) {

    val value: String = username
        .let(::sanitize)
        .also(::validate)

    override fun hashCode(): Int = value.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Username
        if (value != other.value) return false
        return true
    }

    override fun toString(): String = value
}

private fun sanitize(username: String): String = username.trim().toRootUpperCase()
private fun validate(username: String) {}
