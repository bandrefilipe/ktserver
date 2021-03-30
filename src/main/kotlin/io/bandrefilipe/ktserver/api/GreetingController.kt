package io.bandrefilipe.ktserver.api

import io.bandrefilipe.ktserver.application.GreetingUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/greeting"])
private class GreetingController(
    @Autowired
    private val greetingUseCase: GreetingUseCase
) {

    @GetMapping
    fun getGreeting(): String {
        return greetingUseCase.greet()
    }

    @GetMapping(path = ["/{subject}"])
    fun getGreeting(@PathVariable(name = "subject") subject: String): String {
        return greetingUseCase.greet(subject)
    }
}
