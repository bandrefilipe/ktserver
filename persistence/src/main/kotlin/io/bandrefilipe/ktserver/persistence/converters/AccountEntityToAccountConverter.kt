package io.bandrefilipe.ktserver.persistence.converters

import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.Email
import io.bandrefilipe.ktserver.application.model.Username
import io.bandrefilipe.ktserver.commons.wrappers.Secret
import io.bandrefilipe.ktserver.persistence.AccountEntity
import mu.KotlinLogging
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class AccountEntityToAccountConverter : Converter<AccountEntity, Account> {

    init {
        log.debug { "Creating component ${javaClass.simpleName}" }
    }

    override fun convert(source: AccountEntity): Account {
        log.trace { "Converter#convert(source=$source)" }
        return Account(
            id = source.id ?: throwExceptionForMissingId(),
            username = Username(source.username),
            email = Secret(Email(source.email)),
            salt = source.salt,
            password = source.password
        )
    }
}

private val log = KotlinLogging.logger {}

private fun throwExceptionForMissingId(): Nothing = throw IllegalArgumentException(
    """Conversion of type ${Account::class.java} failed due to unexpected missing ("null") ID in type ${AccountEntity::class.java}"""
)
