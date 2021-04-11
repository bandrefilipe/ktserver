package io.bandrefilipe.ktserver.commons.encryptions

import org.apache.commons.codec.digest.DigestUtils

private const val ENCRYPTION_TOKEN: String = "ENCRYPTED:"

private fun isEncrypted(value: String): Boolean = value.startsWith(ENCRYPTION_TOKEN)

private fun handleEncryption(
    data: String,
    encryptor: (String) -> String
): String {
    return when {
        isEncrypted(data) -> data
        else -> encryptor.invoke(data).let { "$ENCRYPTION_TOKEN$it" }
    }
}

fun sha256Hex(valueToEncrypt: String): String {
    return handleEncryption(valueToEncrypt, DigestUtils::sha256Hex)
}
