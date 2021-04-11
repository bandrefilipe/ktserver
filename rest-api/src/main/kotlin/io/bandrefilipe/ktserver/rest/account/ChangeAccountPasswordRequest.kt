package io.bandrefilipe.ktserver.rest.account

internal data class ChangeAccountPasswordRequest(
    val email: String,
    val currentPassword: String,
    val newPassword: String
) {
    override fun toString(): String = "${javaClass.simpleName}(email=secret, currentPassword=secret, newPassword=secret)"
}
