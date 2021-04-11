package io.bandrefilipe.ktserver.persistence

import io.bandrefilipe.ktserver.application.AccountRetriever
import io.bandrefilipe.ktserver.application.NewAccountRegister
import io.bandrefilipe.ktserver.application.PersistenceException
import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.NewAccount
import io.bandrefilipe.ktserver.application.model.Username
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Repository

@Repository
private class AccountPersistenceAdapter(
    @Autowired private val conversionService: ConversionService,
    @Autowired private val accountRepository: AccountRepository
) : NewAccountRegister,
    AccountRetriever {

    init {
        log.debug { "Creating component ${javaClass.simpleName}" }
    }

    override fun registerNewAccount(account: NewAccount): Account {
        log.trace { "NewAccountRegister#registerNewAccount(account=$account)" }
        return conversionService.convert(account, AccountEntity::class.java)!!
            .also { log.debug { "Invoking AccountRepository#save(entity=$it)" } }
            .runCatching { accountRepository.save(this) }
            .getOrElse { throw PersistenceException(it) }
            .let { conversionService.convert(it, Account::class.java) }!!
    }

    override fun retrieveAccountByUsername(username: Username): Account {
        log.trace { "AccountRetriever#retrieveAccountByUsername(username=$username)" }
        return accountRepository.findByUsername(username.value)
            ?.let { conversionService.convert(it, Account::class.java) }
            ?: TODO("Implementar caso de talha do AccountRetriever#retrieveAccountByUsername")
    }
}

private val log = KotlinLogging.logger {}
