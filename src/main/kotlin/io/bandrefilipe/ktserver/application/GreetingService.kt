package io.bandrefilipe.ktserver.application

import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
private class GreetingService : GreetingUseCase {

    init {
        log.debug { "Creating component ${this.javaClass.simpleName}" }
    }

    override fun greet(subject: String): String {
        log.trace { "GreetingUseCase#greet(subject=$subject)" }
        return "Hello, ${subject.trim().capitalize()}!"
    }
}
