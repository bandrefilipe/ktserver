package io.bandrefilipe.ktserver.rest.converters

import io.bandrefilipe.ktserver.application.model.AccountCredentials
import io.bandrefilipe.ktserver.application.model.Email
import io.bandrefilipe.ktserver.application.model.RawPassword
import io.bandrefilipe.ktserver.application.model.Username
import io.bandrefilipe.ktserver.commons.wrappers.Secret
import io.bandrefilipe.ktserver.rest.account.CreateAccountRequest
import mu.KotlinLogging
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
private class CreateAccountRequestToAccountCredentialsConverter :
    Converter<CreateAccountRequest, AccountCredentials> {

    init {
        log.debug { "Creating component ${javaClass.simpleName}" }
    }

    override fun convert(source: CreateAccountRequest): AccountCredentials {
        log.trace { "Converter#convert(source=$source)" }
        return AccountCredentials(
            username = Username(source.username),
            email = Secret(Email(source.email)),
            rawPassword = Secret(RawPassword(source.password))
        )
    }
}

private val log = KotlinLogging.logger {}
