package io.bandrefilipe.ktserver.commons.wrappers

/**
 * A wrapper class which protect its internal `data` from `toString()` calls.
 */
class Secret<T>(val data: T) {

    override fun hashCode(): Int = data.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Secret<*>
        if (data != other.data) return false
        return true
    }

    override fun toString(): String = "secret"
}
