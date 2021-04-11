package io.bandrefilipe.ktserver.application.model

import io.bandrefilipe.ktserver.commons.extensions.toRootLowerCase
import io.bandrefilipe.ktserver.commons.regex.EMAIL_PATTERN

class Email(value: String) {

    val value: String = value
        .let(::sanitize)
        .also(::validate)

    override fun hashCode(): Int = value.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Email
        if (value != other.value) return false
        return true
    }

    override fun toString(): String = "${javaClass.simpleName}(value=$value)"
}

private fun sanitize(email: String): String = email.trim().toRootLowerCase()
private fun validate(email: String) = require(EMAIL_PATTERN.matcher(email).matches()) { "Invalid email: $email" }
