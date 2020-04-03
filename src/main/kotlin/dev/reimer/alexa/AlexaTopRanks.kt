package dev.reimer.alexa

import kotlinx.coroutines.runBlocking
import java.io.File
import java.time.LocalDateTime

object AlexaTopRanks {
    private val DEFAULT_CACHE_DIR by lazy { createTempDir("alexa") }

    suspend fun get(timestamp: LocalDateTime = LocalDateTime.now(), cacheDirectory: File) =
        AlexaTopRanksProvider(timestamp, cacheDirectory).get()

    suspend fun get(timestamp: LocalDateTime = LocalDateTime.now()) = get(timestamp, DEFAULT_CACHE_DIR)

    fun getBlocking(timestamp: LocalDateTime = LocalDateTime.now(), cacheDirectory: File) =
        runBlocking { get(timestamp, cacheDirectory) }

    fun getBlocking(timestamp: LocalDateTime = LocalDateTime.now()) = runBlocking { get(timestamp) }
}
