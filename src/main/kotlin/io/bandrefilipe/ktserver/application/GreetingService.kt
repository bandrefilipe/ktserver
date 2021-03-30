package io.bandrefilipe.ktserver.application

import org.springframework.stereotype.Service

@Service
private class GreetingService : GreetingUseCase {

    override fun greet(subject: String): String {
        return "Hello, ${subject.trim().capitalize()}!"
    }
}
