package io.bandrefilipe.ktserver.application.model

class RawPassword(value: String) {

    val value: String = value.also(::validate)
}

private fun validate(password: String) {}
