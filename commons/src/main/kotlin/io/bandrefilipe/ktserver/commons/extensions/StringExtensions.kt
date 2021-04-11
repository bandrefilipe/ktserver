package io.bandrefilipe.ktserver.commons.extensions

import java.util.*

fun String.toRootUpperCase(): String = this.toUpperCase(Locale.ROOT)

fun String.toRootLowerCase(): String = this.toLowerCase(Locale.ROOT)
