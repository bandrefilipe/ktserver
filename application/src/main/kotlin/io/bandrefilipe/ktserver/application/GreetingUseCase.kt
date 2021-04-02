package io.bandrefilipe.ktserver.application

interface GreetingUseCase {

    fun greet(subject: String = "World"): String
}
