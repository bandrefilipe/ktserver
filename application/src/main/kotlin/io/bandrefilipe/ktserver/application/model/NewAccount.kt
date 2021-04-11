package io.bandrefilipe.ktserver.application.model

import io.bandrefilipe.ktserver.commons.encryptions.sha256Hex
import io.bandrefilipe.ktserver.commons.wrappers.Secret
import java.util.*

class NewAccount(
    credentials: AccountCredentials
) {
    val username: Username = credentials.username
    val email: Secret<Email> = credentials.email
    val salt: Int = RANDOM.nextInt()
    val password: String = sha256Hex(valueToEncrypt = "$username:${credentials.rawPassword.data.value}:$salt")

    private val cachedHashCode: Int = Objects.hash(*arrayOf(username, email, salt, password))
    override fun hashCode(): Int = cachedHashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as NewAccount
        if (username != other.username) return false
        if (email != other.email) return false
        if (salt != other.salt) return false
        if (password != other.password) return false
        return true
    }

    private val cachedToString: String = "${javaClass.simpleName}(username=$username, email=$email, salt=$salt, password=$password)"
    override fun toString(): String = cachedToString
}

private val RANDOM: Random = Random()
