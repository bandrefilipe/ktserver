package io.bandrefilipe.ktserver.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, Long> {

    fun findByUsername(username: String): AccountEntity?
}
