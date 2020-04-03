package dev.reimer.alexa

import dev.reimer.kotlin.jvm.ktx.stripSubDomain
import java.io.File

internal class AlexaRanksFileCache(top1mFile: File) : AbstractMap<String, Long>() {

    override val entries: Set<Map.Entry<String, Long>> by lazy { buildCache(top1mFile) }

    private fun buildCache(file: File): Set<Map.Entry<String, Long>> {
        return file.useLines { lines ->
            lines.map { line ->
                val (rank, domain) = line.split(",")
                Entry(domain, rank.toLong())
            }.toSet()
        }
    }

    override fun containsKey(key: String): Boolean {
        return when {
            super.containsKey(key) -> true
            key.isNotEmpty() -> containsKey(key.stripSubDomain())
            else -> false
        }
    }

    override fun get(key: String): Long? {
        return when {
            super.containsKey(key) -> super.get(key)
            key.isNotEmpty() -> get(key.stripSubDomain())
            else -> null
        }
    }

    private class Entry(override val key: String, override val value: Long) : Map.Entry<String, Long>
}