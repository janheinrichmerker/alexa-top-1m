package dev.reimer.alexa

import dev.reimer.domain.ktx.Domain
import java.io.File

internal class AlexaRanksFileCache(top1mFile: File) : AbstractMap<Domain, Long>() {

    override val entries by lazy { buildCache(top1mFile) }

    private fun buildCache(file: File): Set<Map.Entry<Domain, Long>> {
        return file.useLines { lines ->
            lines.map { line ->
                val (rank, domain) = line.split(",")
                object : Map.Entry<Domain, Long> {
                    override val key = Domain(domain)
                    override val value = rank.toLong()
                }
            }.toSet()
        }
    }

    override fun containsKey(key: Domain): Boolean {
        return when {
            super.containsKey(key) -> true
            key.hasPrefix -> containsKey(key.stripSubDomain())
            else -> false
        }
    }

    override fun get(key: Domain): Long? {
        return when {
            super.containsKey(key) -> super.get(key)
            key.hasPrefix -> get(key.stripSubDomain())
            else -> null
        }
    }

    override fun getOrDefault(key: Domain, defaultValue: Long): Long {
        return when {
            super.containsKey(key) -> super.getOrDefault(key, defaultValue)
            key.hasPrefix -> getOrDefault(key.stripSubDomain(), defaultValue)
            else -> super.getOrDefault(key, defaultValue)
        }
    }
}