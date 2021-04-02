package io.bandrefilipe.ktserver.application

interface GreetingRetriever {

    fun retrieveGreeting(subject: String): String
}
