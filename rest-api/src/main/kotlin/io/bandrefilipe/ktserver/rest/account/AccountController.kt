package io.bandrefilipe.ktserver.rest.account

import io.bandrefilipe.ktserver.application.ChangeAccountPasswordUseCase
import io.bandrefilipe.ktserver.application.CreateAccountUseCase
import io.bandrefilipe.ktserver.application.FetchAccountUseCase
import io.bandrefilipe.ktserver.application.model.Account
import io.bandrefilipe.ktserver.application.model.AccountCredentials
import io.bandrefilipe.ktserver.application.model.Username
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1/account"])
private class AccountController(
    @Autowired private val conversionService: ConversionService,
    @Autowired private val createAccountUseCase: CreateAccountUseCase,
    @Autowired private val fetchAccountUseCase: FetchAccountUseCase,
    @Autowired private val changeAccountPasswordUseCase: ChangeAccountPasswordUseCase
) {

    init {
        log.debug { "Creating component ${javaClass.simpleName}" }
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postAccount(@RequestBody requestBody: CreateAccountRequest): ResponseEntity<Any> {
        log.trace { "AccountController#postAccount(requestBody=$requestBody)" }
        return conversionService
            .convert(requestBody, AccountCredentials::class.java)!!
            .let(createAccountUseCase::createAccount)
            .let(::CreateAccountResponse)
        // TODO Return 422 Unprocessable Entity in case of error
    }

    @GetMapping(
        path = ["/{username}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAccountByUsername(@PathVariable("username") username: String): ResponseEntity<Account> {
        log.trace { "AccountController#getAccountByUsername(username=$username)" }
        TODO("Implement getAccountByUsername")
        return fetchAccountUseCase.fetchAccountByUsername(Username(username))
            .let { ResponseEntity.ok(it) }
    }

    @PutMapping(
        path = ["/password"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun putAccountPassword(@RequestBody requestBody: ChangeAccountPasswordRequest): ResponseEntity<Any> {
        log.trace { "AccountController#putAccountPassword(requestBody=$requestBody)" }
        TODO("Implement putAccountPassword")
        changeAccountPasswordUseCase.changeAccountPassword()
        return ResponseEntity.accepted().body(Any())
    }
}

private val log = KotlinLogging.logger {}
