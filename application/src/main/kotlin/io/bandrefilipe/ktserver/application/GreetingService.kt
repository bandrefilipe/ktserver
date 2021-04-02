package io.bandrefilipe.ktserver.application

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
private class GreetingService(
    @Autowired private val greetingRetriever: GreetingRetriever
) : GreetingUseCase {

    init {
        log.debug { "Creating component ${this.javaClass.simpleName}" }
    }

    override fun greet(subject: String): String {
        log.trace { "GreetingUseCase#greet(subject=$subject)" }
        return greetingRetriever.retrieveGreeting(
            subject = subject
                .trim()
                .capitalize()
        )
    }
}
