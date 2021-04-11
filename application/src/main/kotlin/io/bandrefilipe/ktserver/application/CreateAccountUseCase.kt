package io.bandrefilipe.ktserver.application

import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.AccountCredentials

interface CreateAccountUseCase {

    fun createAccount(accountCredentials: AccountCredentials): Account
}
