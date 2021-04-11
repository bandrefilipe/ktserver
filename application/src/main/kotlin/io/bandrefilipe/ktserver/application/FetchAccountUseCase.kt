package io.bandrefilipe.ktserver.application

import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.Username

interface FetchAccountUseCase {

    fun fetchAccountByUsername(username: Username): Account
}
