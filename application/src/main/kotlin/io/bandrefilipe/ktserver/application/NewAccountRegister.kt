package io.bandrefilipe.ktserver.application

import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.NewAccount

interface NewAccountRegister {

    fun registerNewAccount(account: NewAccount): Account
}
