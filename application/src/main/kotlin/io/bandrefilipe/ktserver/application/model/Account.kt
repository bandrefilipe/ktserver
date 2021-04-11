package io.bandrefilipe.ktserver.application.model

import io.bandrefilipe.ktserver.commons.wrappers.Secret

data class Account(
    val id: Int,
    val username: Username,
    val email: Secret<Email>,
    val salt: Int,
    val password: String
)
