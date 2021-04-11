package io.bandrefilipe.ktserver.persistence

import javax.persistence.*

@Entity
@Table(name = "account")
data class AccountEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account")
    var id: Int? = null,

    @Column(name = "username", nullable = false, unique = true, updatable = false)
    var username: String = "",

    @Column(name = "email", nullable = false)
    var email: String = "",

    @Column(name = "salt", nullable = false)
    var salt: Int = 0,

    @Column(name = "password", nullable = false)
    var password: String = ""
) {
    override fun toString(): String {
        return "${javaClass.simpleName}(id=$id, username=$username, email=secret, salt=$salt, password=$password)"
    }
}
