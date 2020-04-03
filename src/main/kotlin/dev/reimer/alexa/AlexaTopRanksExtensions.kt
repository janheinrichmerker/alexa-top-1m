package dev.reimer.alexa

import dev.reimer.domain.ktx.Domain
import dev.reimer.domain.ktx.domain
import java.net.URL

fun <V> Map<Domain, V>.containsKey(key: URL): Boolean = containsKey(key.domain)

operator fun <V> Map<Domain, V>.get(key: URL): V? = get(key.domain)

inline fun <V> Map<Domain, V>.getOrElse(key: URL, defaultValue: () -> V): V = getOrElse(key.domain, defaultValue)

fun <V> Map<Domain, V>.getValue(key: URL): V = getValue(key.domain)

fun <V> Map<Domain, V>.containsKey(key: String): Boolean = containsKey(Domain(key))

operator fun <V> Map<Domain, V>.get(key: String): V? = get(Domain(key))

inline fun <V> Map<Domain, V>.getOrElse(key: String, defaultValue: () -> V): V = getOrElse(Domain(key), defaultValue)

fun <V> Map<Domain, V>.getValue(key: String): V = getValue(Domain(key))
