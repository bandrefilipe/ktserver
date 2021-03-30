package io.bandrefilipe.ktserver.api

import io.bandrefilipe.ktserver.application.GreetingUseCase
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping(path = ["/greeting"])
private class GreetingController(
    @Autowired
    private val greetingUseCase: GreetingUseCase
) {

    init {
        log.debug { "Creating component ${this.javaClass.simpleName}" }
    }

    @GetMapping
    fun getGreeting(): String {
        log.trace { "GreetingController#getGreeting()" }
        return greetingUseCase.greet()
    }

    @GetMapping(path = ["/{subject}"])
    fun getGreeting(@PathVariable(name = "subject") subject: String): String {
        log.trace { "GreetingController#getGreeting(subject=$subject)" }
        return greetingUseCase.greet(subject)
    }
}
