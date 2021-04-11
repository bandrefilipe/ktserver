package io.bandrefilipe.ktserver.persistence.converters

import io.bandrefilipe.ktserver.application.model.NewAccount
import io.bandrefilipe.ktserver.persistence.AccountEntity
import mu.KotlinLogging
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
private class NewAccountToAccountEntityConverter : Converter<NewAccount, AccountEntity> {

    init {
        log.debug { "Creating component ${javaClass.simpleName}" }
    }

    override fun convert(source: NewAccount): AccountEntity {
        log.trace { "Converter#convert(source=$source)" }
        return AccountEntity(
            username = source.username.value,
            email = source.email.data.value,
            salt = source.salt,
            password = source.password
        )
    }
}

private val log = KotlinLogging.logger {}
