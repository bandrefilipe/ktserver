package io.bandrefilipe.ktserver.application.model

import io.bandrefilipe.ktserver.commons.wrappers.Secret

data class AccountCredentials(
    val username: Username,
    val email: Secret<Email>,
    val rawPassword: Secret<RawPassword>
)
