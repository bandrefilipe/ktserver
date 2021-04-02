package io.bandrefilipe.ktserver.persistence

import io.bandrefilipe.ktserver.application.GreetingRetriever
import mu.KotlinLogging
import org.springframework.stereotype.Repository

private val log = KotlinLogging.logger {}

@Repository
class GreetingRetrieverAdapter : GreetingRetriever {

    init {
        log.debug { "Creating component ${this.javaClass.simpleName}" }
    }

    override fun retrieveGreeting(subject: String): String {
        log.trace { "GreetingRetriever#retrieveGreeting(subject=$subject)" }
        return "Hello, $subject!"
    }
}
