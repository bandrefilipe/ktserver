package io.bandrefilipe.ktserver.application.services

import io.bandrefilipe.ktserver.application.*
import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.AccountCredentials
import io.bandrefilipe.ktserver.application.model.NewAccount
import io.bandrefilipe.ktserver.application.model.Username
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class AccountService(
    @Autowired private val newAccountRegister: NewAccountRegister,
    @Autowired private val accountRetriever: AccountRetriever
) : CreateAccountUseCase,
    FetchAccountUseCase,
    ChangeAccountPasswordUseCase {

    init {
        log.debug { "Creating component ${javaClass.simpleName}" }
    }

    override fun createAccount(accountCredentials: AccountCredentials): Account {
        log.trace { "CreateAccountUseCase#createAccount(accountCredentials=$accountCredentials)" }
        return NewAccount(accountCredentials)
            .let(newAccountRegister::registerNewAccount)
    }

    override fun fetchAccountByUsername(username: Username): Account {
        log.trace { "FetchAccountUseCase#fetchAccountByUsername(username=$username)" }
        return accountRetriever.retrieveAccountByUsername(username)
    }

    override fun changeAccountPassword() {
        log.trace { "ChangeAccountPasswordUseCase#changeAccountPassword()" }
        TODO("Not yet implemented")
    }
}

private val log = KotlinLogging.logger {}
