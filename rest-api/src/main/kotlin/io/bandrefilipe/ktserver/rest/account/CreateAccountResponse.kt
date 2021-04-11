package io.bandrefilipe.ktserver.rest.account

import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.commons.extensions.toRootLowerCase
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.util.MultiValueMapAdapter
import java.net.URI

class CreateAccountResponse(account: Account) : ResponseEntity<Any>(headers(account), HttpStatus.CREATED)

private fun headers(account: Account): MultiValueMap<String, String> {
    val locationHeader = Pair(HttpHeaders.LOCATION, locationURI(account))
    return MultiValueMapAdapter(mapOf(locationHeader))
}

private fun locationURI(account: Account): List<String> {
    return listOf(URI("/api/v1/account/${account.username.value.toRootLowerCase()}").toString())
}
