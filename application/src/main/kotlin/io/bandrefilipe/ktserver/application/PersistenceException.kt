package io.bandrefilipe.ktserver.application

class PersistenceException(cause: Throwable?) : Exception("Cannot persist entity", cause)
