package io.bandrefilipe.ktserver.rest.account

internal data class CreateAccountRequest(
    val username: String,
    val email: String,
    val password: String
) {
    override fun toString(): String = "${javaClass.simpleName}(username=$username, email=secret, password=secret)"
}
