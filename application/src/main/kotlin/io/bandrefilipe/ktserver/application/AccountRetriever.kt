package io.bandrefilipe.ktserver.application

import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.Username

interface AccountRetriever {

    fun retrieveAccountByUsername(username: Username): Account
}
