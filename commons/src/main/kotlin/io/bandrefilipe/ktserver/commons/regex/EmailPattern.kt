package io.bandrefilipe.ktserver.commons.regex

import java.util.regex.Pattern

private const val EMAIL_REGEX: String = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"

/**
 * https://stackoverflow.com/questions/8204680/java-regex-email#answer-48725527
 */
val EMAIL_PATTERN: Pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
